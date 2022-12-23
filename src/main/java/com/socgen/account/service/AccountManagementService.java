package com.socgen.account.service;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Scanner;

import com.socgen.account.model.Account;
import com.socgen.account.model.Transaction;
import com.socgen.account.model.TransactionType;

public class AccountManagementService {

	private static final String NO_ENOUGH_BALANCE_MESSAGE = "No enough balance to make this withdrawal";
	private static final String INVALID_CHOICE_MESSAGE = "Invalid choice! please restart";
	private static final String ENTER_WITHDRAWAL_AMOUNT_MESSAGE = "Please enter the withdrawal amount";
	private static final String ENTER_DEPOSIT_AMOUNT_MESSAGE = "Please enter the deposit amount";
	private static final String WELCOME_MESSAGE = "Welcome to your Bank!";
	private static final String ENTER_IDENTIFIER_MESSAGE = "Please enter your identifier";
	public static final String OPARATIONS_LIST_MESSAGE = "Please choose the operation : \n1- make a deposit\n2- make a withdrawal\n3- the history of my operations\n4- Exit";

	private Scanner input;
	PrintStream output = System.out;

	public PrintStream getOutput() {
		return output;
	}

	public void setOutput(PrintStream output) {
		this.output = output;
	}

	public AccountManagementService(Scanner input) {
		super();
		this.input = input;
	}

	public Account loginUser() {
		output.println(WELCOME_MESSAGE);
		output.println(ENTER_IDENTIFIER_MESSAGE);
		String identifier = input.nextLine();
		Account account = new Account(); // a new account is created to simulate the retrieved user account from data
											// base.
		account.setClientIdentifier(identifier);
		return account;
	}

	public void displayMenu(Account account) {
		output.println("Account: " + account.getClientIdentifier());
		output.println(OPARATIONS_LIST_MESSAGE);
	}

	public void run(Account account) {
		int operation = 0;
		while (true) {
			operation = input.nextInt();
			switch (operation) {
			case (1):
				makeDeposit(account);
				break;
			case (2):
				makeWithdrawal(account);
				break;
			case (3):
				displayTransactions(account);
				break;
			case (4):
				System.exit(0);
			default:
				output.println(INVALID_CHOICE_MESSAGE);
			}
		}

	}

	public void makeDeposit(Account account) {
		output.println(ENTER_DEPOSIT_AMOUNT_MESSAGE);
		int amount = input.nextInt();
		account.setBalance(account.getBalance().add(new BigDecimal(amount)));
		account.addTransaction(new Transaction(LocalDateTime.now(), new BigDecimal(amount), TransactionType.DEPOSIT));
		output.println("new balance : " + account.getBalance());
	}

	public void makeWithdrawal(Account account) {
		output.println(ENTER_WITHDRAWAL_AMOUNT_MESSAGE);
		int amount = input.nextInt();
		if (new BigDecimal(amount).compareTo(account.getBalance()) <= 0) {// check if there is enough balance on the
																			// account
			account.setBalance(account.getBalance().subtract(new BigDecimal(amount)));
			account.addTransaction(
					new Transaction(LocalDateTime.now(), new BigDecimal(amount), TransactionType.WITHDRAWAL));
		} else {
			output.println(NO_ENOUGH_BALANCE_MESSAGE);
		}
		output.println("new balance : " + account.getBalance());
	}

	public void displayTransactions(Account account) {
		account.getTransactions().forEach(transaction -> output.println("Operation : " + transaction));
	}
}
