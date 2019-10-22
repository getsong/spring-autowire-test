package com.getsong.mockito.annotation.jdbc;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * TODO: Purpose
 *
 * @author TODO: getso
 * @since 21/10/2019 8:29 PM
 */
@Slf4j
public class JdbcTest {

  public static void main(String[] args) throws Exception {
    Class.forName("com.mysql.cj.jdbc.Driver");
    try (Connection connection =
        DriverManager.getConnection(
            "jdbc:mysql://localhost/feedback?user=sqluser&password=sqluserpw&serverTimezone=UTC")) {
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery("select * from feedback.comments");
      while (resultSet.next()) {
        log.info(resultSet.getString("myuser"));
      }
    }
  }
}
