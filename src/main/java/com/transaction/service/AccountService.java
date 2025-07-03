package com.transaction.service;

import com.transaction.entity.ReceiverAccount;
import com.transaction.entity.SenderAccount;
import com.transaction.repository.ReceiverRepo;
import com.transaction.repository.SenderRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private SenderRepo senderRepo;
    private ReceiverRepo receiverRepo;

    public AccountService(SenderRepo senderRepo, ReceiverRepo receiverRepo) {
        this.senderRepo = senderRepo;
        this.receiverRepo = receiverRepo;
        
    }

    public SenderAccount saveSenderAccount(SenderAccount senderAccount) {
        return senderRepo.save(senderAccount);
    }

    // Save receiver account
    public ReceiverAccount saveReceiverAccount(ReceiverAccount receiverAccount) {
        return receiverRepo.save(receiverAccount);
    }

    @Transactional
    public void transferMoney(String from, String to, Double amount){
        SenderAccount senderAccount = senderRepo.findByName(from)
                .orElseThrow(() -> new RuntimeException("Sender not found: " + from));

        ReceiverAccount receiverAccount = receiverRepo.findByName(to)
                .orElseThrow(() -> new RuntimeException("Receiver not found " + to));

        if (senderAccount.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance for sender: " + from);
        }


        senderAccount.setBalance(senderAccount.getBalance() - amount);
        receiverAccount.setBalance(receiverAccount.getBalance() + amount);

        senderRepo.save(senderAccount);
        receiverRepo.save(receiverAccount);
    }
    }

