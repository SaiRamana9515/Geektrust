package com.example.geektrust.metrocard.entities;

public abstract class PassengerType {
    private final String typeName;
    private final int chargePerTicket;

    public PassengerType(String typeName, int chargePerTicket){
        this.typeName = typeName;
        this.chargePerTicket = chargePerTicket;
    }

     public String getTypeName(){
        return this.typeName;
     }

     public int getChargePerTicket(){
        return this.chargePerTicket;
     }
}
