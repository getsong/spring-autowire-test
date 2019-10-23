package com.getsong.mockito.annotation.jdbc;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * TODO: Purpose
 *
 * @author TODO: getso
 * @since 23/10/2019 8:07 PM
 */
@Component
public class EmployeeRowMapper implements RowMapper<Employee> {

  @Override
  public Employee mapRow(ResultSet resultSet, int i) throws SQLException {
    Employee employee = new Employee();

    employee.setId(resultSet.getInt("id"));
    employee.setFirstName(resultSet.getString("first_name"));
    employee.setLastName(resultSet.getString("last_name"));
    employee.setAddress(resultSet.getString("address"));
    return employee;
  }
}
