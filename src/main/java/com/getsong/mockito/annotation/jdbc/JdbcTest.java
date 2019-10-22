package com.getsong.mockito.annotation.jdbc;

import lombok.extern.slf4j.Slf4j;

import java.sql.*;

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
                "jdbc:mysql://localhost/feedback?user=sqluser&password=sqluserpw&serverTimezone=UTC");
        Statement statement = connection.createStatement()) {
      statement.executeUpdate(
          "insert into feedback.comments values (default, 'test', 'testemail', 'test', '2009-09-14', 'test', 'test')");
      ResultSet resultSet = statement.executeQuery("select * from feedback.comments");
      while (resultSet.next()) {
        log.info(resultSet.getString("myuser"));
      }
      log.info("prepared statement: select");
      PreparedStatement preparedStatement =
          connection.prepareStatement("select * from feedback.comments where myuser = ?");
      preparedStatement.setString(1, "test");
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        log.info(resultSet.getString("myuser"));
      }

      log.info("delete test data");
      PreparedStatement preparedStatement1 =
          connection.prepareStatement("delete from feedback.comments where myuser = ?");
      preparedStatement1.setString(1, "test");
      log.info("number of rows deleted: {}", preparedStatement1.executeUpdate());
    }
  }
}
