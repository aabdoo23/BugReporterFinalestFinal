# BugReporterFinalestFinal
Bug Report Application

This is a bug report application written in Java using Swing for GUI and linked to a MySQL database. The application contains the following classes:

EmployeeLogin: Allows employees to login to the system using their username and password.
EmployeeInterface: Provides the interface for employees to report bugs, view their assigned bugs, and update the status of the bugs they are assigned to.
BugReport: Represents a bug report in the system.
MainMenu: Provides the main menu interface for the application.
NewEmployee: Allows administrators to add new employees to the system.
Admin: Provides the interface for administrators to manage employees and view bug reports.
Employee: Represents an employee in the system.
Bug: Represents a bug in the system.
Global LinkedLists: The application maintains four global LinkedLists to keep track of employees, unfixed bugs, fixed bugs. These lists are used to update the database.
Usage
To use the application, run the Main class. The application will display a main menu screen where employees can log in by entering their username and password. Once logged in, employees will see the EmployeeInterface, which allows them to report new bugs, view their assigned bugs, and update the status of bugs they are assigned to.

Administrators can access the Admin interface from the main menu. From there, they can manage employees and view bug reports.

Database Setup
The application requires a MySQL database to store data. To set up the database, run the following SQL commands:

CREATE DATABASE bugsreports;

USE bugsreports;

CREATE TABLE bugs (
    ID INT(200) NOT NULL,
    type INT(20) NOT NULL,
    severity INT(20) NOT NULL,
    assignedToID INT(200),
    reportTime VARCHAR(30) NOT NULL,
    fixTime VARCHAR(30),
    description VARCHAR(500),
    reportedBy VARCHAR(50),
    fixReport VARCHAR(500)
);

CREATE TABLE fixedbugs (
    ID INT(200) NOT NULL,
    type INT(20) NOT NULL,
    severity INT(20) NOT NULL,
    assignedToID INT(200),
    reportTime VARCHAR(30) NOT NULL,
    fixTime VARCHAR(30),
    description VARCHAR(500),
    reportedBy VARCHAR(50),
    fixReport VARCHAR(500)
);

CREATE TABLE employees (
    ID INT(200) NOT NULL,
    rank INT(20) NOT NULL,
    name VARCHAR(50) NOT NULL,
    phoneNumber VARCHAR(15),
    password VARCHAR(30) NOT NULL,
    userName VARCHAR(30) NOT NULL
);


Dependencies
The application uses the following dependencies:

MySQL Connector/J 8.0.25
JUnit 5.7.2 (for testing)
Testing
The application includes JUnit tests for the Employee, Bug, and EmployeeBug classes. To run the tests, run the TestSuite class.

Contributing
If you find a bug or would like to suggest an enhancement, please open an issue on GitHub. Pull requests are also welcome.
