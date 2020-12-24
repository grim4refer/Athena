package com.athena.database.model;

import static com.google.common.base.Preconditions.checkNotNull;

public final class RefferalReward {

	private final int referralId;
	private final String referralName;
	private final int availableAmount;
	private int initialAmount;

	public RefferalReward(int id, String referralName,int availableAmount, int initialAmount) {
		this.referralId = id;
		this.referralName = checkNotNull(referralName, "name");
		this.availableAmount = availableAmount;
		this.initialAmount = initialAmount;
	}

	public int getId() {
		return referralId;
	}

	public String getName() {
		return referralName;
	}

	public int getAvailableAmount() {
		return availableAmount;
	}

	public int getInitialAmount() {
		return initialAmount;
	}

	public int getReferralId() {
		return referralId;
	}

	public String getReferralName() {
		return referralName;
	}


	@Override
	public int hashCode() {
		return referralId;
	}

	@Override
	public boolean equals(Object object) {
		if (object == this) return true;
		if (object != null && object.getClass() == RefferalReward.class) {
			RefferalReward other = (RefferalReward) object;
			return other.referralId == this.referralId;
		}
		return false;
	}

	@Override
	public String toString() {
		return RefferalReward.class.getSimpleName() + "{id=" + referralId + "}";
	}

}
