package com.arlania.database;

import java.util.concurrent.Future;
import java.util.function.Consumer;

import static com.google.common.base.Preconditions.checkNotNull;

public final class Transaction<T> {

	private final AsyncRequest<T> transaction;
	final Consumer<T> successCallback;
	final Consumer<Throwable> exceptionCallback;
	Future<T> future;

	public Transaction(AsyncRequest<T> transaction,
					   Consumer<T> successCallback,
					   Consumer<Throwable> exceptionCallback) {
		this.transaction = checkNotNull(transaction, "transaction == null");
		this.successCallback = successCallback;
		this.exceptionCallback = exceptionCallback;
	}

	public AsyncRequest<T> getAction() {
		return transaction;
	}

	public boolean cancel(boolean mayInterruptIfRunning) {
		if (future == null) return false;
		return future.cancel(mayInterruptIfRunning);
	}

}
