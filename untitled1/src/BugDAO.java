import java.sql.*;
import java.util.LinkedList;

public class BugDAO {
    private Connection conn;
    String url = "jdbc:mysql://localhost:3306/bugsreports";
    String username = "root";
    String pass = "";

    public BugDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, pass);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }

    //bug
    public void clearBugTable() {
        try {
            Statement stmt = conn.createStatement();
            String sql = "TRUNCATE TABLE bugs";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public LinkedList<bug> getAllBugs() {
        LinkedList<bug> bugs = new LinkedList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM bugs");
            while (rs.next()) {
                int id = rs.getInt("ID");
                int type = rs.getInt("type");
                int severity = rs.getInt("severity");
                int assignedTo = rs.getInt("assignedToID");
                Time reportTime = rs.getTime("reportTime");
                Time fixTime = rs.getTime("fixTime");
                String description = rs.getString("description");
                String reportedBy = rs.getString("reportedBy");
                String fixReport = rs.getString("fixReport");
                bug bug = new bug(id, type, severity, assignedTo, reportTime.toLocalTime(), fixTime.toLocalTime(), description, reportedBy, fixReport);
                bugs.add(bug);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bugs;
    }

    public void insertBugs() {
        try {
            String sql = "INSERT INTO bugs (ID, type, severity, assignedToID, reportTime, fixTime, description, reportedBy, fixReport) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);

            for (bug bug : globals.totalBugs) {
                stmt.setInt(1, bug.ID);
                stmt.setInt(2, bug.type);
                stmt.setInt(3, bug.severity);
                stmt.setInt(4, bug.assignedTo);
                stmt.setTime(5, Time.valueOf(bug.reportTime));
                stmt.setTime(6, Time.valueOf(bug.fixTime));
                stmt.setString(7, bug.description);
                stmt.setString(8, bug.reportedBy);
                stmt.setString(9, bug.fixReport);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //fixedBug
    public void clearFixedBugTable() {
        try {
            Statement stmt = conn.createStatement();
            String sql = "TRUNCATE TABLE fixedbugs";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public LinkedList<bug> getAllFixedBugs() {
        LinkedList<bug> bugs = new LinkedList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM fixedbugs");
            while (rs.next()) {
                int id = rs.getInt("ID");
                int type = rs.getInt("type");
                int severity = rs.getInt("severity");
                int assignedTo = rs.getInt("assignedToID");
                Time reportTime = rs.getTime("reportTime");
                Time fixTime = rs.getTime("fixTime");
                String description = rs.getString("description");
                String reportedBy = rs.getString("reportedBy");
                String fixReport = rs.getString("fixReport");
                bug bug = new bug(id, type, severity, assignedTo, reportTime.toLocalTime(), fixTime.toLocalTime(), description, reportedBy, fixReport);
                bugs.add(bug);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bugs;
    }

    public void insertFixedBugs() {
        try {
            String sql = "INSERT INTO fixedbugs (ID, type, severity, assignedToID, reportTime, fixTime, description, reportedBy, fixReport) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);

            for (bug bug : globals.fixedBugs) {
                stmt.setInt(1, bug.ID);
                stmt.setInt(2, bug.type);
                stmt.setInt(3, bug.severity);
                stmt.setInt(4, bug.assignedTo);
                stmt.setTime(5, Time.valueOf(bug.reportTime));
                stmt.setTime(6, Time.valueOf(bug.fixTime));
                stmt.setString(7, bug.description);
                stmt.setString(8, bug.reportedBy);
                stmt.setString(9, bug.fixReport);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //employees
    public void clearEmployeeTable() {
        try {
            Statement stmt = conn.createStatement();
            String sql = "TRUNCATE TABLE employees";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertEmployees() {
        try {
            String sql = "INSERT INTO employees (ID, rank, name, phoneNumber, password, userName) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            for (Employee employee : globals.employeeLinkedList) {
                stmt.setInt(1, employee.ID);
                stmt.setInt(2, employee.Rank);
                stmt.setString(3, employee.name);
                stmt.setString(4, employee.phoneNumber);
                stmt.setString(5, employee.password);
                stmt.setString(6, employee.userName);
                stmt.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public LinkedList<Employee> getEmployees() {
        LinkedList<Employee> employees = new LinkedList<>();
        try {
            String sql = "SELECT * FROM employees";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Employee employee = new Employee(
                        rs.getInt("ID"),
                        rs.getInt("rank"),
                        rs.getString("name"),
                        rs.getString("phoneNumber"),
                        rs.getString("password"),
                        rs.getString("userName"));
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }



}