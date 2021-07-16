package com.rewards.controllers;

import com.rewards.beans.CustomerBean;
import com.rewards.entities.Customer;
import com.rewards.repos.CustomerRepository;
import com.rewards.utils.RewardsUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Keyur Bhatt
 *
 */
@RestController
public class CustomerController {

    @Autowired
    private CustomerRepository repository;

    /**
     * 
     * @return
     */
    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public List<CustomerBean> getCustomers() {

        List<Customer> customersfromDB = new ArrayList<>();
        
        repository.findAll().forEach(customersfromDB::add);
        List<CustomerBean> customerLst = new ArrayList<>();
        
        int numberPurchases = 0;
        int firstMonthPoints = 0;
        int secondMonthPoints = 0;
        int ThirdMonthPoints = 0;
        LocalDate localDate = LocalDate.now();
        CustomerBean customerBean = null;
        for(Customer customer : customersfromDB) {
            numberPurchases = customer.getPurchases().size();
            firstMonthPoints = RewardsUtils.calculateTotalPoints(customer, localDate.getMonth());
            secondMonthPoints = RewardsUtils.calculateTotalPoints(customer, localDate.minusDays(30).getMonth());
            ThirdMonthPoints = RewardsUtils.calculateTotalPoints(customer, localDate.minusDays(60).getMonth());
            
            customerBean = new CustomerBean(customer.getFirstName(), customer.getLastName(), 
            		numberPurchases, firstMonthPoints, secondMonthPoints, ThirdMonthPoints);
            customerLst.add(customerBean);
        }
        return customerLst;
    }
}
