package com.xujie.manager.config;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.sql.*;

@Configuration
@Primary
@Slf4j
public class DataSourceConfig {


    @Value("${spring.datasource.url}")
    private String datasourceUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @PostConstruct
    public void mysqlDataSource() {
        try {
            String[] str = datasourceUrl.split("\\?");
            String[] split = str[0].split("/");
            String serverUrl = split[2].split(":")[0];
            String port = split[2].split(":")[1];

            String newConnection = "jdbc:mysql://" + serverUrl + ":" + port + "/?" + str[1];
            String datasourceName = str[0].substring(str[0].lastIndexOf("/") + 1);

            try (Connection conn = DriverManager.getConnection(newConnection, username, password)) {
                DatabaseMetaData metaData = conn.getMetaData();
                ResultSet resultSet = metaData.getCatalogs();
                boolean databaseExists = false;
                while (resultSet.next()) {
                    if (datasourceName.equalsIgnoreCase(resultSet.getString("TABLE_CAT"))) {
                        log.info("Database already exists.");
                        databaseExists = true;
                        break;
                    }
                }

                if (!databaseExists) {
                    try (Statement statement = conn.createStatement()) {
                        String createQuery = "CREATE DATABASE `" + datasourceName + "` default character set utf8 COLLATE utf8_general_ci";
                        statement.executeUpdate(createQuery);
                    }
                    log.info("Database created successfully.");
                }
            }

        } catch (Exception e) {
            log.info("Failed to connect to the database server...");
            e.printStackTrace();
        }
    }
}
