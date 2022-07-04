package com.cwdegidio.mybank.context;

import com.cwdegidio.mybank.service.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class Application {

    public static final TransactionService transactionService = new TransactionService();
    public static final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

}
