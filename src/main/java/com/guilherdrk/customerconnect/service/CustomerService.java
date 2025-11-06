package com.guilherdrk.customerconnect.service;

import ch.qos.logback.core.util.StringUtil;
import com.guilherdrk.customerconnect.dto.CreateCustomerDTO;
import com.guilherdrk.customerconnect.dto.UpdateCustomerDTO;
import com.guilherdrk.customerconnect.entity.CustomerEntity;
import com.guilherdrk.customerconnect.repository.CustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Optional;

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

    public Page<CustomerEntity> listAllCustomer(Integer page,
                                                Integer pageSize,
                                                String orderBy, String cpf, String email){

        PageRequest pageRequest = getPageRequest(page, pageSize, orderBy);

        return findWithFilter(cpf, email, pageRequest);
    }


    public Optional<CustomerEntity> findCustomById(Long id){
        var customer = customerRepository.findById(id);
        return customer;

    }

    public Optional<CustomerEntity> updateCustomer(Long id, UpdateCustomerDTO dto){
        var entity = customerRepository.findById(id);
        if (entity.isPresent()){
            updateFields(dto, entity);
            customerRepository.save(entity.get());
        }
        return entity;
    }

    private static void updateFields(UpdateCustomerDTO dto, Optional<CustomerEntity> entity) {
        if (StringUtils.hasText(dto.fullName())){
            entity.get().setFullName(dto.fullName());
        }
        if (StringUtils.hasText(dto.email())){
            entity.get().setEmail(dto.email());
        }
        if (StringUtils.hasText(dto.phoneNumber())){
            entity.get().setPhoneNumber(dto.phoneNumber());
        }

        entity.get().setCreateAt(LocalDateTime.now());
    }


    private Page<CustomerEntity> findWithFilter(String cpf, String email, PageRequest pageRequest) {
        if(StringUtils.hasText(email) && StringUtils.hasText(cpf)){
            return customerRepository.findByEmailAndCpf(email, cpf, pageRequest);
        }
        if (StringUtils.hasText(email)){
            return customerRepository.findByEmail(email, pageRequest);
        }
        if (StringUtils.hasText(cpf)){
            return customerRepository.findByCpf(cpf, pageRequest);
        }

        return customerRepository.findAll(pageRequest);
    }

    private PageRequest getPageRequest(Integer page, Integer pageSize, String orderBy) {
        var direction = Sort.Direction.DESC;
        if(orderBy.equalsIgnoreCase("asc")){
            direction = Sort.Direction.ASC;
        }
        PageRequest pageRequest = PageRequest.of(page, pageSize, direction, "createAt");
        return pageRequest;
    }

}
