package com.ostech.cbt.database;

import io.github.cdimascio.dotenv.Dotenv;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConfiguration {

    public Connection getDatabaseConnection() {
        try {
            final Dotenv dotenv = Dotenv.load();
            final String DATABASE_HOST = dotenv.get("DATABASE_HOST");
            final String DATABASE_PASSWORD = dotenv.get("DATABASE_PASSWORD");
            final String DATABASE_USER = dotenv.get("DATABASE_USER");
            final String DATABASE_NAME = dotenv.get("DATABASE_NAME");

            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);
            String url = String.format("jdbc:mysql://%s:3306/%s", DATABASE_HOST, DATABASE_NAME);

            return DriverManager.getConnection(url, DATABASE_USER, DATABASE_PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}