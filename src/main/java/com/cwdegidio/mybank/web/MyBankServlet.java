package com.cwdegidio.mybank.web;


import com.cwdegidio.mybank.context.Application;
import com.cwdegidio.mybank.model.Transaction;
import com.cwdegidio.mybank.service.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class MyBankServlet extends HttpServlet {

    private TransactionService transactionService = Application.transactionService;
    private ObjectMapper objectMapper = Application.objectMapper;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getRequestURI().equalsIgnoreCase("/transactions")) {
            BigDecimal amount = BigDecimal.valueOf(Double.parseDouble(request.getParameter("amount")));
            String reference = request.getParameter("reference");

            Transaction transaction = transactionService.create(amount, reference);

            response.setContentType("application/json; charset=UTF-8");
            String json = objectMapper.writeValueAsString(transaction);
            response.getWriter().print(json);
        }
        else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getRequestURI().equalsIgnoreCase("/transactions")) {
            List<Transaction> transactions = transactionService.findAll();
            response.setContentType("application/json; charset=UTF-8");
            response.getWriter().print(objectMapper.writeValueAsString(transactions));
        }
        else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
