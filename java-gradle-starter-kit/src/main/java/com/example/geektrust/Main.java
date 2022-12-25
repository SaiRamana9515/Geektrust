package com.example.geektrust;

import com.example.geektrust.metrocard.entities.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Printing...");
        try {
            FileInputStream fis = new FileInputStream(args[0]);
            Scanner sc = new Scanner(fis);
            HashMap<String, Passenger> passengersByCard = new HashMap<>();
            HashMap<String, MetroCard> metroCardById = new HashMap<>();
            HashMap<String, PassengerType> passengerTypeByName = new HashMap<>();
            passengerTypeByName.put("ADULT", new Adult());
            passengerTypeByName.put("KID", new Kid());
            passengerTypeByName.put("SENIOR_CITIZEN", new SeniorCitizen());
            HashMap<String, Station> stationsByName = new HashMap<>();
            stationsByName.put("AIRPORT", new Station("AIRPORT"));
            stationsByName.put("CENTRAL", new Station("CENTRAL"));

            //int serviceFee=2;
            while (sc.hasNext()) {
                String strArray[] = (sc.nextLine()).split(" ");
                if (strArray[0].equals("BALANCE")) {
                    MetroCard metroCard = new MetroCard(strArray[1], Integer.parseInt(strArray[2]));
                    metroCardById.put(strArray[1], metroCard);
                } else if (strArray[0].equals("CHECK_IN")) {
                    MetroCard metroCard = metroCardById.get(strArray[1]);
                    PassengerType passengerType = passengerTypeByName.get(strArray[2]);
                    Passenger passenger = new Passenger(metroCard, passengerType);
                    Station station = stationsByName.get(strArray[3]);
                    station.checkIn(passenger);

                } else if(strArray[0].equals("PRINT_SUMMARY")){
                    Station station = stationsByName.get("CENTRAL");
                    station.printSummary();
                    station = stationsByName.get("AIRPORT");
                    station.printSummary();
                }
            }
            sc.close();
        } catch (IOException e) {
            System.out.println("FILE NOT FOUND");
        }
    }
}
