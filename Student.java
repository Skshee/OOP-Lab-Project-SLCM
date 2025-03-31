package SLCMProject;

import java.util.ArrayList;
import java.util.Scanner;

class InvalidUsernameException extends Exception { // Custom exception if wrong username is entered
    public InvalidUsernameException(String message) {
        super(message);
    }
}

class InvalidPasswordException extends Exception { // Custom exception if wrong password is entered
    public InvalidPasswordException(String message) {
        super(message);
    }
}

class StudentProfile {
    protected int RegistrationNumber;
    protected String Name;
    protected String Email;
    protected String Password;
    protected String Address;
    protected long PhoneNumber;
    protected String Course;
    protected int Semester;
    protected int Year;
    protected String Department;
    protected int RollNo;
    protected Marks marks;

    public StudentProfile(int RegistrationNumber, String Name, String Email, String Password, String Address,
                          long PhoneNumber, String Course, int Semester, int Year, String Department, int RollNo) {
        this.RegistrationNumber = RegistrationNumber;
        this.Name = Name;
        this.Email = Email;
        this.Password = Password;
        this.Address = Address;
        this.PhoneNumber = PhoneNumber;
        this.Course = Course;
        this.Semester = Semester;
        this.Year = Year;
        this.Department = Department;
        this.RollNo = RollNo;
    }

    public int getRegistrationNumber() {
        return RegistrationNumber;
    }

    public String getPassword() {
        return Password;
    }

    public void setMarks(int[] marksArr, int[] credits) {
        marks = new Marks(marksArr, credits);
    }

}

// class Attendance {
//     public void rollNumberWise(int rollNo) {
//         System.out.println("Displaying attendance for Roll No: " + rollNo);
//         // Dummy implementation
//     }
// }

class StudentLogin {
    private ArrayList<StudentProfile> students; // ArrayList to store student profiles

    public StudentLogin(ArrayList<StudentProfile> students) {
        this.students = students;
    }

    public StudentProfile validateCredentials(int registrationNumber, String password) throws InvalidUsernameException, InvalidPasswordException {
        for (StudentProfile student : students) {
            if (student.getRegistrationNumber() == registrationNumber) {
                if (student.getPassword().equals(password)) {
                    System.out.println("Login Successful");
                    return student;
                } else {
                    throw new InvalidPasswordException("Wrong Password entered");
                }
            }
        }
        throw new InvalidUsernameException("Wrong Username entered");
    }

    public void displayProfile(StudentProfile student) {
        if (student != null) {
            System.out.println("Name: " + student.Name);
            System.out.println("Registration Number: " + student.getRegistrationNumber());
            System.out.println("Email: " + student.Email);
            System.out.println("Address: " + student.Address);
            System.out.println("Phone Number: " + student.PhoneNumber);
            System.out.println("Course: " + student.Course);
            System.out.println("Semester: " + student.Semester);
            System.out.println("Year: " + student.Year);
            System.out.println("Department: " + student.Department);
            System.out.println("Roll No: " + student.RollNo);
        }
    }

    public void displayAttendance(StudentProfile student, Attendance attendance) {
        attendance.rollNumberWise(student.RollNo);
    }
}

public class Student {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Attendance attendance = new Attendance();

        ArrayList<StudentProfile> students = new ArrayList<>();
        students.add(new StudentProfile(
                230953380,
                "Suvan Kumar Shee",
                "blahblah@gmail.com",
                "Noobmaster69",
                "123 Elm Street",
                7995724447L,
                "CCE",
                3,
                2024,
                "ICT",
                44
        ));

        students.add(new StudentProfile(
                230953440,
                "Armaan Gupta",
                "PoseidonNigga@gmail.com",
                "Yapmaster3000",
                "Bangalore",
                696969696L,
                "CCE",
                3,
                2024,
                "ICT",
                53
        ));

        students.add(new StudentProfile(
                23095382,
                "Madhav",
                "SonGoku@Yahoo.com",
                "GopiRizzer123",
                "Vaikuntha",
                123456789L,
                "CCE",
                3,
                2024,
                "ICT",
                45
        ));

        StudentLogin studentLogin = new StudentLogin(students);

        System.out.println("Enter Username (registration number):");
        int username = sc.nextInt();

        System.out.println("Enter Password:");
        String password = sc.next();

        StudentProfile loginStudent = null;
        try {
            loginStudent = studentLogin.validateCredentials(username, password);
        } catch (InvalidUsernameException u) {
            System.out.println("Exception: " + u.getMessage());
        } catch (InvalidPasswordException p) {
            System.out.println("Exception: " + p.getMessage());
        }

        if (loginStudent != null) {
            while (true) {
                System.out.println("Choose an option:");
                System.out.println("1. View Profile");
                System.out.println("2. View Attendance");
                System.out.println("3. Exit");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("Profile Details:");
                        studentLogin.displayProfile(loginStudent);
                        break;
                    case 2:
                        System.out.println("Attendance Details:");
                        studentLogin.displayAttendance(loginStudent, attendance);
                        break;
                    case 3:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid Choice. Try again.");
                        break;
                }
            }
        }
        sc.close();
    }
}
