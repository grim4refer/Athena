package com.arlania.database.model;

import java.time.LocalDateTime;

import static com.google.common.base.Preconditions.checkNotNull;

public final class Donation {

	private final int id;
	private final int productId;
	private final int quantity;
	private final String name;
	private final LocalDateTime time;

	public Donation(int id, int productId, int quantity, String name, LocalDateTime time) {
		this.id = id;
		this.productId = productId;
		this.quantity = quantity;
		this.name = checkNotNull(name, "name");
		this.time = checkNotNull(time, "time");
	}

	public int getId() {
		return id;
	}

	public int getProductId() {
		return productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public String getName() {
		return name;
	}

	public LocalDateTime getTime() {
		return time;
	}

	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public boolean equals(Object object) {
		if (object == this) return true;
		if (object != null && object.getClass() == Donation.class) {
			Donation other = (Donation) object;
			return other.id == this.id;
		}
		return false;
	}

	@Override
	public String toString() {
		return Donation.class.getSimpleName() + "{id=" + id + "}";
	}

}
