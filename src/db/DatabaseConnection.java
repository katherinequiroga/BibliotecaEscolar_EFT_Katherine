package db;
import java.sql.*;
public class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection connection;
    private final String URL = "jdbc:mysql://localhost:3306/biblioteca_escolar?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private final String USER = "root";
    private final String PASS = "root"; // si tu MySQL tiene clave ponla aqui, ej "1234"

    private DatabaseConnection() throws SQLException {
        this.connection = DriverManager.getConnection(URL, USER, PASS);
    }
    public static DatabaseConnection getInstance() throws SQLException {
        if(instance==null || instance.getConnection().isClosed()) instance = new DatabaseConnection();
        return instance;
    }
    public Connection getConnection(){ return connection; }
}