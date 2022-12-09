package database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlSingleton  {

    private static Connection singletonConnection;


    public static Connection getConnection() {
        if (singletonConnection == null) {
            return conectar();
        } else {
            return singletonConnection;
        }
    }

    public static Connection conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            singletonConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/posto", "root", "root");
//			singletonConnectionPGSQL = DriverManager.getConnection("jdbc:postgresql://192.168.0.102:5432/farmacia", "postgres", "123123123");
//			singletonConnectionSQLServer = DriverManager.getConnection("jdbc:sqlserver://;serverName=192.168.0.102\\\\instance1;encrypt=true;integratedSecurity=true;", "sa", "123123123");
            return singletonConnection;
            
        } catch (SQLException | ClassNotFoundException e) {
            return null;
        }
    }

    public void desconectar(Connection conn) {
        try {
            singletonConnection.close();
        } catch (SQLException e) {
        }
    }

}