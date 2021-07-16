package com.rewards.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.rewards.entities.Customer;
import com.rewards.entities.Sales;
import com.rewards.repos.CustomerRepository;
import com.rewards.repos.SalesRepository;

import java.time.LocalDate;
import java.time.Month;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SalesControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SalesRepository purchaseRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void getPurchases() throws Exception {
        Customer customer1 = new Customer("Ayoub", "Benzzine");
        Customer customer2 = new Customer("Mariam", "Makhtari");
        Sales purchase1 = new Sales(customer1, LocalDate.of(2021, Month.MARCH, 30), 150);
        Sales purchase2 = new Sales(customer2, LocalDate.of(2021, Month.MARCH, 30), 40);
        Sales purchase3 = new Sales(customer2, LocalDate.of(2021, Month.MARCH, 30), 200);
        customerRepository.deleteAll();
        customerRepository.save(customer1);
        customerRepository.save(customer2);
        purchaseRepository.deleteAll();
        purchaseRepository.save(purchase1);
        purchaseRepository.save(purchase2);
        purchaseRepository.save(purchase3);
        this.mockMvc.perform(get("/purchases")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("150")))
                .andExpect(content().string(containsString("40")))
                .andExpect(content().string(containsString("200")));
    }
}
