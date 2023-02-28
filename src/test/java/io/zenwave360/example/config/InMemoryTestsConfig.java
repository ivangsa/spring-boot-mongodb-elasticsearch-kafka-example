package io.zenwave360.example.config;

import io.zenwave360.example.core.outbound.mongodb.*;
import io.zenwave360.example.core.outbound.search.*;
import io.zenwave360.example.infrastructure.mongodb.inmemory.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

// @Configuration
public class InMemoryTestsConfig {
  private final CustomerRepository customerRepository = new CustomerRepositoryInMemory();

  @Bean
  @Primary
  public <T extends CustomerRepository> T customerRepository() {
    return (T) customerRepository;
  }

  @Bean
  @Primary
  public CustomerSearchRepository customerSearchRepository() {
    return null; // TODO
  }

  private final CustomerOrderRepository customerOrderRepository = new CustomerOrderRepositoryInMemory();

  @Bean
  @Primary
  public <T extends CustomerOrderRepository> T customerOrderRepository() {
    return (T) customerOrderRepository;
  }
}
