package com.guilherdrk.customerconnect.controller;

import com.guilherdrk.customerconnect.dto.CreateCustomerDTO;
import com.guilherdrk.customerconnect.entity.CustomerEntity;
import com.guilherdrk.customerconnect.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(path = "/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    private ResponseEntity<Long> createCustomer(@RequestBody CreateCustomerDTO createCustomerDTO){

        var costumer = customerService.createCostumer(createCustomerDTO);

        return ResponseEntity.created(URI.create("/customers/" + costumer.getId())).build();
    }
}
