import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.Random;

public class blabla extends JFrame {
    private JPanel mainPanel;
    private JComboBox typeCB;
    private JTextField tfDescription;
    private JComboBox severityCB;
    private JButton submitButton;
    private JButton cancelButton;
    private JTextField tfUsername;
    BugDAO bugDAO=new BugDAO();
    public blabla(){
        setContentPane(mainPanel);
        setTitle("Report a bug");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400,400);
        setLocationRelativeTo(null);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reportBug();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        setVisible(true);
    }

    public void reportBug() {
        String des=tfDescription.getText();
        int scb=severityCB.getSelectedIndex();
        int tcb=typeCB.getSelectedIndex();
        String rptBy=tfUsername.getText();
        if(des.isEmpty()||(scb!=0&&scb!=1&&scb!=2)||(tcb!=0&&tcb!=1)){
            JOptionPane.showMessageDialog(this,"Please enter all fields","Try again",JOptionPane.ERROR_MESSAGE);
            return;
        }
        LocalTime rptTime=LocalTime.now();
        addBugToDB(des,tcb,scb,rptTime, rptBy,0);

    }
    public void addBugToDB(String des,int type, int severe, LocalTime rptTime, String rptBy, int assignTo) {
        Random random=new Random();
        bug buggie=new bug(random.nextInt(10),type,severe,assignTo,rptTime, LocalTime.MIDNIGHT,des,rptBy,"Not fixed yet");
        globals.employeeLinkedList.sort((o1, o2) -> Integer.compare(o1.assignedBugs.size(), o2.assignedBugs.size()));
        buggie.assignedTo=globals.employeeLinkedList.getFirst().ID;
        globals.employeeLinkedList.getFirst().assignedBugs.add(buggie);
        globals.totalBugs.add(buggie);
        JOptionPane.showMessageDialog(null,"Reported and assigned to "+globals.employeeLinkedList.getFirst().ID);
        bugDAO.clearBugTable();
        bugDAO.insertBugs();
        dispose();
    }

}
