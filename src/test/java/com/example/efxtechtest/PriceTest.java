package com.example.efxtechtest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PriceTest {

    @Test
    public void addCommission_isAdjusted_returnsBidAndAsk () {
        Price testPrice = new Price (1, "EUR/JPY", 10.0, 14.0, "01-06-2020 12:01:03:110");
        testPrice.addCommission();
        assertEquals(10.0*0.99, testPrice.getBid());
        assertEquals(14.0*1.01, testPrice.getAsk());
    }

    @Test
    public void createPrice_isCorrectTimestamp_returnsCorrectType () {
        Price testPrice = new Price (1, "EUR/JPY", 10.0, 14.0, "01-06-2020 12:01:02:110");
        String timestamp = String.valueOf(testPrice.getTimeStamp());
        assertEquals("2020-06-01 12:01:02.11", timestamp);
    }
}