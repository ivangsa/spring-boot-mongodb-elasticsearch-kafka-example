package io.zenwave360.example.core.implementation;

import io.zenwave360.example.core.domain.*;
import io.zenwave360.example.core.implementation.mappers.*;
import io.zenwave360.example.core.inbound.*;
import io.zenwave360.example.core.inbound.dtos.*;
import io.zenwave360.example.core.outbound.mongodb.*;
import io.zenwave360.example.core.outbound.search.*;
import java.util.Optional;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Service Implementation for managing [Customer]. */
@Service
@Transactional
public class CustomerUseCasesImpl implements CustomerUseCases {

  private final Logger log = LoggerFactory.getLogger(getClass());

  private final CustomerMapper customerMapper = Mappers.getMapper(CustomerMapper.class);
  private final CustomerRepository customerRepository;
  private final CustomerSearchRepository customerSearchRepository;

  /** Constructor. */
  public CustomerUseCasesImpl(CustomerRepository customerRepository, CustomerSearchRepository customerSearchRepository) {
    this.customerRepository = customerRepository;
    this.customerSearchRepository = customerSearchRepository;
  }

  // Customer

  @Override
  public Customer createCustomer(CustomerInput customerInput) {
    log.debug("Request to save CustomerInput : {}", customerInput);
    var customer = customerRepository.save(customerMapper.update(new Customer(), customerInput));
    return customer;
  }

  @Override
  public Optional<Customer> updateCustomer(String id, CustomerInput customerInput) {
    log.debug("Request to update Customer : {}", customerInput);

    var customer =
        customerRepository
            .findById(id)
            .map(
                existingCustomer -> {
                  return customerMapper.update(existingCustomer, customerInput);
                })
            .map(customerRepository::save);
    return customer;
  }

  @Override
  public Page<Customer> listCustomers(Pageable pageable) {
    log.debug("Request list of Customers: {}", pageable);
    return customerRepository.findAll(pageable);
  }

  @Override
  public Page<Customer> searchCustomers(CustomerCriteria criteria, Pageable pageable) {
    log.debug("Request to search Customers: {} - {}", criteria, pageable);
    // TODO implement this search by criteria
    return customerRepository.findAll(pageable);
  }

  @Override
  public Optional<Customer> getCustomer(String id) {
    log.debug("Request to get Customer : {}", id);
    return customerRepository.findById(id);
  }

  @Override
  public void deleteCustomer(String id) {
    log.debug("Request to delete Customer : {}", id);
    customerRepository.deleteById(id);
  }
}
