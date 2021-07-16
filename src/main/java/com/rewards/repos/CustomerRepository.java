package com.rewards.repos;

import org.springframework.data.repository.CrudRepository;

import com.rewards.entities.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
