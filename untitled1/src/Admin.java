import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class Admin extends JFrame {
    private JPanel panel1;
    private JList<String> list1;
    private JList<String> list2;
    private JList<String> list3;
    private JButton dropButton;
    private JButton fixButton;
    private JButton reSubmitButton;
    private JButton dropFixedButton;
    private JButton removeButton;
    private JButton closeButton;
    BugDAO bugDAO=new BugDAO();

    Admin(){
        setContentPane(panel1);
        setTitle("Admin Interface");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setVisible(true);

        list1.setListData(updateBugs(globals.totalBugs));
        list2.setListData(updateBugs(globals.fixedBugs));
        list3.setListData(updateEmp());

        fixButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                globals.fixedBugs.add(globals.totalBugs.get(list1.getSelectedIndex()));
                globals.totalBugs.remove(list1.getSelectedIndex());

                //update DB
                bugDAO.clearFixedBugTable();
                bugDAO.insertFixedBugs();
                bugDAO.clearBugTable();
                bugDAO.insertBugs();

                //updated
                JOptionPane.showMessageDialog(null, "Fixed");
                list1.setListData(updateBugs(globals.totalBugs));
                list2.setListData(updateBugs(globals.fixedBugs));
            }
        });
        dropButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                globals.totalBugs.remove(list1.getSelectedIndex());
                bugDAO.clearBugTable();
                bugDAO.insertBugs();
                JOptionPane.showMessageDialog(null, "Dropped");
                list1.setListData(updateBugs(globals.totalBugs));
            }
        });
        reSubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                globals.totalBugs.add(globals.fixedBugs.get(list2.getSelectedIndex()));
                globals.fixedBugs.remove(list2.getSelectedIndex());
                bugDAO.clearBugTable();
                bugDAO.insertBugs();
                bugDAO.clearFixedBugTable();
                bugDAO.insertFixedBugs();
                JOptionPane.showMessageDialog(null, "Resubmitted");
                list1.setListData(updateBugs(globals.totalBugs));
                list2.setListData(updateBugs(globals.fixedBugs));
            }
        });
        dropFixedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                globals.fixedBugs.remove(list2.getSelectedIndex());
                bugDAO.clearFixedBugTable();
                bugDAO.insertFixedBugs();
                JOptionPane.showMessageDialog(null, "Dropped");
                list2.setListData(updateBugs(globals.fixedBugs));
            }
        });
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                globals.employeeLinkedList.remove(list3.getSelectedIndex());
                bugDAO.clearEmployeeTable();
                bugDAO.insertEmployees();
                JOptionPane.showMessageDialog(null, "Employee Removed");
                list3.setListData(updateEmp());
            }
        });

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
    public String[] updateBugs(LinkedList<bug>bugLinkedList){
        int i = 0;
        String[] bugStrings = new String[bugLinkedList.size()];
        for (bug buggie : bugLinkedList) {
            bugStrings[i] = buggie.view();
            i++;
        }
        return bugStrings;
    }

    public String[] updateEmp(){
        LinkedList<Employee>employeeLinkedList=globals.employeeLinkedList;
        int i = 0;
        String[] bugStrings = new String[employeeLinkedList.size()];
        for (Employee employee: employeeLinkedList) {
            bugStrings[i] = employee.view();
            i++;
        }
        return bugStrings;
    }
}
