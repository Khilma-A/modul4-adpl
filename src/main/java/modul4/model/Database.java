package modul4.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    private final String url = "jdbc:mysql://localhost:3306/moduladpl";
    private final String user = "root";
    private final String pass = "Khilmaa*2103";
    private Connection conn;
    private Statement stmt;

    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, pass);
            stmt = conn.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Gagal membuka koneksi database: " + e.getMessage());
        }
    }

    public void query(String sql) {
        try {
            if (stmt != null) {
                stmt.executeUpdate(sql);
            }
        } catch (SQLException e) {
            System.err.println("Gagal mengeksekusi query: " + e.getMessage());
        }
    }

    public ResultSet view(String sql) {
        try {
            if (stmt != null) {
                return stmt.executeQuery(sql);
            }
        } catch (SQLException e) {
            System.err.println("Gagal mengambil data ResultSet: " + e.getMessage());
        }
        return null;
    }

    public void disconnect() {
        try {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.err.println("Gagal menutup koneksi: " + e.getMessage());
        }
    }
}
