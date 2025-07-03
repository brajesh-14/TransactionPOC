package com.transaction.controller;

import com.transaction.dto.AccountDto;
import com.transaction.entity.ReceiverAccount;
import com.transaction.entity.SenderAccount;
import com.transaction.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    private final AccountService accountService;

    @Autowired
    public TransactionController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/sender")
    public SenderAccount createSender(@RequestBody SenderAccount senderAccount) {
        return accountService.saveSenderAccount(senderAccount);
    }

    // Create Receiver Account
    @PostMapping("/receiver")
    public ReceiverAccount createReceiver(@RequestBody ReceiverAccount receiverAccount) {
        return accountService.saveReceiverAccount(receiverAccount);
    }

    @PostMapping("/transfer")
    public String transferMoney(@RequestBody AccountDto dto) {
        accountService.transferMoney(dto.getFrom(), dto.getTo(), dto.getBalance());
        return "Transfer successful from " + dto.getFrom() + " to " + dto.getTo();
    }
}
