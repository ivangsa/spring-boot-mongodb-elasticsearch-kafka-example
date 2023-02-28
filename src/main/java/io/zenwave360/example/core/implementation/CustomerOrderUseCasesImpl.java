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

/** Service Implementation for managing [CustomerOrder]. */
@Service
@Transactional
public class CustomerOrderUseCasesImpl implements CustomerOrderUseCases {

  private final Logger log = LoggerFactory.getLogger(getClass());

  private final CustomerOrderMapper customerOrderMapper = Mappers.getMapper(CustomerOrderMapper.class);
  private final CustomerOrderRepository customerOrderRepository;

  /** Constructor. */
  public CustomerOrderUseCasesImpl(CustomerOrderRepository customerOrderRepository) {
    this.customerOrderRepository = customerOrderRepository;
  }

  // CustomerOrder

  @Override
  public CustomerOrder createCustomerOrder(CustomerOrderInput customerOrderInput) {
    log.debug("Request to save CustomerOrderInput : {}", customerOrderInput);
    var customerOrder = customerOrderRepository.save(customerOrderMapper.update(new CustomerOrder(), customerOrderInput));
    return customerOrder;
  }

  @Override
  public Optional<CustomerOrder> updateCustomerOrder(String id, CustomerOrderInput customerOrderInput) {
    log.debug("Request to update CustomerOrder : {}", customerOrderInput);

    var customerOrder =
        customerOrderRepository
            .findById(id)
            .map(
                existingCustomerOrder -> {
                  return customerOrderMapper.update(existingCustomerOrder, customerOrderInput);
                })
            .map(customerOrderRepository::save);
    return customerOrder;
  }

  @Override
  public Page<CustomerOrder> listCustomerOrders(Pageable pageable) {
    log.debug("Request list of CustomerOrders: {}", pageable);
    return customerOrderRepository.findAll(pageable);
  }

  @Override
  public Page<CustomerOrder> searchCustomerOrders(CustomerOrderSearchCriteria criteria, Pageable pageable) {
    log.debug("Request to search CustomerOrders: {} - {}", criteria, pageable);
    // TODO implement this search by criteria
    return customerOrderRepository.findAll(pageable);
  }

  @Override
  public Optional<CustomerOrder> getCustomerOrder(String id) {
    log.debug("Request to get CustomerOrder : {}", id);
    return customerOrderRepository.findById(id);
  }

  @Override
  public void deleteCustomerOrder(String id) {
    log.debug("Request to delete CustomerOrder : {}", id);
    customerOrderRepository.deleteById(id);
  }
}
