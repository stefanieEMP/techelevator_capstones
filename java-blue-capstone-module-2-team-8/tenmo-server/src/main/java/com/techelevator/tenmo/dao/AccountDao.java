package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

import java.math.BigDecimal;
import java.util.List;

public interface AccountDao {

    List<Account> listAccounts();

    Account findAccountByUserId(int userId);

    BigDecimal getAccountBalance(int accountId);
}
