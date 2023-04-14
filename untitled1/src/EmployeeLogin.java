import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class EmployeeLogin extends JFrame {
    private JTextField tfUsername;
    private JPasswordField passwordField1;
    private JButton signUpButton;
    private JButton loginButton;
    private JButton cancelButton;
    private JPanel mainPane;

    EmployeeLogin() {
        setContentPane(mainPane);
        setTitle("Login");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setVisible(true);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = tfUsername.getText(),pass = passwordField1.getText();
                for (Employee employee : globals.employeeLinkedList) {
                    if (Objects.equals(employee.userName, user)) {
                        if (Objects.equals(employee.password, pass)) {
                            JOptionPane.showMessageDialog(null, "Login Successful");
                            new EmployeeInterface(employee);
                            setVisible(false);
                            return;
                        } else
                            System.out.println("Wrong pass");
                    }
                }
                JOptionPane.showMessageDialog(null, "Error: Credentials don't match", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new NewEmployee();
            }
        });
    }
}
