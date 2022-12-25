package com.example.geektrust.metrocard.entities;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Station {
    private final String name;
    private int totalAmountCollected;
    private int totalDiscountGiven;

    private final Map<String, PassengerSummary> passengerSummaryByTypeName;


    public Station(String name){
        this.name = name;
        this.passengerSummaryByTypeName = new HashMap<>();
        passengerSummaryByTypeName.put("ADULT", new PassengerSummary("ADULT"));
        passengerSummaryByTypeName.put("KID", new PassengerSummary("KID"));
        passengerSummaryByTypeName.put("SENIOR_CITIZEN", new PassengerSummary("SENIOR_CITIZEN"));
    }

    public void checkIn(Passenger passenger) {
        int charge = chargePassenger(passenger);
        checkInPassenger(passenger);
        updatePassengerSummary(passenger, charge);
    }

    public void printSummary(){
        printStationSummary();
        printPassengerSummary();
    }

    private void printStationSummary(){
        System.out.println("TOTAL_COLLECTION"+" "+name.toUpperCase()+" "+totalAmountCollected+" "+totalDiscountGiven);
    }

    private void printPassengerSummary(){
        List<PassengerSummary> passengerSummaries = passengerSummaryByTypeName.values().stream().collect(Collectors.toList());
        for(PassengerSummary passengerSummary : passengerSummaries){
            System.out.println(passengerSummary.getType() + " " + passengerSummary.getNumberOfPassengers());
        }
    }

    private int chargePassenger(Passenger passenger) {
        int charge = calculateCharges(passenger);
        int availableBalance = passenger.getCard().getBalanceAmount();
        if(availableBalance >= charge){
            passenger.getCard().setBalanceAmount(availableBalance-charge);
        }else{
            charge = charge + ((charge - availableBalance)*2/100);
            passenger.getCard().setBalanceAmount(0);
        }
        totalAmountCollected += charge;
        return charge;
    }

    private void checkInPassenger(Passenger passenger){
        passenger.setLastCheckedInStation(this);
    }

    private void updatePassengerSummary(Passenger passenger, int charge){
        String passengerTypeName = passenger.getType().getTypeName();
        PassengerSummary passengerSummary = passengerSummaryByTypeName.get(passengerTypeName);
        int passengerCount = passengerSummary.getNumberOfPassengers()+1;
        int totalCharge = passengerSummary.getTotalAmountCharged() + charge;
        passengerSummary.setNumberOfPassengers(passengerCount);
        passengerSummary.setTotalAmountCharged(totalCharge);
    }

    private int calculateCharges(Passenger passenger){
        int totalTicketPrice = passenger.getType().getChargePerTicket();
        int discount = calculateDiscount(passenger);
        int netCharge = totalTicketPrice - discount;
        return netCharge;
    }

    private int calculateDiscount(Passenger passenger){
        if(passenger.getLastCheckedInStation() == null || (passenger.getLastCheckedInStation().name).equals(this.name)){
            return 0;
        }

        int discount = passenger.getType().getChargePerTicket()/2;
        totalDiscountGiven += discount;
        return discount;
    }
}
