import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class HRISGUI extends JFrame {
    private JFrame frame;
    private JComboBox<String> departmentComboBox, positionComboBox;
    private JTextField nameField, birthDateField;
    private JButton addEmployeeButton, showAllButton, backButton;
    private JTextArea displayArea;
    private ArrayList<Employee> employees;

    public HRISGUI() {
        frame = new JFrame("HRIS");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        employees = new ArrayList<>();

        frame.add(showMainPanel(), BorderLayout.CENTER);
        frame.pack();
        frame.setSize(600, 400);
        frame.setVisible(true);
    }

    private JPanel showMainPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        JButton addButton = new JButton("Add Data");
        showAllButton = new JButton("Show Data");

        JPanel buttonPanel = new JPanel(new GridLayout(2, 1));
        buttonPanel.add(addButton);
        buttonPanel.add(showAllButton);

        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaintPanel(showInputPanel());
            }
        });

        showAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaintPanel(showAllDataPanel());
            }
        });

        return mainPanel;
    }

    private JPanel showInputPanel() {
        JPanel inputPanel = new JPanel(new GridLayout(6, 2));

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(15);

        JLabel birthDateLabel = new JLabel("Birth Date (DD-MM-YYYY):");
        birthDateField = new JTextField(15);

        JLabel departmentLabel = new JLabel("Department:");
        String[] departments = Employee.getDepartment();
        departmentComboBox = new JComboBox<>(departments);

        JLabel positionLabel = new JLabel("Position:");
        String[] positions = {"Manager", "Developer", "HR Staff"};
        positionComboBox = new JComboBox<>(positions);

        addEmployeeButton = new JButton("Add Data");
        backButton = new JButton("Back");

        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(birthDateLabel);
        inputPanel.add(birthDateField);
        inputPanel.add(departmentLabel);
        inputPanel.add(departmentComboBox);
        inputPanel.add(positionLabel);
        inputPanel.add(positionComboBox);
        inputPanel.add(new JLabel());
        inputPanel.add(addEmployeeButton);
        inputPanel.add(new JLabel());
        inputPanel.add(backButton);

        addEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String birthDate = birthDateField.getText();
                String department = departmentComboBox.getSelectedItem().toString();
                String position = positionComboBox.getSelectedItem().toString();

                Employee employee;
                if (position.equals("Manager")) {
                    employee = new Manager(name, birthDate, department);
                } else if (position.equals("Developer")) {
                    employee = new Developer(name, birthDate, department);
                } else {
                    employee = new HRStaff(name, birthDate, department);
                }

                employees.add(employee);
                JOptionPane.showMessageDialog(HRISGUI.this, "Employee added: " + employee.getDetails());

                // Clear input fields
                nameField.setText("");
                birthDateField.setText("");
                departmentComboBox.setSelectedIndex(0);
                positionComboBox.setSelectedIndex(0);

                // Refresh display area
                displayAllEmployees();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaintPanel(showMainPanel());
            }
        });

        return inputPanel;
    }

    private JPanel showAllDataPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        StringBuilder strBuilder = new StringBuilder();
        for (Employee employee : employees) {
            strBuilder.append(employee.getDetails()).append("\n");
        }

        displayArea = new JTextArea(strBuilder.toString());
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaintPanel(showMainPanel());
            }
        });

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(backButton);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        return panel;
    }

    private void repaintPanel(JPanel newPanel) {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(newPanel);
        frame.revalidate();
        frame.repaint();
    }

    // Metode untuk menambahkan pegawai dari luar kelas
    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    // Metode untuk menampilkan semua pegawai dari luar kelas
    public void displayAllEmployees() {
        if (displayArea != null) {
            StringBuilder strBuilder = new StringBuilder();
            for (Employee employee : employees) {
                strBuilder.append(employee.getDetails()).append("\n");
            }
            displayArea.setText(strBuilder.toString());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new HRISGUI();
            }
        });
    }
}
