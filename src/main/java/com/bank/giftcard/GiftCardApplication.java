package com.bank.giftcard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.bank.giftcard"})
public class GiftCardApplication {

	public static void main(String[] args) {
		SpringApplication.run(GiftCardApplication.class, args);
	}
}
