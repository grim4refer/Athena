package com.athena.database;

import java.util.function.Consumer;
import java.util.function.Supplier;

import static com.google.common.base.Preconditions.checkNotNull;

public final class AsyncRequest<T> {

	public static <T> AsyncRequest<T> request(Supplier<T> request) {
		return new AsyncRequest<>(request);
	}

	public static AsyncRequest<Void> run(Runnable r) {
		return new AsyncRequest<>(() -> {
			r.run();
			return null;
		});
	}

	private final Supplier<T> request;

	public AsyncRequest(Supplier<T> request) {
		this.request = checkNotNull(request, "request == null");
	}

	public Transaction<T> queue() {
		return queue(null, null);
	}

	public Transaction<T> queue(Consumer<T> successCallback) {
		return queue(successCallback, null);
	}

	public Transaction<T> queue(Consumer<T> successCallback, Consumer<Throwable> exceptionCallback) {
		return Worker.worker().submit(new Transaction<>(this, successCallback, exceptionCallback));
	}

	public T complete() {
		return request.get();
	}

}
