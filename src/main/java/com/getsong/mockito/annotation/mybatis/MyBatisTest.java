package com.getsong.mockito.annotation.mybatis;

import com.getsong.mockito.annotation.jdbc.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * TODO: Purpose
 *
 * @author getsong
 * @since 31/10/2019 11:43 PM
 */
@Slf4j
// @SpringBootApplication
@ComponentScan("com.getsong.mockito.annotation")
// @Configuration
public class MyBatisTest {

  private final EmployeeMapper employeeMapper;

  public MyBatisTest(EmployeeMapper employeeMapper) {
    this.employeeMapper = employeeMapper;
  }

  public static void main(String[] args) {
    ApplicationContext applicationContext =
        new AnnotationConfigApplicationContext(MyBatisTest.class);

    MyBatisTest myBatisTest = applicationContext.getBean(MyBatisTest.class);
    EmployeeMapper employeeMapper = applicationContext.getBean(EmployeeMapper.class);
    Employee employee = employeeMapper.findById(2);
    log.info(employee.toString());
    for (String s : applicationContext.getBeanDefinitionNames()) {
      log.info(s);
    }
  }
}
