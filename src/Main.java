import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                HRISGUI hrisgui = new HRISGUI();

                // Menambahkan beberapa pegawai secara manual
                // Misalnya menambahkan beberapa pegawai untuk pengujian
                hrisgui.addEmployee(new Manager("John Doe", "01-01-1980", "Product"));
                hrisgui.addEmployee(new Developer("Jane Smith", "15-05-1990", "Product"));
                hrisgui.addEmployee(new HRStaff("Emily Johnson", "20-07-1985", "Human Resources"));

                // Menampilkan data awal yang telah ditambahkan
                hrisgui.displayAllEmployees();
            }
        });
    }
}
