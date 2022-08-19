package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfers;

import java.math.BigDecimal;
import java.util.List;

public interface TransfersDao {

    List<Transfers> listTransfers();

    List<Transfers> listTransfersByUser(int userId);

    Transfers findTransferByTransferId(int transferId);

    List<Transfers> findTransfersByTransferType(int transferTypeId);

    List<Transfers> findTransfersByStatus(int transferStatusId);

    List<Transfers> getSentTransfers (int fromAccountId);

    List<Transfers> getReceivedTransfers (int toAccountId);

    String getFromUsername (int fromAccountId);

    String getToUsername (int toAccountId);

    BigDecimal getTransferAmount (int transferId);

    Integer sendTransfer(int fromUserId, int toUserId, BigDecimal transferAmount);

    //String requestTransfer(int fromUserId, int toUserId, BigDecimal transferAmount);

}
