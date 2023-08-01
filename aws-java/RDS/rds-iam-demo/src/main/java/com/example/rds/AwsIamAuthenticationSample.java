package com.example.rds;

import java.sql.*;
import java.util.Properties;

public class AwsIamAuthenticationSample {

   private static final String CONNECTION_STRING = "jdbc:mysql:aws://employees-database.c1vd6amykmfy.ap-south-1.rds.amazonaws.com:3306/employees-database";
   private static final String USER = "admin";

   public static void main(String[] args) throws SQLException {
      final Properties properties = new Properties();
      // Enable AWS IAM database authentication
      properties.setProperty("useAwsIam", "true");
      properties.setProperty("user", USER);

      // Try and make a connection
      try (final Connection conn = DriverManager.getConnection(CONNECTION_STRING, properties);
           final Statement statement = conn.createStatement();
           final ResultSet rs = statement.executeQuery("SELECT * FROM employees")) {
         while (rs.next()) {
            System.out.println(rs.getString("first_name"));
         }
      }
   }
}
