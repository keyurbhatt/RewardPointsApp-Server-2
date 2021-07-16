package com.rewards.beans;

/**
 * 
 * @author Keyur Bhatt
 *
 */
public class CustomerBean {

    private String firstName;
    private String lastName;
    private int numberPurchases;
    private int firstMonthPts;
    private int secondMonthPts;
    private int thirdMonthPts;

    public CustomerBean(String firstName, String lastName, int numberPurchases, int firstMonthPts,
                       int secondMonthPts, int thirdMonthPts) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.numberPurchases = numberPurchases;
        this.firstMonthPts = firstMonthPts;
        this.secondMonthPts = secondMonthPts;
        this.thirdMonthPts = thirdMonthPts;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getNumberPurchases() {
        return numberPurchases;
    }

    public void setNumberPurchases(int numberPurchases) {
        this.numberPurchases = numberPurchases;
    }

    public int getJanuaryPoints() {
        return firstMonthPts;
    }

    public void setJanuaryPoints(int januaryPoints) {
        this.firstMonthPts = januaryPoints;
    }

    public int getFebruaryPoints() {
        return secondMonthPts;
    }

    public void setFebruaryPoints(int februaryPoints) {
        this.secondMonthPts = februaryPoints;
    }

    public int getMarchPoints() {
        return thirdMonthPts;
    }

    public void setMarchPoints(int marchPoints) {
        this.thirdMonthPts = marchPoints;
    }
}
