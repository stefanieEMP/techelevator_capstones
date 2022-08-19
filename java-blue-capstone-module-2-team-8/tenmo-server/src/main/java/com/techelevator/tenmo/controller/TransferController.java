package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransfersDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfers;
import com.techelevator.tenmo.security.SecurityUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// preAuth here
@RequestMapping("/transfer")
public class TransferController {
    // these should be private
    UserDao userDao;
    AccountDao accountDao;
    TransfersDao transfersDao;

    public TransferController(UserDao userDao, AccountDao accountDao, TransfersDao transfersDao) {
        this.userDao = userDao;
        this.accountDao = accountDao;
        this.transfersDao = transfersDao;
    }

    @GetMapping("/sent")
    public List<Transfers> getSentTransfer() {
        String username = SecurityUtils.getCurrentUsername().orElse(null);
        int userId = userDao.findIdByUsername(username);
        Account account = accountDao.findAccountByUserId(userId);
        return transfersDao.getSentTransfers(account.getId());
    }
    //In AccountDao interface, should Account findAccountByUserId be int findAccountByUserId?

    @GetMapping("/received")
    public List<Transfers> getReceivedTransfer() {
        String username = SecurityUtils.getCurrentUsername().orElse(null);
        int userId = userDao.findIdByUsername(username);
        Account account = accountDao.findAccountByUserId(userId);
        return transfersDao.getReceivedTransfers(account.getId());
    }
    //should I just make a sent and received method? or is that too complicated? when returned in client, they are
    //looking for transfer history which I assume means sent and received

    @GetMapping
    public List<Transfers> getTransfers() {
        String username = SecurityUtils.getCurrentUsername().orElse(null);
        int userId = userDao.findIdByUsername(username);
        return transfersDao.listTransfersByUser(userId);
    }

    @PostMapping("/send")
    // @valid here before @RequestBody
    public Integer processSendTransaction(@RequestBody Transfers transfer) {
        return transfersDao.sendTransfer(transfer.getFromAccountId(), transfer.getToAccountId(), transfer.getTransferAmount());
    }
}
