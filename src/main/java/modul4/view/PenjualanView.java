package modul4.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PenjualanView extends JFrame {

    private JTextField textKode;
    private JTextField textNama;
    private JTextField textHarga;
    private JButton btnSimpan;
    private JButton btnHapus;
    private JTable tablePenjualan;
    private DefaultTableModel tableModel;

    public PenjualanView() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Sistem Penjualan Toko - Khilma 'Ainunnajah (109092530002)");
        setSize(650, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel panelForm = new JPanel(new GridLayout(4, 2, 8, 8));
        panelForm.setBorder(BorderFactory.createEmptyBorder(15, 15, 10, 15));

        panelForm.add(new JLabel("Kode Barang:"));
        textKode = new JTextField();
        panelForm.add(textKode);

        panelForm.add(new JLabel("Nama Barang:"));
        textNama = new JTextField();
        panelForm.add(textNama);

        panelForm.add(new JLabel("Harga Satuan (Rp):"));
        textHarga = new JTextField();
        panelForm.add(textHarga);

        btnSimpan = new JButton("Simpan Data");
        btnHapus = new JButton("Hapus Data");
        panelForm.add(btnSimpan);
        panelForm.add(btnHapus);

        add(panelForm, BorderLayout.NORTH);

        String[] columns = {"Kode", "Nama Barang", "Harga (Rp)"};
        tableModel = new DefaultTableModel(columns, 0);
        tablePenjualan = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tablePenjualan);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 15, 15, 15));

        add(scrollPane, BorderLayout.CENTER);
    }

    public JTextField getTextKode() {
        return textKode;
    }

    public JTextField getTextNama() {
        return textNama;
    }

    public JTextField getTextHarga() {
        return textHarga;
    }

    public JButton getBtnSimpan() {
        return btnSimpan;
    }

    public JButton getBtnHapus() {
        return btnHapus;
    }

    public JTable getTablePenjualan() {
        return tablePenjualan;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }
}
