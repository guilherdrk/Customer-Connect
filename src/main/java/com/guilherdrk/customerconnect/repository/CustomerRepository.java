package com.guilherdrk.customerconnect.repository;

import com.guilherdrk.customerconnect.entity.CustomerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    Page<CustomerEntity> findByEmail(String email, PageRequest pageRequest);
    Page<CustomerEntity> findByCpf(String cpf, PageRequest pageRequest);
    Page<CustomerEntity> findByEmailAndCpf(String email, String cpf, PageRequest pageRequest);

}
