package modul4;

import modul4.view.PenjualanView;
import modul4.controller.PenjualanController;
import modul4.model.Penjualan;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Penjualan model = new Penjualan();
                PenjualanView view = new PenjualanView();
                new PenjualanController(model, view);
                view.setVisible(true);
            }
        });
    }
}
