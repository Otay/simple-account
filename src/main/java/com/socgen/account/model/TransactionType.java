package com.socgen.account.model;

public enum TransactionType {

	DEPOSIT("Deposit"), WITHDRAWAL("Withdrawal");

	public final String label;

	public String getLabel() {
		return label;
	}

	TransactionType(String label) {
		this.label = label;
	}

}
