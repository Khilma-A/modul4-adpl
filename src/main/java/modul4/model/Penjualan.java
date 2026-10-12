package modul4.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modul4.model.Database;

public class Penjualan {

    private String kode;
    private String nama;
    private double harga;
    private final Database db;

    public Penjualan() {
        this.db = new Database();
    }

    public Penjualan(String kode, String nama, double harga) {
        this.kode = kode;
        this.nama = nama;
        this.harga = harga;
        this.db = new Database();
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public List<Penjualan> getAllPenjualan() {
        List<Penjualan> list = new ArrayList<>();
        db.connect();
        ResultSet rs = db.view("SELECT * FROM tabel_penjualan ORDER BY kode ASC");
        try {
            if (rs != null) {
                while (rs.next()) {
                    Penjualan p = new Penjualan(
                            rs.getString("kode"),
                            rs.getString("nama"),
                            rs.getDouble("harga")
                    );
                    list.add(p);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetch data penjualan: " + e.getMessage());
        } finally {
            db.disconnect();
        }
        return list;
    }

    public void save() {
        db.connect();
        ResultSet rs = db.view("SELECT kode FROM tabel_penjualan WHERE kode = '"
                + this.kode + "'");
        try {
            if (rs != null && rs.next()) {
                String sqlUpdate = "UPDATE tabel_penjualan SET nama = '"
                        + this.nama + "', harga = " + this.harga + " WHERE kode = '"
                        + this.kode + "'";
                db.query(sqlUpdate);
            } else {
                String sqlInsert = "INSERT INTO tabel_penjualan (kode, nama,"
                        + " harga) VALUES ('" + this.kode + "', '" + this.nama
                        + "', " + this.harga + ")";
                db.query(sqlInsert);
            }
        } catch (SQLException e) {
            System.err.println("Error saat menyimpan data: " + e.getMessage());
        } finally {
            db.disconnect();
        }
    }

    public void delete() {
        db.connect();
        String sqlDelete = "DELETE FROM tabel_penjualan WHERE kode = '"
                + this.kode + "'";
        db.query(sqlDelete);
        db.disconnect();
    }
}
