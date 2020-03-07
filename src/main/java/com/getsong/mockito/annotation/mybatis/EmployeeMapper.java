package com.getsong.mockito.annotation.mybatis;

import com.getsong.mockito.annotation.jdbc.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

/**
 * TODO: Purpose
 *
 * @author getsong
 * @since 31/10/2019 10:24 PM
 */
@Mapper
// @Repository
public interface EmployeeMapper {

  @Select("select * from employees where id = #{id}")
  @Results(@Result(property = "firstName", column = "first_name"))
  Employee findById(int id);
}
