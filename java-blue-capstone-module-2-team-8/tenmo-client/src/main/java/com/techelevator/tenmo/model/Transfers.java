package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Transfers {

    private int toAccountId;
    private int fromAccountId;
    private int transferId;
    private int transferTypeId;
    private int transferStatusId;
    private String toUsername;
    private String fromUsername;
    private BigDecimal transferAmount;

    public int getTransferStatusId() {
        return transferStatusId;
    }

    public void setTransferStatusId(int transferStatusId) {
        this.transferStatusId = transferStatusId;
    }

    public int getToAccountId() {
        return toAccountId;
    }

    public void setToAccountId(int toUserId) {
        this.toAccountId = toUserId;
    }

    public int getFromAccountId() {
        return fromAccountId;
    }

    public void setFromAccountId(int fromUserId) {
        this.fromAccountId = fromUserId;
    }

    public int getTransferId() {
        return transferId;
    }

    public void setTransferId(int transferId) {
        this.transferId = transferId;
    }

    public int getTransferTypeId() {
        return transferTypeId;
    }

    public void setTransferTypeId(int transferTypeId) {
        this.transferTypeId = transferTypeId;
    }

    public String getToUsername() {
        return toUsername;
    }

    public void setToUsername(String toUsername) {
        this.toUsername = toUsername;
    }

    public String getFromUsername() {
        return fromUsername;
    }

    public void setFromUsername(String fromUsername) {
        this.fromUsername = fromUsername;
    }

    public BigDecimal getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(BigDecimal transferAmount) {
        this.transferAmount = transferAmount;
    }
}
