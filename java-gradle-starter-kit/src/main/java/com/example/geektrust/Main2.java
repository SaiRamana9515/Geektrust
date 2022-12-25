package com.example.geektrust;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) throws FileNotFoundException {
        /*
        Sample code to read from file passed as command line argument
        try {
            // the file to be opened for reading
            FileInputStream fis = new FileInputStream(args[0]);
            Scanner sc = new Scanner(fis); // file to be scanned
            // returns true if there is another line to read
            while (sc.hasNextLine()) {
               //Add your code here to process input commands
            }
            sc.close(); // closes the scanner
        } catch (IOException e) {
        }
        */
        try {
            FileInputStream fis = new FileInputStream(args[0]);
            Scanner sc = new Scanner(fis);
            HashMap<String,Integer> balanceMap = new HashMap<>();
            //HashMap<String,Integer> ticketPriceMap = getTicketPriceMap();
            HashMap<String,String> lastCheckinMap = new HashMap<>();
            HashMap<String,Integer> airportPassengerCountMap = new HashMap<>();
            HashMap<String,Integer> airportCollectionCountMap = new HashMap<>();
            HashMap<String,Integer> centralPassengerCountMap = new HashMap<>();
            HashMap<String,Integer> centralCollectionCountMap = new HashMap<>();
            HashMap<String,Integer> discountMap = new HashMap<>();
            //int serviceFee=2;
            while(sc.hasNext()){
                String strArray[] = (sc.nextLine()).split(" ");
                if(strArray[0] == "BALANCE"){
                    updateBalanceSheet(balanceMap,strArray);
                }else if(strArray[0] == "CHECK_IN"){
                    String type=strArray[2], station=strArray[3];
                    int discount = 0;
                    if(station == "AIRPORT") {
                        discount = updateCheck_InDetails(strArray, balanceMap, lastCheckinMap, airportCollectionCountMap);
                        airportPassengerCountMap.put(type,airportPassengerCountMap.getOrDefault(type,0) + 1);
                    }else{
                        discount = updateCheck_InDetails(strArray, balanceMap, lastCheckinMap, centralCollectionCountMap);
                        centralPassengerCountMap.put(type,centralPassengerCountMap.getOrDefault(type,0) + 1);
                    }
                    discountMap.put(station,discountMap.getOrDefault(station,0) + discount);
                }else{
                    System.out.println("TOTAL_COLLECTION"+" "+"CENTRAL"+" "+getTotalCollection(centralCollectionCountMap)+" "+discountMap.get("CENTRAL"));
                    printPassengerSummary(centralPassengerCountMap, centralCollectionCountMap);
                    System.out.println("TOTAL_COLLECTION"+" "+"AIRPORT"+" "+getTotalCollection(airportCollectionCountMap)+" "+discountMap.get("AIRPORT"));
                    printPassengerSummary(airportPassengerCountMap, airportCollectionCountMap);
                }
            }
            sc.close();
        } catch (IOException e) {
            System.out.println("FILE NOT FOUND");
        }
	}
    public static HashMap<String,Integer> getTicketPriceMap(){
        HashMap<String,Integer> ticketPriceMap = new HashMap<>();
        ticketPriceMap.put("ADULT",200);
        ticketPriceMap.put("SENIOR_CITIZEN",100);
        ticketPriceMap.put("KID", 50);
        return ticketPriceMap;
    }
    public static void updateBalanceSheet(HashMap<String,Integer> balanceMap, String[] arr){
        String metroCardNumber = arr[1];
        int balance = Integer.parseInt(arr[2]);
        balanceMap.put(metroCardNumber,balance);
    }
    public static int updateCheck_InDetails(String[] arr,HashMap<String,Integer> balanceMap,HashMap<String,String> lastCheckinMap,HashMap<String,Integer> collectionCountMap){
        HashMap<String,Integer> ticketPriceMap = getTicketPriceMap();
        String id= arr[1], type=arr[2], station=arr[3];
        int charges=0, totalCharges=0, discount=0, serviceFee=2, ticketPrice= ticketPriceMap.get(type);
        if(lastCheckinMap.containsKey(id) && lastCheckinMap.get(id) != station){
            discount = ticketPrice/2;
            lastCheckinMap.remove(id);
            charges = ticketPrice - discount;
        }else if(lastCheckinMap.containsKey(id)){
            charges = ticketPrice;
        }else{
            charges = ticketPrice;
            lastCheckinMap.put(id,station);
        }
        if(charges <= balanceMap.get(id)){
            balanceMap.replace(id,balanceMap.get(id) - charges);
            totalCharges = charges;
        }else{
            totalCharges = charges + ((charges-balanceMap.get(id))*2)/100;
            balanceMap.replace(id,0);
        }
        collectionCountMap.put(type,collectionCountMap.getOrDefault(type,0)+totalCharges);
        return discount;
    }
    public static int getTotalCollection(HashMap<String,Integer> collectionCountMap) {
        int totalCollection = 0;
        for (Map.Entry<String, Integer> entry : collectionCountMap.entrySet()) {
            totalCollection += entry.getValue();
        }
        return totalCollection;
    }
    public static void printPassengerSummary(HashMap<String,Integer> passengerCountMap, HashMap<String,Integer> collectionCountMap){

    }
}
