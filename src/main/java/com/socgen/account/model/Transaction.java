package com.socgen.account.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {

	private LocalDateTime time;

	private BigDecimal amount;

	private TransactionType type;

	public Transaction(LocalDateTime time, BigDecimal amount, TransactionType type) {
		super();
		this.time = time;
		this.amount = amount;
		this.type = type;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public TransactionType getType() {
		return type;
	}

	public void setType(TransactionType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Transaction [time=" + time + ", amount=" + amount + ", type=" + type.getLabel() + "]";
	}
}
