package com.example.efxtechtest;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PriceRepository implements PriceMessageInterface {
    private final List<Price> prices = new ArrayList<>();

    // Create
    public void addPrice(Price newPrice) {
        if (checkPriceIsLatest(newPrice)){
            prices.add(newPrice);
        }
    }

    public boolean checkPriceIsLatest(Price newPrice) {
        if (prices.size() > 0){
            for (int i = prices.size()-1; i >= 0; i--) {
                if (prices.get(i).getInstrument().equals(newPrice.getInstrument())){
                    if (prices.get(i).getTimeStamp().compareTo(newPrice.getTimeStamp()) > 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public void onMessage(String message) {
        // split the message into an array of line
        String[] lines = message.split("\\r?\\n");
        // loop through array and split line into sections
        // create Price object and add to prices
        for (String line : lines) {
            try {
                String[] splitLine = line.split(",");

                int id = Integer.parseInt(splitLine[0].trim());
                String instrument = splitLine[1].trim();
                double bid = Double.parseDouble(splitLine[2].trim());
                double ask = Double.parseDouble(splitLine[3].trim());
                String timeStamp = splitLine[4].trim();

                Price tempPrice = new Price(id, instrument, bid, ask, timeStamp);
                tempPrice.addCommission();
                addPrice(tempPrice);
            } catch (Exception e) {
                System.out.println("This price was invalid and didn't log.");
            }
        }
    }

    // Read
    public List<Price> getAllPrices() {
        return prices;
    }

    public Price getLatestPrice(String instrument) {
        for (int i = prices.size()-1; i >= 0; i--) {
            if (prices.get(i).getInstrument().toLowerCase().equals(instrument)) {
                return prices.get(i);
            }
        }
        return null;
    }
}
