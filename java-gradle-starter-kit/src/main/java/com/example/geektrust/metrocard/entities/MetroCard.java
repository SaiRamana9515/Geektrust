package com.example.geektrust.metrocard.entities;

import com.example.geektrust.metrocard.exception.InvalidBalanceException;

public class MetroCard {
    private String id;
    private int balanceAmount;

    public MetroCard(String id, int balanceAmount){
        this.id = id;
        this.balanceAmount = balanceAmount;
    }

    public int getBalanceAmount(){
        return this.balanceAmount;
    }

    public void setBalanceAmount(int balanceAmount) {
        this.balanceAmount = balanceAmount;
    }
}
