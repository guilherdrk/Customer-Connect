package com.guilherdrk.customerconnect.service;

import com.guilherdrk.customerconnect.dto.CreateCustomerDTO;
import com.guilherdrk.customerconnect.entity.CustomerEntity;
import com.guilherdrk.customerconnect.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerEntity createCostumer(CreateCustomerDTO createCustomerDTO){
        var costumer = new CustomerEntity();
        costumer.setFullName(createCustomerDTO.fullName());
        costumer.setCpf(createCustomerDTO.cpf());
        costumer.setEmail(createCustomerDTO.email());
        costumer.setPhoneNumber(createCustomerDTO.phoneNumber());
        costumer.setCreateAt(LocalDateTime.now());

        return customerRepository.save(costumer);

    }

}
