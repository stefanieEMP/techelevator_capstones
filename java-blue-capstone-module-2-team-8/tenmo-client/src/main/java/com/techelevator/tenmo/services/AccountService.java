package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.AuthenticatedUser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class AccountService {

    private final String baseUrl;
    private final RestTemplate restTemplate = new RestTemplate();

    public AccountService(String url) {
        this.baseUrl = url;
    }

    public BigDecimal getAccountBalance(AuthenticatedUser currentUser) {
        Account account = getAccount(currentUser);
        if( account != null ) {
            return account.getBalance();
        }
        return null;
    }

    public Account getAccount(AuthenticatedUser currentUser) {
        Account account = null;
        try {
            account = restTemplate.exchange(baseUrl + "account", HttpMethod.GET, makeAuthEntity(currentUser), Account.class).getBody();
        } catch (RestClientResponseException e) {
            System.out.println("Error getting account: ("+e.getRawStatusCode()+") "+e.getMessage());
        }
        return account;
    }

    public List<Account> listAccounts(AuthenticatedUser currentUser) {
        List<Account> accountList = null;
        try {
            Account[] accounts = restTemplate.exchange(baseUrl + "accounts", HttpMethod.GET, makeAuthEntity(currentUser), Account[].class).getBody();
            accountList = Arrays.asList(accounts);
        } catch (RestClientResponseException e) {
            System.out.println("Error getting account: "+e.getMessage());
        }
        return accountList;
    }

    private HttpEntity<Void> makeAuthEntity(AuthenticatedUser currentUser) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(currentUser.getToken());
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        return entity;
    }
}
