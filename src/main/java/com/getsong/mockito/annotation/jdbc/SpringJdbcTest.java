package com.getsong.mockito.annotation.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * TODO: Purpose
 *
 * @author TODO: getso
 * @since 22/10/2019 11:07 PM
 */
@Slf4j
@ComponentScan("com.getsong.mockito.annotation.jdbc")
public class SpringJdbcTest {

  JdbcTemplate jdbcTemplate;

  public SpringJdbcTest(DataSource dataSource) {
    jdbcTemplate = new JdbcTemplate(dataSource);
  }

  public int getCountOfEmployees() {
    return jdbcTemplate.queryForObject("select count(*) from employees", Integer.class);
  }

  public int addEmployee() {
    return jdbcTemplate.update(
        "insert into employees (first_name, last_name, address) values (?, ?, ?)",
        "jay",
        "chou",
        "address");
  }

  public static void main(String[] args) {
    ApplicationContext applicationContext =
        new AnnotationConfigApplicationContext(SpringJdbcTest.class);
    SpringJdbcTest springJdbcTest = applicationContext.getBean(SpringJdbcTest.class);
    log.info("inserted {} employees", springJdbcTest.addEmployee());
    log.info(String.valueOf(springJdbcTest.getCountOfEmployees()));
  }
}
