package com.arlania.database;

import com.arlania.util.NamedThreadFactory;
import com.arlania.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Consumer;

import static com.google.common.base.Preconditions.checkNotNull;

public final class Worker {

	public static Worker worker() {
		return instance;
	}

	private static final Worker instance = new Worker();

	private static final Logger log = LogManager.getLogger();


	private final List<Transaction<?>> transactions;
	private final ScheduledThreadPoolExecutor pool;
	private final ScheduledExecutorService service;

	private Worker() {
		final int coreCount = Runtime.getRuntime().availableProcessors();
		transactions = Collections.synchronizedList(new ArrayList<>(100));
		pool = new ScheduledThreadPoolExecutor(coreCount, new NamedThreadFactory("JDBC-Worker"));
		service = Executors.newSingleThreadScheduledExecutor();
		init();
	}

	public <T> Transaction<T> submit(Transaction<T> transaction) {
		transaction.future = pool.submit(() -> transaction.getAction().complete());
		transactions.add(transaction);
		return transaction;
	}

	public <T> Future<T> submit(Callable<T> task) {
		checkNotNull(task, "task");
		return pool.submit(task);
	}

	private void init() {
		service.scheduleWithFixedDelay(this::update, 0, 15, TimeUnit.MILLISECONDS);
	}

	public void shutdown() {
		// TODO ensure all transactions are completed first.
		pool.shutdown();
		service.shutdown();
	}

	@SuppressWarnings("unchecked")
	private void update() {
		if (transactions.isEmpty()) return;

		// TODO Actually send the callbacks back to the main game thread.
		synchronized (transactions) {
			Iterator<Transaction<?>> iterator = transactions.iterator();
			while (iterator.hasNext()) {
				Transaction transaction = iterator.next();
				if (transaction.future.isDone()) {
					try {
						Object data = transaction.future.get();
						Consumer callback = transaction.successCallback;
						if (callback != null) {
							World.callback(() -> handleExceptionalSuccessCallback(callback, data));
						}
					} catch (Throwable t) {
						Consumer<Throwable> callback = transaction.exceptionCallback;
						if (callback == null) {
							log.warn("An uncaught exception (no exception callback) for a transaction!", t);
						} else {
							World.callback(() -> handleExceptionalExceptionCallback(callback, t));
						}
					} finally {
						iterator.remove();
					}
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void handleExceptionalSuccessCallback(Consumer callback, Object result) {
		try {
			callback.accept(result);
		} catch (Throwable t) {
			log.error("A transaction completed successfully, but callback threw an exception!", t);
		}
	}

	@SuppressWarnings("unchecked")
	private void handleExceptionalExceptionCallback(Consumer<Throwable> callback, Throwable cause) {
		try {
			callback.accept(cause);
		} catch (Throwable t) {
			log.error("A transaction completed successfully, but callback threw an exception!", t);
		}
	}

}
