package com.rewards;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.rewards.entities.Customer;
import com.rewards.entities.Sales;
import com.rewards.repos.CustomerRepository;
import com.rewards.repos.SalesRepository;
import com.rewards.utils.RewardConstants;
import com.rewards.utils.RewardsUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Keyur Bhatt
 *
 */
@SpringBootApplication
public class RewardsProgramApp {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private SalesRepository purchaseRepository;

    public static void main(String[] args) {
        SpringApplication.run(RewardsProgramApp.class, args);
    }

    /**
     * 
     * @return
     */
    @Bean
    CommandLineRunner runner() {
        return args -> {
           
            //saving the customers to db
            List<Customer> customers = saveAndRetrieveCustomers(RewardConstants.F_NAMES, RewardConstants.L_NAMES);

            //saving the sales to db
            saveSales(customers);
        };
    }

    /**
     * Save the customers
     * 
     * @param firstNames
     * @param lasttNames
     * @return
     */
    private List<Customer> saveAndRetrieveCustomers(String[] firstNames, String[] lasttNames) {
    	
    	List<Customer> customers = new ArrayList<>();
        for(int i = 0; i < firstNames.length; i++){
            Customer customer = new Customer(firstNames[i], lasttNames[i]);
            customers.add(customer);
            customerRepository.save(customer);
        }
        return customers;
    }
    
    /**
     * Save all the sales
     * 
     * @param customers
     */
    private void saveSales(List<Customer> customers) {
    	LocalDate[] dates = RewardsUtils.getRandomDates();
    	double[] amounts = RewardsUtils.getRandomAmounts(100, 5, 200);
    	
        // Saving 100 purchases to the database
        for(int i = 0; i < amounts.length; i++){
            int customerIndex = (int) (Math.random() * 50);
            Sales sale = new Sales(customers.get(customerIndex), dates[i], amounts[i]);
            purchaseRepository.save(sale);
        }
    }
}