package com.guilherdrk.customerconnect.repository;

import com.guilherdrk.customerconnect.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
}
