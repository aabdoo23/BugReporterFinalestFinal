import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.Random;

public class NewEmployee extends JFrame {
    private JTextField tfName;
    private JTextField tfPN;
    private JButton registerButton;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JComboBox comboBox1;
    private JPanel mainPanel;
    private JTextField tfUN;
    BugDAO bugDAO=new BugDAO();

    public NewEmployee() {
        setContentPane(mainPanel);
        setTitle("Sign Up");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setVisible(true);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!Objects.equals(passwordField1.getText(), passwordField2.getText())) {
                    JOptionPane.showMessageDialog(null, "Error: Passwords don't match", "Error", JOptionPane.ERROR_MESSAGE);

                } else {
                    JOptionPane.showMessageDialog(null, "Success");
                    Random random=new Random();
                    int ide=random.nextInt(200);
                    Employee employee = new Employee(ide,
                            comboBox1.getSelectedIndex(),
                            tfName.getText(), tfPN.getText(),
                            passwordField2.getText(),
                            tfUN.getText());
                    globals.employeeLinkedList.add(employee);
                    bugDAO.clearEmployeeTable();
                    bugDAO.insertEmployees();
                    dispose();
                }
            }
        });
    }
}
