package com.bank.giftcard.repository;

import com.bank.giftcard.controller.GiftCardType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class GiftCardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerId;
    private GiftCardType giftCardType;
    private double amount;

    // Constructors, getters, and setters
}
