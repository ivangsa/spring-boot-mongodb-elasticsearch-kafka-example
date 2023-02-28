package io.zenwave360.example.infrastructure.mongodb;

import io.zenwave360.example.core.domain.*;
import io.zenwave360.example.core.outbound.mongodb.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerRepositoryIntegrationTest extends BaseRepositoryIntegrationTest {

  @Autowired CustomerRepository customerRepository;

  @Test
  public void findAllTest() {
    var results = customerRepository.findAll();
    Assertions.assertFalse(results.isEmpty());
  }

  @Test
  public void findByIdTest() {
    var id = "1";
    var customer = customerRepository.findById(id).orElseThrow();
    Assertions.assertTrue(customer.getId() != null);
    Assertions.assertTrue(customer.getVersion() != null);
  }

  @Test
  public void saveTest() {
    Customer customer = new Customer();
    customer.setFirstName(null);
    customer.setLastName(null);
    customer.setPassword(null);
    customer.setEmail(null);
    customer.setUsername(null);

    var created = customerRepository.save(customer);
    Assertions.assertTrue(created.getId() != null);
    Assertions.assertTrue(created.getVersion() != null);
  }

  @Test
  public void updateTest() {
    var id = "1";
    var customer = customerRepository.findById(id).orElseThrow();
    customer.setFirstName(null);
    customer.setLastName(null);
    customer.setPassword(null);
    customer.setEmail(null);
    customer.setUsername(null);

    customer = customerRepository.save(customer);
    Assertions.assertEquals("", customer.getFirstName());
    Assertions.assertEquals("", customer.getLastName());
    Assertions.assertEquals("", customer.getPassword());
    Assertions.assertEquals("", customer.getEmail());
    Assertions.assertEquals("", customer.getUsername());
  }

  @Test
  public void deleteTest() {
    var id = "1";
    customerRepository.deleteById(id);
    var notFound = customerRepository.findById(id);
    Assertions.assertFalse(notFound.isPresent());
  }
}
