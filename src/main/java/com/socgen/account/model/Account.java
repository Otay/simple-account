package com.socgen.account.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Account {

	private String clientIdentifier;

	private BigDecimal balance;

	private List<Transaction> transactions = new ArrayList<>();

	public Account() {
		balance = new BigDecimal(0.0);
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void addTransaction(Transaction transaction) {
		this.transactions.add(transaction);
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getClientIdentifier() {
		return clientIdentifier;
	}

	public void setClientIdentifier(String clientIdentifier) {
		this.clientIdentifier = clientIdentifier;
	}

}
