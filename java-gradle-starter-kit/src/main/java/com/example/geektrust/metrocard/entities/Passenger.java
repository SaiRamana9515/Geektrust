package com.example.geektrust.metrocard.entities;

public class Passenger {
    private MetroCard card;
    private PassengerType type;
    private Station lastCheckedInStation;

    public Passenger(MetroCard card, PassengerType type){
        this.card = card;
        this.type = type;
        lastCheckedInStation = null;
    }

    public Station getLastCheckedInStation() {
        return lastCheckedInStation;
    }

    public void setLastCheckedInStation(Station lastCheckedInStation) {
        this.lastCheckedInStation = lastCheckedInStation;
    }

    public MetroCard getCard() {
        return card;
    }

    public PassengerType getType() {
        return type;
    }
}
