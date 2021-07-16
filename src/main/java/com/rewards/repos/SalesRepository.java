package com.rewards.repos;

import org.springframework.data.repository.CrudRepository;

import com.rewards.entities.Sales;

/**
 * 
 * @author Keyur Bhatt
 *
 */
public interface SalesRepository extends CrudRepository<Sales, Long> {
}
