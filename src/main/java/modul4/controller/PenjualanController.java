package modul4.controller;

import modul4.model.Penjualan;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import modul4.view.PenjualanView;

public class PenjualanController {

    private final Penjualan model;
    private final PenjualanView view;

    public PenjualanController(Penjualan model, PenjualanView view) {
        this.model = model;
        this.view = view;
        initController();
        loadTable();
    }

    private void initController() {
        view.getBtnSimpan().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionSimpan();
            }
        });

        view.getBtnHapus().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionHapus();
            }
        });
    }

    private void actionSimpan() {
        String kode = view.getTextKode().getText().trim();
        String nama = view.getTextNama().getText().trim();
        String strHarga = view.getTextHarga().getText().trim();

        if (kode.isEmpty() || nama.isEmpty() || strHarga.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Semua field input wajib "
                    + "diisi!", "Validasi", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            double harga = Double.parseDouble(strHarga);
            Penjualan p = new Penjualan(kode, nama, harga);
            p.save();
            JOptionPane.showMessageDialog(view, "Data barang berhasil disimpan "
                    + "ke database!");
            clearForm();
            loadTable();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view, "Format harga harus berupa angka!",
                    "Kesalahan", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actionHapus() {
        int selectedRow = view.getTablePenjualan().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(view, "Pilih baris data pada tabel "
                    + "yang ingin dihapus!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String kode = view.getTableModel().getValueAt(selectedRow, 0).toString();
        int confirm = JOptionPane.showConfirmDialog(view, "Hapus data barang "
                + "dengan kode " + kode + "?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            Penjualan p = new Penjualan();
            p.setKode(kode);
            p.delete();
            JOptionPane.showMessageDialog(view, "Data barang berhasil dihapus!");
            clearForm();
            loadTable();
        }
    }

    private void loadTable() {
        DefaultTableModel tm = view.getTableModel();
        tm.setRowCount(0);
        List<Penjualan> list = model.getAllPenjualan();
        for (Penjualan p : list) {
            tm.addRow(new Object[]{p.getKode(), p.getNama(), p.getHarga()});
        }
    }

    private void clearForm() {
        view.getTextKode().setText("");
        view.getTextNama().setText("");
        view.getTextHarga().setText("");
    }
}
