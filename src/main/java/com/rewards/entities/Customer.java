package com.rewards.entities;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * 
 * @author Keyur Bhatt
 *
 */
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String fName;

    @Column(nullable = false)
    private String lName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private List<Sales> purchases;

    public Customer() {}

    public Customer(String firstName, String lastName) {
        this.fName = firstName;
        this.lName = lastName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return fName;
    }

    public void setFirstName(String firstName) {
        this.fName = firstName;
    }

    public String getLastName() {
        return lName;
    }

    public void setLastName(String lastName) {
        this.lName = lastName;
    }

    public List<Sales> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Sales> purchases) {
        this.purchases = purchases;
    }
}
