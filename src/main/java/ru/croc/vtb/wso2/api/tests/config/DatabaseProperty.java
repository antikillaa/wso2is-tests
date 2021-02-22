package ru.croc.vtb.wso2.api.tests.config;

public class DatabaseProperty {

    public static final String URL_db_test = "jdbc:sqlserver://test_db:1433;databaseName=auth;loginTimeout=60;queryTimeout=60";
    public static final String URL_db_k3 = "jdbc:sqlserver://k3-ac-reg-sql.msk.vtb24.ru:1433;databaseName=Auth;loginTimeout=60;queryTimeout=60";
    public static final String URL_db_k4 = "jdbc:sqlserver://k4-ac-reg-sql.msk.vtb24.ru:1433;databaseName=Auth;loginTimeout=60;queryTimeout=60";
    public static final String URL_db_k5 = "jdbc:sqlserver://k5-ac-reg-sql.msk.vtb24.ru:1433;databaseName=Auth;loginTimeout=60;queryTimeout=60";

    public static final String URL_ac_test = "http://test_ac:8080/services/AuthenticationServiceSoap";
    public static final String URL_ac_k3 = "http://k3-ac-service:8080/services/AuthenticationServiceSoap";
    public static final String URL_ac_k4 = "http://k4-ac-service:8080/AuthenticationServiceK4/services/AuthenticationServiceSoap";
    public static final String URL_ac_k5 = "http://k5-ac-service:8080/AuthenticationServiceK5/services/AuthenticationServiceSoap";

    public static final String USER_test = "ac_user";
    public static final String USER_k3 = "k3-ac-reg-svc";
    public static final String USER_k4 = "k4-ac-reg-svc";
    public static final String USER_k5 = "k5-ac-reg-svc";

    public static final String PASSWORD_test = "P@ssw0rdvtb0";
    public static final String PASSWORD_k3 = "bvER86nCbXKuW9R";
    public static final String PASSWORD_k4 = "SV9kXuE8mQ2dP";
    public static final String PASSWORD_k5 = "DhPoVnAwHTeZkQ";

    public static final String NEW_PASSWORD = "123123Qw!";

}
