package com.example.efxtechtest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class EfxTechTestApplicationTests {

	@Autowired
	private PriceController controller;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

	@Autowired
	PriceRepository priceRepository;

	@Test
	void getLatestPrice_isEURJPYLatestPrice_returnsCorrectObject() {
		Price price =  priceRepository.getLatestPrice("eur-jpy");
		// 110, EUR/JPY, 119.61, 119.91, 01-06-2020 12:01:02:110
		Price testPrice = new Price (110, "EUR/JPY", 119.61, 119.91, "01-06-2020 12:01:02:110");
		assertEquals(testPrice.getId(),price.getId());
		testPrice.addCommission();
		assertEquals(testPrice.getInstrument(),price.getInstrument());
		assertEquals(testPrice.getBid(),price.getBid());
		assertEquals(testPrice.getAsk(),price.getAsk());
		assertEquals(testPrice.getTimeStamp(),price.getTimeStamp());
	}

	@Test
	void getLatestPrice_isGBPUSDLatestPrice_returnsCorrectObject() {
		Price price =  priceRepository.getLatestPrice("gbp-usd");
		// 109, GBP/USD, 1.2499, 1.2561, 01-06-2020 12:01:02:100
		Price testPrice = new Price (109, "GBP/USD", 1.2499, 1.2561, "01-06-2020 12:01:02:100");
		assertEquals(testPrice.getId(),price.getId());
		testPrice.addCommission();
		assertEquals(testPrice.getInstrument(),price.getInstrument());
		assertEquals(testPrice.getBid(),price.getBid());
		assertEquals(testPrice.getAsk(),price.getAsk());
		assertEquals(testPrice.getTimeStamp(),price.getTimeStamp());
	}

	@Test
	void getLatestPrice_isEURUSDLatestPrice_returnsCorrectObject() {
		Price price =  priceRepository.getLatestPrice("eur-usd");
		// 106, EUR/USD, 1.1000, 1.2000, 01-06-2020 12:01:01:001
		Price testPrice = new Price (106, "EUR/USD", 1.1000, 1.2000, "01-06-2020 12:01:01:001");
		assertEquals(testPrice.getId(),price.getId());
		testPrice.addCommission();
		assertEquals(testPrice.getInstrument(),price.getInstrument());
		assertEquals(testPrice.getBid(),price.getBid());
		assertEquals(testPrice.getAsk(),price.getAsk());
		assertEquals(testPrice.getTimeStamp(),price.getTimeStamp());
	}

	@Test
	void getLatestPrice_isNewEURUSDLatestPrice_returnsCorrectObject() {
		String message = "112, EUR/USD, 1.1500, 1.2500, 01-07-2020 12:01:01:001";
		priceRepository.onMessage(message);
		Price price =  priceRepository.getLatestPrice("eur-usd");
		Price testPrice = new Price (112, "EUR/USD", 1.1500, 1.2500, "01-07-2020 12:01:01:001");
		assertEquals(testPrice.getId(),price.getId());
		testPrice.addCommission();
		assertEquals(testPrice.getInstrument(),price.getInstrument());
		assertEquals(testPrice.getBid(),price.getBid());
		assertEquals(testPrice.getAsk(),price.getAsk());
		assertEquals(testPrice.getTimeStamp(),price.getTimeStamp());
	}
}
