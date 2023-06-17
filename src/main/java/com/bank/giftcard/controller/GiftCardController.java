package com.bank.giftcard.controller;

import com.bank.giftcard.repository.GiftCardEntity;
import com.bank.giftcard.service.GiftCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gift-cards")
public class GiftCardController {
    @Autowired
    private GiftCardService giftCardService;

    @PostMapping("/purchase")
    public ResponseEntity<GiftCardResponse> purchaseGiftCard(
            @RequestBody GiftCardRequest giftCardRequest) {
        GiftCardResponse response = giftCardService.purchaseGiftCard(giftCardRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GiftCardEntity> getGiftCardById(
            @PathVariable Long id) {
        GiftCardEntity response = giftCardService.getGiftcardById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
