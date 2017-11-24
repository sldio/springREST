package com.spring.jpa.services.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil
{
    private static Connection connection;

    private final static String URL = "jdbc:postgresql://localhost:5432/message";
    private final static String USER_NAME = "postgres";
    private final static String PASSWORD = "root";

    private static Boolean isDriverRegistered()
    {
        try{
            DriverManager.registerDriver(new org.postgresql.Driver());
            return true;
        }
        catch (SQLException e){
            System.out.println("no driver");
            return false;
        }
    }

    public static Connection getConnection()
    {
        if (isDriverRegistered()){

            try {
                connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            }
            catch (SQLException e){
                System.out.println("can`t connect to database");
            }
        }
        return connection;
    }

    public static void closeConnection()
    {
        try{
            connection.close();
        }
        catch (SQLException e){
            System.out.println("can`t close");
            }
    }
}
