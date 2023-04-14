import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;

public class EmployeeInterface extends JFrame {
    private JList<String> list;
    private JPanel panel1;
    private JButton reportFixedButton;
    private JTextField tfFixReport;

    BugDAO bugDAO=new BugDAO();

    EmployeeInterface(Employee employee) {
        setContentPane(panel1);
        setTitle("Employee Interface");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        String[] bugStrings = new String[employee.assignedBugs.size()];
        int i = 0;
        for (bug buggie : employee.assignedBugs) {
            bugStrings[i] = "ID: " + buggie.ID + " | Type: " + buggie.type + " | Severity: " +
                    buggie.severity + " | Assigned To: " + buggie.assignedTo + " | Report Time: " +
                    buggie.reportTime.toString() + " | Fix Time: " + buggie.fixTime +
                    " | Description: " + buggie.description + " | Reported By: " + buggie.reportedBy;
            i++;
        }

        list.setListData(bugStrings);

        reportFixedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tfFixReport.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please provide a report for the fix", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    bug fixed=employee.assignedBugs.get(list.getSelectedIndex());
                    fixed.fixReport=tfFixReport.getText();
                    fixed.fixTime= LocalTime.now();
                    employee.assignedBugs.remove(list.getSelectedIndex());
                    employee.fixedBugs.add(fixed);
                    globals.totalBugs.remove(fixed);
                    globals.fixedBugs.add(fixed);
                    //DB
                    bugDAO.clearFixedBugTable();
                    bugDAO.insertFixedBugs();
                    bugDAO.clearBugTable();
                    bugDAO.insertBugs();
                    //
                    JOptionPane.showMessageDialog(null, "Bug fixed (yay) ");
                    String[] bugStrings = new String[employee.assignedBugs.size()];
                    int i = 0;
                    for (bug buggie : employee.assignedBugs) {
                        bugStrings[i] = "ID: " + buggie.ID + " | Type: " + buggie.type + " | Severity: " +
                                buggie.severity + " | Assigned To: " + buggie.assignedTo + " | Report Time: " +
                                buggie.reportTime.toString() + " | Fix Time: " + buggie.fixTime +
                                " | Description: " + buggie.description + " | Reported By: " + buggie.reportedBy;
                        i++;
                    }

                    list.setListData(bugStrings);


                }
            }
        });
    }
}

