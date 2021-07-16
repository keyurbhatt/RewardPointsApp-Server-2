package com.rewards.controllers;

import com.rewards.beans.SalesBean;
import com.rewards.entities.Sales;
import com.rewards.repos.SalesRepository;
import com.rewards.utils.RewardsUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SalesController {

    @Autowired
    private SalesRepository repository;

    /**
     * 
     * @return
     */
    @RequestMapping(value = "/sales", method = RequestMethod.GET)
    public List<SalesBean> getSales() {

        List<Sales> salesFromDB = new ArrayList<>();
        repository.findAll().forEach(salesFromDB::add);
        
        List<SalesBean> salesLst = new ArrayList<>();
        SalesBean salesBean = null;
        for(Sales sale : salesFromDB) {
        	int points = 0;
            points = RewardsUtils.calculatePoints(sale.getAmount());
            salesBean = new SalesBean( sale.getCustomer().getFirstName(), sale.getCustomer().getLastName(), 
            		sale.getDate(), sale.getAmount(), points);
            salesLst.add(salesBean);
        }
        return salesLst;
    }
}
