package com.bank.giftcard.controller;

import lombok.Data;

@Data
public class GiftCardRequest {
    private String customer;
    private GiftCardType giftCardType;
    private double amount;
    private Long accountId;

    // Constructors, getters, and setters
}
