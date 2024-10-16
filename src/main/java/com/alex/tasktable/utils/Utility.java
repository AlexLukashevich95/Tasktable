package com.alex.tasktable.utils;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Utility {
    private static Connection connection;

    public static Connection getConnection(DataSource dataSource) {
        try {
            connection = dataSource.getConnection();
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static PreparedStatement getPrepareStatement(DataSource dataSource, String query) {
        try {
            return getConnection(dataSource).prepareStatement(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static PreparedStatement getPrepareStatement(DataSource dataSource, String query, List<Object> params) {
        try {
            PreparedStatement preparedStatement = getConnection(dataSource).prepareStatement(query);
            for (int i = 0; i < params.size(); i++) {
                preparedStatement.setObject(i + 1, params.get(i));
            }
            return preparedStatement;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public static int executeUpdate(PreparedStatement preparedStatement) {
        try {
            connection.setAutoCommit(false);
            int result = preparedStatement.executeUpdate();
            if (result!=0)
                connection.commit();
            connection.setAutoCommit(true);
            return result;
        } catch (SQLException e) {
            rollbackTransaction(connection);
            throw new RuntimeException(e);
        }
    }

    public static ResultSet getResultSet(PreparedStatement preparedStatement) {
        try {
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void rollbackTransaction(Connection connection) {
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}