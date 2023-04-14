import java.sql.SQLException;
import java.util.LinkedList;

class Employee{
    public int ID,Rank;
    public String name,phoneNumber,password,userName;
    public LinkedList<bug>assignedBugs= new LinkedList<>();
    public LinkedList<bug>fixedBugs=new LinkedList<>();
    Employee(){}
    Employee(int id,int rank,String n,String PN,String pass,String un){
        ID=id;
        userName=un;
        Rank=rank;name=n;phoneNumber=PN;
        password=pass;
    }
    public String view(){
        return ("ID: "+ID+"\n Rank:"+ Rank +"\n Name:"+name+
                "\n Phone Number:"+phoneNumber)+viewAssignedBugs()+viewFixedBugs();
    }
    public String viewAssignedBugs(){
        String s="Assigned bugs:\n";
        for (bug assignedBug : assignedBugs) {
            s+=assignedBug.view()+'\n';
        }
        return s;
    }
    public String viewFixedBugs(){
        String s="Fixed bugs:\n";
        for (bug fixedBug: fixedBugs) {
            s+=fixedBug.view()+'\n';
        }
        return s;
    }


}
public class Main {
    public static void main(String[] args) throws SQLException {
        BugDAO bugDAO=new BugDAO();
        globals.employeeLinkedList=bugDAO.getEmployees();
        globals.totalBugs=bugDAO.getAllBugs();
        globals.fixedBugs=bugDAO.getAllFixedBugs();

        new Main_Menu();


    }

}