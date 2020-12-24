package com.athena.util;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import static com.google.common.base.Preconditions.checkNotNull;

public final class NamedThreadFactory implements ThreadFactory {

	private final String name;
	private final AtomicInteger childCount = new AtomicInteger();
	private final boolean daemon;

	public NamedThreadFactory(String name) {
		this(name, false);
	}

	public NamedThreadFactory(String name, boolean daemon) {
		this.name = checkNotNull(name, "name == null");
		this.daemon = daemon;
	}

	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(r, name + "-" + childCount.incrementAndGet());
		t.setDaemon(daemon);
		return t;
	}

}
