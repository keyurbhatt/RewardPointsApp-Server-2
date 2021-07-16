/**
 * 
 */
package com.rewards.utils;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import com.rewards.entities.Customer;
import com.rewards.entities.Sales;

/**
 * @author Keyur Bhatt
 *
 */
public class RewardsUtils {

    /**
     * 
     * @param startDate
     * @param endDate
     * @return
     */
    private static LocalDate returnBetweenDate(LocalDate startDate, LocalDate endDate) {
        long randomDay = ThreadLocalRandom
                .current()
                .nextLong(startDate.toEpochDay(), endDate.toEpochDay());
        return LocalDate.ofEpochDay(randomDay);
    }
    
    /**
     * Generate random dates
     * 
     * @return
     */
    public static LocalDate[] getRandomDates() {
    	LocalDate[] dates = new LocalDate[100];
        for(int i = 0; i < dates.length; i++) {
            dates[i] = returnBetweenDate(LocalDate.now().minusDays(90), LocalDate.now());
        }
        return dates;
    }
    
    /**
     * 
     * @param noOfValuesToBeCreated
     * @param minRange
     * @param maxRange
     * @return
     */
    public static double[] getRandomAmounts(int noOfValuesToBeCreated, int minRange, int maxRange) {
    	List<Double> amtLst = new Random().doubles(noOfValuesToBeCreated, minRange, maxRange).boxed().collect( Collectors.toList());
    	return amtLst.stream().mapToDouble(Double::doubleValue).toArray();
    }
    
    /**
     * 
     * @param customer
     * @param month
     * @return
     */
    public static int calculateTotalPoints(Customer customer, Month month) {
        int totalPoints = 0;
        int points = 0;
        
        for(Sales sale : customer.getPurchases()) {
            if(sale.getDate().getMonth().equals(month)) {
            	points = calculatePoints(sale.getAmount());
            }
            totalPoints += points;
        }
        return totalPoints;
    }
    
    /**
     * 
     * @param amount
     * @return
     */
    public static int calculatePoints(double amount) {
    	int points = 0; 
    	if (amount > RewardConstants.AMOUNT_50) {
            points += Math.floor(amount - RewardConstants.AMOUNT_50);
        }
        if (amount > RewardConstants.AMOUNT_100) {
            points += Math.floor(amount - RewardConstants.AMOUNT_100);
        }
        return points;
    }
    
}