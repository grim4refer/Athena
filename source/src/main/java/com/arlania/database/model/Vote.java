package com.arlania.database.model;

import java.time.LocalDateTime;

import static com.google.common.base.Preconditions.checkNotNull;

public final class Vote {

	private final int id;
	private final String name;
	private final String ipAddress;
	private final LocalDateTime time;
	private final int siteId;
	private boolean claimed;

	public Vote(int id, String name, String ipAddress, LocalDateTime time, int siteId, boolean claimed) {
		this.id = id;
		this.name = checkNotNull(name, "name");
		this.ipAddress = checkNotNull(ipAddress, "ip address");
		this.time = checkNotNull(time, "time");
		this.siteId = siteId;
		this.claimed = claimed;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public int getSiteId() {
		return siteId;
	}

	public boolean isClaimed() {
		return claimed;
	}

	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public boolean equals(Object object) {
		if (object == this) return true;
		if (object != null && object.getClass() == Vote.class) {
			Vote other = (Vote) object;
			return other.id == this.id;
		}
		return false;
	}

	@Override
	public String toString() {
		return Vote.class.getSimpleName() + "{id=" + id + "}";
	}

}
