package io.zenwave360.example.infrastructure.mongodb;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@org.springframework.transaction.annotation.Transactional
public abstract class BaseRepositoryIntegrationTest {}
