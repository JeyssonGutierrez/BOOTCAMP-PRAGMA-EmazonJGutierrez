package com.bootcamp_2024_1.emazon.infrastructure.adapter.config;

import com.bootcamp_2024_1.emazon.infrastructure.config.DatabaseConfig;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class DatabaseConfigTest {

  @Mock
  private Environment env;

  @InjectMocks
  private DatabaseConfig databaseConfig;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);

    // Mocking environment properties
    when(env.getProperty("spring.datasource.driver-class-name")).thenReturn("com.mysql.cj.jdbc.Driver");
    when(env.getProperty("spring.datasource.url")).thenReturn("jdbc:mysql://localhost:3306/emazon");
    when(env.getProperty("spring.datasource.username")).thenReturn("root");
    when(env.getProperty("spring.datasource.password")).thenReturn("password");
    when(env.getProperty("spring.jpa.hibernate.ddl-auto")).thenReturn("update");
    when(env.getProperty("spring.jpa.properties.hibernate.dialect")).thenReturn("org.hibernate.dialect.MySQL8Dialect");
    when(env.getProperty("spring.jpa.show-sql")).thenReturn("true");
  }

  @Test
  public void testDataSource() {
    DataSource dataSource = databaseConfig.dataSource();
    assertNotNull(dataSource);
    assertTrue(dataSource instanceof DriverManagerDataSource);

    DriverManagerDataSource ds = (DriverManagerDataSource) dataSource;
    assertEquals("jdbc:mysql://localhost:3306/emazon", ds.getUrl());
    assertEquals("root", ds.getUsername());
    assertEquals("password", ds.getPassword());
  }

  @Test
  public void testEntityManagerFactory() {
    LocalContainerEntityManagerFactoryBean emf = databaseConfig.entityManagerFactory();
    assertNotNull(emf);
    assertNotNull(emf.getJpaVendorAdapter());
    // No podemos verificar directamente las propiedades internas, pero podemos
    // verificar que el JpaVendorAdapter está configurado correctamente.
  }

  @Test
  public void testTransactionManager() {
    // Mockeamos el EntityManagerFactory
    LocalContainerEntityManagerFactoryBean mockEntityManagerFactoryBean = Mockito.mock(LocalContainerEntityManagerFactoryBean.class);
    when(mockEntityManagerFactoryBean.getObject()).thenReturn(Mockito.mock(EntityManagerFactory.class));

    // Configuramos el DatabaseConfig para usar este mock
    databaseConfig = Mockito.spy(databaseConfig);
    when(databaseConfig.entityManagerFactory()).thenReturn(mockEntityManagerFactoryBean);

    // Ahora verificamos el transactionManager
    JpaTransactionManager transactionManager = databaseConfig.transactionManager();
    assertNotNull(transactionManager);
    assertNotNull(transactionManager.getEntityManagerFactory());
  }
}
