package com.getsong.mockito.annotation.jdbc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * TODO: Purpose
 *
 * @author TODO: getso
 * @since 23/10/2019 12:01 AM
 */
@Configuration
// @ComponentScan("com.getsong.mockito.annotation.jdbc")
public class SpringJdbcConfig {

  @Bean
  public DataSource mysqlDataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
    dataSource.setUrl("jdbc:mysql://localhost/feedback?serverTimezone=UTC");
    dataSource.setUsername("sqluser");
    dataSource.setPassword("sqluserpw");
    return dataSource;
  }
}
