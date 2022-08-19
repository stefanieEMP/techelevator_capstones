package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfers;
import org.springframework.jdbc.InvalidResultSetAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTransfersDao implements TransfersDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcTransfersDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Transfers> listTransfers() {
        List<Transfers> transfers = new ArrayList<>();
        String sql = "SELECT transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount " +
                "FROM transfer;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            Transfers transfer = mapRowToTransfer(results);
            transfers.add(transfer);
        }
        return transfers;
    }

    @Override
    public List<Transfers> listTransfersByUser(int userId) {
        List<Transfers> transfersList = new ArrayList<>();
        String sql = "SELECT transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount, fromUser.username as username_from, toUser.username as username_to " +
                "FROM transfer " +
                "LEFT JOIN account fromAccount ON fromAccount.account_id = transfer.account_from " +
                "LEFT JOIN tenmo_user fromUser ON fromAccount.user_id = fromUser.user_id " +
                "LEFT JOIN account toAccount ON toAccount.account_id = transfer.account_to " +
                "LEFT JOIN tenmo_user toUser ON toAccount.user_id = toUser.user_id " +
                "WHERE fromUser.user_id = ? OR toUser.user_id = ?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, userId, userId);
        while (rowSet.next()) {
            transfersList.add(mapRowToTransfer(rowSet));
        }
        return transfersList;
    }

    @Override
    public Transfers findTransferByTransferId(int transferId) {
        String sql = "SELECT transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount " +
                "FROM transfer WHERE transfer_id = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, transferId);
        if (!rowSet.next()) {
            return null;
        }
        return mapRowToTransfer(rowSet);
    }

    @Override
    public List<Transfers> findTransfersByTransferType(int transferTypeId) {
        List<Transfers> transfersByType = new ArrayList<>();
        String sql = "SELECT transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount " +
                "FROM transfer WHERE transfer_type_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, transferTypeId);
        while (results.next()) {
            Transfers transfer = mapRowToTransfer(results);
            transfersByType.add(transfer);
        }
        return transfersByType;
    }

    @Override
    public List<Transfers> findTransfersByStatus(int transferStatusId) {
        List<Transfers> transfersByStatus = new ArrayList<>();
        String sql = "SELECT transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount " +
                "FROM transfer WHERE transfer_status_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, transferStatusId);
        while (results.next()) {
            Transfers transfer = mapRowToTransfer(results);
            transfersByStatus.add(transfer);
        }
        return transfersByStatus;
    }

    @Override
    public List<Transfers> getSentTransfers(int fromAccountId) {
        List<Transfers> transfersSentByUser = new ArrayList<>();
        String sql = "SELECT transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount " +
                "FROM transfer WHERE account_from = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, fromAccountId);
        while (results.next()) {
            Transfers transfer = mapRowToTransfer(results);
            transfersSentByUser.add(transfer);
        }
        return transfersSentByUser;
    }

    @Override
    public List<Transfers> getReceivedTransfers(int toAccountId) {
        List<Transfers> transfersReceivedByUser = new ArrayList<>();
        String sql = "SELECT transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount " +
                "FROM transfer WHERE account_to = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, toAccountId);
        while (results.next()) {
            Transfers transfer = mapRowToTransfer(results);
            transfersReceivedByUser.add(transfer);
        }
        return transfersReceivedByUser;
    }

    @Override
    public String getFromUsername (int fromAccountId) {
        String sql = "SELECT username FROM tenmo_user JOIN account ON user_id WHERE account_id = ?;";
        return jdbcTemplate.queryForObject(sql, String.class, fromAccountId);
    }

    @Override
    public String getToUsername (int toAccountId) {
        String sql = "SELECT username FROM tenmo_user JOIN account ON user_id WHERE account_id = ?;";
        return jdbcTemplate.queryForObject(sql, String.class, toAccountId);
    }

    @Override
    public BigDecimal getTransferAmount (int transferId) {
        String sql = "SELECT amount FROM transfer WHERE transfer_id = ?;";
        return jdbcTemplate.queryForObject(sql, BigDecimal.class, transferId);
    }

    @Override
    public Integer sendTransfer(int fromUserId, int toUserId, BigDecimal transferAmount) {
        String sql = "INSERT INTO transfer (transfer_type_id, transfer_status_id, account_from, account_to, amount) VALUES(";
        //transfer_type_id
        sql += "(SELECT transfer_type_id FROM transfer_type WHERE transfer_type_desc = 'Send'), ";
        //transfer_status_id
        sql += "(SELECT transfer_status_id FROM transfer_status WHERE transfer_status_desc = 'Approved'), ";
        sql += "?, ?, ?) RETURNING transfer_id";

        Integer transferId = jdbcTemplate.queryForObject(sql, Integer.class, fromUserId, toUserId, transferAmount);

        sql = "UPDATE account SET balance = balance - ? WHERE account_id = ?";
        jdbcTemplate.update(sql, transferAmount, fromUserId);
        sql = "UPDATE account SET balance = balance + ? WHERE account_id = ?";
        jdbcTemplate.update(sql, transferAmount, toUserId);

        return transferId;
    }

    private Transfers mapRowToTransfer(SqlRowSet rs) {
        Transfers transfer = new Transfers();
        transfer.setTransferId(rs.getInt("transfer_id"));
        transfer.setTransferTypeId(rs.getInt("transfer_type_id"));
        transfer.setTransferStatusId(rs.getInt("transfer_status_id"));
        transfer.setToAccountId(rs.getInt("account_to"));
        transfer.setFromAccountId(rs.getInt("account_from"));
        transfer.setTransferAmount(rs.getBigDecimal("amount"));
        // get usernames if they exist
        try {
            transfer.setFromUsername(rs.getString("username_from"));
            transfer.setToUsername(rs.getString("username_to"));
        } catch (InvalidResultSetAccessException e) {
            // usernames aren't in the results
        }
        return transfer;
    }

}
