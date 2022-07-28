package com.example.efxtechtest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Component
public class PriceInitialiser {
    private final PriceRepository priceRepository;

    @Value("classpath:dummydata.csv")
    private Resource resourceFile;

    @Autowired
    public PriceInitialiser(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }


    // "C:\\Users\\USER\\Documents\\Development\\Tech-Tests\\Santander\\efx-tech-test\\src\\main\\resources\\dummyData.csv"
    @PostConstruct
    public void init() throws IOException {
        BufferedReader sc = new BufferedReader(new FileReader(resourceFile.getFile()));
        String line = null;
        while ((line = sc.readLine()) != null) {
            priceRepository.onMessage(line);
        }
    }
}
