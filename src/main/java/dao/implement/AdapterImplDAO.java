package dao.implement;


import dao.AdapterDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AdapterImplDAO extends AdapterDAO {
    private static AdapterDAO dao;
    public static final String DEFAULT_HOST = "localhost";
    public static final String DEFAULT_DATABASE = "cursovoiperevoz";
    public static final String DEFAULT_LOGIN = "postgres";
    public static final String DEFAULT_PASSWORD = "1234";
    public static final int DEFAULT_PORT = 5432;
    private static AdapterDAO instance;

    private AdapterImplDAO() {
        super ("org.postgresql.Driver");
        setURL(DEFAULT_HOST, DEFAULT_DATABASE, DEFAULT_PORT);
        connect(DEFAULT_LOGIN, DEFAULT_PASSWORD);
    }
    @Override
    public void setURL (String host, String database, int port) {
        if (database.length() > 0)
            this.url = "jdbc:postgresql://" + host + ":" + port + "/" + database;
        else
            this.url = "jdbc:postgresql://" + host + ":" + port;
    }

    @Override
    public void connect(String login, String password) {
        super.connect(login, password);
    }

    @Override
    public Connection getConnection (){
        Connection connection =null;
        try {
            connection = DriverManager.getConnection(url, properties);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    public static void initInstance(){
        if (dao == null){
            dao = new AdapterImplDAO();
        }
    }

    public static AdapterDAO getInstance(){
        initInstance();
        return dao;
    }
   /* public static AdapterDAO getInstance(){
        if (instance==null){
           instance = new AdapterImplDAO();
       }
       return instance;
    }*/

}
