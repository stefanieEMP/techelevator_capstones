package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.security.SecurityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountController {
    UserDao userDao;
    AccountDao accountDao;

    public AccountController(UserDao userDao, AccountDao accountDao) {
        this.userDao = userDao;
        this.accountDao = accountDao;
    }
    @GetMapping("/account")
    public Account getAccount() {
        String username = SecurityUtils.getCurrentUsername().orElse(null);
        int id = userDao.findIdByUsername(username);
        return accountDao.findAccountByUserId(id);
    }
    @GetMapping("/accounts")
    public List<Account> getAccounts() {
        return accountDao.listAccounts();
    }
}
