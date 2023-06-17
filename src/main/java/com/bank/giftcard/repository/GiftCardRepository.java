package com.bank.giftcard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GiftCardRepository extends JpaRepository<GiftCardEntity, Long> {
    // Additional custom query methods, if needed
}
