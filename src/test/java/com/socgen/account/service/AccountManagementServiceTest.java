package com.socgen.account.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.socgen.account.model.Account;

public class AccountManagementServiceTest {
	private AccountManagementService service;
	final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

	@BeforeEach
	void setUp() {
		System.setIn(new ByteArrayInputStream("000561300N05\n1000\n300".getBytes()));
		service = new AccountManagementService(new Scanner(System.in));
	}

	@Test
	void when_login_then_check_identifier() {
		Account account = service.loginUser();
		Assertions.assertEquals(account.getClientIdentifier(), "000561300N05");
	}

	@Test
	void when_make_a_deposit_then_check_balance_and_transactions() {
		Account account = service.loginUser();
		service.makeDeposit(account);
		Assertions.assertEquals(account.getBalance(), new BigDecimal("1000"));
		Assertions.assertEquals(account.getTransactions().size(), 1);
	}

	@Test
	void when_make_a_withdrawal_then_check_balance_and_transactions() {
		Account account = service.loginUser();
		service.makeDeposit(account);
		service.makeWithdrawal(account);
		Assertions.assertEquals(account.getBalance(), new BigDecimal("700"));
		Assertions.assertEquals(account.getTransactions().size(), 2);
	}

	@Test
	void when_make_a_withdrawal_without_enough_balance_then_check_balance_and_transactions() {
		Account account = service.loginUser();
		service.makeWithdrawal(account);
		Assertions.assertEquals(account.getBalance(), new BigDecimal("0"));
		Assertions.assertEquals(account.getTransactions().size(), 0);
	}

	@Test
	void when_make_a_withdrawal_then_check_transactions() {
		Account account = service.loginUser();
		service.makeDeposit(account);
		service.makeWithdrawal(account);
		service.setOutput(new PrintStream(outputStreamCaptor));
		service.displayTransactions(account);

		for (String type : Arrays.asList("Deposit", "Withdrawal")) {
			Assertions.assertTrue(outputStreamCaptor.toString().contains(type));
		}
	}
}
