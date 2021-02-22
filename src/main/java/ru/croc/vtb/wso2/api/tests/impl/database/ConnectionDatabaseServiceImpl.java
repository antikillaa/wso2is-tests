package ru.croc.vtb.wso2.api.tests.impl.database;

import ru.croc.vtb.wso2.api.tests.services.database.ConnectionDatabaseService;

import java.sql.*;

public class ConnectionDatabaseServiceImpl implements ConnectionDatabaseService {

    public Statement getConnection(String URL, String user, String pass) {
        Statement stmt = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(URL, user, pass);
            stmt = conn.createStatement();

            if (conn != null) {
                DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
                System.out.println("Driver name: " + dm.getDriverName());
                System.out.println("Driver version: " + dm.getDriverVersion());
                System.out.println("Product name: " + dm.getDatabaseProductName());
                System.out.println("Product version: " + dm.getDatabaseProductVersion());
            }

        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
      return stmt;
    }

}
