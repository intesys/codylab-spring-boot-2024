package it.intesys.academy.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseManager {

    public static final Logger log = LoggerFactory.getLogger(DriverManager.class);

    public static void closeResultSet(ResultSet resultSet) {

        try {
            if (resultSet != null) resultSet.close();
        } catch (SQLException e) {
            log.error("Some errors occur during closing connection", e);
        }

    }

}
