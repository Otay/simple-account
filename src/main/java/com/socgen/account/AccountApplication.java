package com.socgen.account;

import java.util.Scanner;

import com.socgen.account.model.Account;
import com.socgen.account.service.AccountManagementService;

public class AccountApplication {

	public static void main(String[] args) {

		AccountManagementService service = new AccountManagementService(new Scanner(System.in));
		Account account = service.loginUser();
		service.displayMenu(account);
		service.run(account);
	}

}
