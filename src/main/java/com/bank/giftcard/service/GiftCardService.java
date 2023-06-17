package com.bank.giftcard.service;

import com.bank.giftcard.config.AccountFeignClient;
import com.bank.giftcard.config.TransactionRequest;
import com.bank.giftcard.controller.GiftCardRequest;
import com.bank.giftcard.controller.GiftCardResponse;
import com.bank.giftcard.exception.ResourceNotFoundException;
import com.bank.giftcard.repository.Account;
import com.bank.giftcard.repository.GiftCardEntity;
import com.bank.giftcard.repository.GiftCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class GiftCardService {
    @Autowired
    private AccountFeignClient accountFeignClient;
    @Autowired
    private GiftCardRepository giftCardRepository;


    public GiftCardResponse purchaseGiftCard(GiftCardRequest request) {
        ResponseEntity<List<Account>> accountByEmail = accountFeignClient.getAccountByEmail(request.getCustomer());
        if (Objects.requireNonNull(accountByEmail.getBody()).isEmpty()) {
            throw new UsernameNotFoundException("User account does not exist. Please register.");
        }
        Account account = accountByEmail
                .getBody()
                .stream().filter(a -> Objects.equals(a.getId(), request.getAccountId()))
                .findFirst().orElseThrow((() -> new ResourceNotFoundException("Account with Id: " + request.getAccountId() + " does not exist.")));

        if (account.getBalance() >= request.getAmount()) {


            // Deduct the amount from the account balance
            accountFeignClient.withdrawBalance(TransactionRequest.builder()
                    .accountId(account.getId()).amount(request.getAmount()).build());

            GiftCardEntity giftCardEntity = new GiftCardEntity();
            giftCardEntity.setCustomerId(request.getCustomer());
            giftCardEntity.setGiftCardType(request.getGiftCardType());
            giftCardEntity.setAmount(request.getAmount());
            giftCardRepository.save(giftCardEntity);

            GiftCardResponse response = new GiftCardResponse();
            response.setMessage("Gift card purchased successfully");
            response.setSuccess(true);
            return response;
        } else {
            throw new IllegalArgumentException("Insufficient balance in the account to purchase the gift card");
        }
    }

    public GiftCardEntity getGiftcardById(Long id) {
        return giftCardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Gift card not available."));
    }
}
