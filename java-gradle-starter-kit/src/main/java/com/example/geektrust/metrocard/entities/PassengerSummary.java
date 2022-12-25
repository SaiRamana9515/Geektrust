package com.example.geektrust.metrocard.entities;

public class PassengerSummary {
    private final String type;
    private int totalAmountCharged;
    private int numberOfPassengers;

    public PassengerSummary(String type) {
        this.type = type;
        this.totalAmountCharged = 0;
        this.numberOfPassengers = 0;
    }

    public String getType() {
        return type;
    }

    public int getTotalAmountCharged() {
        return totalAmountCharged;
    }

    public void setTotalAmountCharged(int totalAmountCharged) {
        this.totalAmountCharged = totalAmountCharged;
    }

    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }
}
