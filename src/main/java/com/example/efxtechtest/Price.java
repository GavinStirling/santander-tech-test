package com.example.efxtechtest;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Price {
    private static final double BID_COMMISSION = 0.99;
    private static final double ASK_COMMISSION = 1.01;

    private int id;
    private String instrument;
    private double bid;
    private double ask;
    private Timestamp timeStamp;

    public Price (int id, String instrument, double bid, double ask, String timestampString) {
        this.id = id;
        this.instrument = instrument.replace("/","-");
        this.bid = bid;
        this.ask = ask;

        String dateTime = timestampString;
        DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss:SSS");
        LocalDateTime localDateTime = LocalDateTime.from(formatDateTime.parse(dateTime));
        Timestamp timestamp = Timestamp.valueOf(localDateTime);

        this.timeStamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    public double getBid() {
        return bid;
    }

    public void setBid(double bid) {
        this.bid = bid;
    }

    public double getAsk() {
        return ask;
    }

    public void setAsk(double ask) {
        this.ask = ask;
    }

    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void addCommission() {
        this.bid = this.bid * BID_COMMISSION;
        this.ask = this.ask * ASK_COMMISSION;
    }
}
