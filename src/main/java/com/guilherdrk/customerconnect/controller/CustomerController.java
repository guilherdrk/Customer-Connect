package com.guilherdrk.customerconnect.controller;

import com.guilherdrk.customerconnect.dto.ApiResponse;
import com.guilherdrk.customerconnect.dto.CreateCustomerDTO;
import com.guilherdrk.customerconnect.dto.PaginationResponse;
import com.guilherdrk.customerconnect.entity.CustomerEntity;
import com.guilherdrk.customerconnect.service.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Void> createCustomer(@RequestBody CreateCustomerDTO createCustomerDTO){

        var costumer = customerService.createCostumer(createCustomerDTO);

        return ResponseEntity.created(URI.create("/customers/" + costumer.getId())).build();
    }

    @GetMapping
    public ResponseEntity<ApiResponse<CustomerEntity>>listAll(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                              @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize,
                                                              @RequestParam(name = "orderBy", defaultValue = "asc") String orderBy,
                                                              @RequestParam(name = "cpf", required = false) String cpf,
                                                              @RequestParam(name = "email", required = false) String email){

        var pageResponse = customerService.listAllCustomer(page, pageSize, orderBy, cpf, email);
        return ResponseEntity.ok(new ApiResponse<>(
                pageResponse.getContent(),
                new PaginationResponse(pageResponse.getNumber(),
                        pageResponse.getSize(),
                        pageResponse.getTotalElements(),
                        pageResponse.getTotalPages()
                )
        ));
    }
}
