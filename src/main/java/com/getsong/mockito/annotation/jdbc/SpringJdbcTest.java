package com.getsong.mockito.annotation.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import javax.sql.DataSource;
import java.util.List;

/**
 * TODO: Purpose
 *
 * @author TODO: getso
 * @since 22/10/2019 11:07 PM
 */
@Slf4j
@ComponentScan("com.getsong.mockito.annotation.jdbc")
public class SpringJdbcTest {

  private JdbcTemplate jdbcTemplate;

  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  private EmployeeRowMapper employeeRowMapper;

  public SpringJdbcTest(DataSource dataSource, EmployeeRowMapper employeeRowMapper) {
    jdbcTemplate = new JdbcTemplate(dataSource);
    namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    this.employeeRowMapper = employeeRowMapper;
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

  public String getEmployeeUsingMapSqlParameterSource() {
    final SqlParameterSource parameterSource = new MapSqlParameterSource().addValue("id", 1);
    return namedParameterJdbcTemplate.queryForObject(
        "select first_name from employees where id = :id", parameterSource, String.class);
  }

  public String getEmployeeUsingBeanProperty() {
    Employee employee = new Employee();
    employee.setFirstName("James");
    final SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(employee);
    return namedParameterJdbcTemplate.queryForObject(
        "select last_name from employees where first_name = :firstName",
        parameterSource,
        String.class);
  }

  public List<Employee> getEmployeesByFirstName(String firstName) {
    return jdbcTemplate.query(
        "select * from employees where first_name = ?",
        new Object[] {firstName},
        employeeRowMapper);
  }

  public static void main(String[] args) {
    ApplicationContext applicationContext =
        new AnnotationConfigApplicationContext(SpringJdbcTest.class);
    SpringJdbcTest springJdbcTest = applicationContext.getBean(SpringJdbcTest.class);
    log.info("inserted {} employees", springJdbcTest.addEmployee());
    log.info(String.valueOf(springJdbcTest.getCountOfEmployees()));
    log.info(
        "get first name by MapSqlParameterSource: {}",
        springJdbcTest.getEmployeeUsingMapSqlParameterSource());
    log.info(
        "get last name by BeanPropertySqlParameterSource: {}",
        springJdbcTest.getEmployeeUsingBeanProperty());
    log.info(springJdbcTest.getEmployeesByFirstName("Jay").toString());
  }
}
