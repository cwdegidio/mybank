package com.cwdegidio.mybank.service;

import com.cwdegidio.mybank.model.Transaction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class TransactionService {
    private List<Transaction> transactions = new CopyOnWriteArrayList<>();
    private final String bankSlogan;

    public TransactionService(@Value("${bank.slogan}") String bankSlogan) {
        this.bankSlogan = bankSlogan;
    }

    public Transaction create(BigDecimal amount, String reference) {
        String id = UUID.randomUUID().toString();
        ZonedDateTime timestamp = ZonedDateTime.now();
        Transaction transaction = new Transaction(id, timestamp, amount, bankSlogan + " " + reference);
        transactions.add(transaction);
        return transaction;
    }

    public List<Transaction> findAll() {
        return transactions;
    }
}
