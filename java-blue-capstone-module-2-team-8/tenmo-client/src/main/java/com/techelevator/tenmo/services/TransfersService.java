package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.Transfers;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class TransfersService {

    private final String baseUrl;
    private final RestTemplate restTemplate = new RestTemplate();

    public TransfersService(String url) {
        this.baseUrl = url;
    }

    //Transfer History
    public List<Transfers> getTransferHistory(AuthenticatedUser currentUser) {
        List<Transfers> transfersList = null;
        try {
            Transfers[] transfers = restTemplate.exchange(baseUrl + "/transfer", HttpMethod.GET, makeAuthEntity(currentUser), Transfers[].class).getBody();
            transfersList = Arrays.asList(transfers);
        } catch (RestClientResponseException e) {
            System.out.println("Error getting transfers: "+e.getMessage());
        }
        return transfersList;
    }

    //Pending Transfer Requests


    //Send
    public Integer sendTransaction(AuthenticatedUser currentUser, int fromAccountId, int toAccountId, BigDecimal amountToSend) {
        Integer transferId = null;

        Transfers transfers = new Transfers();
        transfers.setTransferAmount(amountToSend);
        transfers.setFromAccountId(fromAccountId);
        transfers.setToAccountId(toAccountId);

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(currentUser.getToken());
        HttpEntity<Transfers> entity = new HttpEntity<>(transfers, headers);

        try {
            transferId = restTemplate.exchange(baseUrl+"/transfer/send", HttpMethod.POST, entity, Integer.class).getBody();
        } catch (RestClientResponseException e) {
            System.out.println("An error occurred when sending transfer: "+e.getMessage());
        }

        return transferId;
    }


    //Request


    private HttpEntity<Void> makeAuthEntity(AuthenticatedUser currentUser) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(currentUser.getToken());
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        return entity;
    }
}
