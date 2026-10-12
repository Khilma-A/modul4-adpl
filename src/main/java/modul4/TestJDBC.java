package modul4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestJDBC {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/moduladpl";
        String user = "root";
        String pass = "Khilmaa*2103";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Status Koneksi: BERHASIL!");
            System.out.println("Terhubung ke basis data: moduladpl");
            conn.close();
        } catch (ClassNotFoundException e) {
            System.err.println("Driver MySQL JDBC tidak ditemukan: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Koneksi ke database gagal: " + e.getMessage());
        }
    }
}
