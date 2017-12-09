package com.Benjamin;

//used
//  https://www.youtube.com/watch?v=NoPzqahrzp8

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionConfiguration {  // public class for  data conn

    public static Connection getConnection() {  // making a method to be used

        Connection connection = null; // Starting with a null connection

        try {
            Class.forName("com.mysql.jdbc.Driver"); // finding the driver
            // connected to the spades database. with user and password already set.
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/spades", "user", "kittens");

        } catch (Exception e) {  // In case of connection problems
            e.printStackTrace();
        }

        return connection;
    }
}
