package com.cwdegidio.mybank.service;

import com.cwdegidio.mybank.model.Transaction;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public class TransactionService {
    private List<Transaction> transactions = new CopyOnWriteArrayList<>();

    public Transaction create(BigDecimal amount, String reference) {
        String id = UUID.randomUUID().toString();
        ZonedDateTime timestamp = ZonedDateTime.now();
        Transaction transaction = new Transaction(id, timestamp, amount, reference);
        transactions.add(transaction);
        return transaction;
    }

    public List<Transaction> findAll() {
        return transactions;
    }
}
