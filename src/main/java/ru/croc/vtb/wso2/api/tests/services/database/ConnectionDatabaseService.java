package ru.croc.vtb.wso2.api.tests.services.database;

import java.sql.*;

public interface ConnectionDatabaseService {

    Statement getConnection(String URL, String user, String pass);
}
