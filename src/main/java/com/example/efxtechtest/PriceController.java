package com.example.efxtechtest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PriceController {

    @Autowired
    PriceRepository priceRepository;

    @GetMapping("/prices")
    public ResponseEntity<List<Price>> getPrices() {
        List<Price> prices = priceRepository.getAllPrices();
        return ResponseEntity.status(HttpStatus.FOUND).body(prices);
    }

    @GetMapping("/prices/{instrument}")
    public ResponseEntity<Price> getLatestPrice(@PathVariable String instrument) {
        Price price = priceRepository.getLatestPrice(instrument);
        System.out.println(price);
        return ResponseEntity.status(HttpStatus.FOUND).body(price);
    }
}