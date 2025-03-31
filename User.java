package SLCMProject;

import java.util.ArrayList;
import java.util.Scanner;

public class User {

    static ArrayList<StudentProfile> students;
    static Attendance[] attendance;
    public static final int courses = 8;
    public static final int[] credits = {4, 4, 4, 3, 3, 1, 1, 1};

    static {
        students = new ArrayList<>();
        students.add(new StudentProfile(
                230953380,
                "Suvan Kumar Shee",
                "suvan@gmail.com",
                "HelloWorld",
                "Hyderabad",
                11111111L,
                "CCE",
                3,
                2024,
                "ICT",
                1
        ));

        students.add(new StudentProfile(
                230953440,
                "Armaan Gupta",
                "armaan@gmail.com",
                "Poseidon3000",
                "Bangalore",
                999999999L,
                "CCE",
                3,
                2024,
                "ICT",
                2
        ));

        students.add(new StudentProfile(
                23095382,
                "Gokul Nair",
                "gokul@gmail.com",
                "Madhav789",
                "Delhi",
                123456789L,
                "CCE",
                3,
                2024,
                "ICT",
                3
        ));
    }

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        Attendance[] attendance = new Attendance[courses];
        for (int i = 0; i < courses; i++)
            attendance[i] = new Attendance(credits[i]);

        LOGIN:
        while (true) {
            System.out.print("1. Admin Login\n2. Student login\n"+
                             "3. Register\n4. Exit\nEnter choice: ");
            char accChoice = scan.next().charAt(0);
            scan.nextLine();
            if (accChoice == '1') {

                System.out.println("Enter Username:");
                String username = scan.nextLine();

                System.out.println("Enter Password:");
                String password = scan.nextLine();

                boolean authenticated = false;
                try {
                    authenticated = Admin.authenticate(username, password);
                } catch (InvalidUsernameException | InvalidPasswordException I) {
                    System.out.println(I.getMessage());
                }

                if (!authenticated)
                    continue LOGIN;

                System.out.println("1. Update attendance\n2. Update marks\n3. Logout");

                while (true) {
                    System.out.print("Enter choice: ");
                    char ch = scan.next().charAt(0);

                    if (ch == '1') {
                        System.out.print("Enter month and day: ");
                        int month = scan.nextInt(), day = scan.nextInt();
                        System.out.print("1. OOP\n2. DS\n3. DSCO\n4. PDC\n5. Maths\n"+
                                         "6. OOP Lab\n7. DS Lab\n8. DSCO Lab\nEnter choice: ");
                        int subject = scan.nextInt();
                        if (subject > courses || subject < 1) {
                            System.out.println("Invalid subject");
                            continue;
                        }
                        attendance[subject].markAttendance(month, day, students.size());
                    }
                    if (ch == '2') {
                        System.out.print("Enter student registration number: ");
                        long regno = scan.nextLong();
                        StudentProfile student = null;
                        for (StudentProfile stud : students) {
                            if (stud.getRegistrationNumber() == regno)
                                student = stud;
                        }
                        if (student == null) {
                            System.out.println("Student not found");
                            continue;
                        }
                        System.out.println("Subjects:\n1. OOP\n2. DS\n3. DSCO\n4. PDC\n5. Maths\n"+
                                           "6. OOP Lab\n7. DS Lab\n8. DSCO Lab");
                        int[] marksArr = new int[courses];
                        for (int i = 0; i < courses; i++) {
                            System.out.print("Enter marks for subject "+(i+1)+": ");
                            marksArr[i] = scan.nextInt();
                        }
                        student.setMarks(marksArr, credits);
                    }
                    if (ch == '3') {
                        System.out.println("Logged out successfully!");
                        continue LOGIN;
                    }

                }

            } else if (accChoice == '2') {

                StudentLogin studentLogin = new StudentLogin(students);

                System.out.println("Enter Username (registration number):");
                int username = scan.nextInt();
                scan.nextLine();

                System.out.println("Enter Password:");
                String password = scan.nextLine();

                StudentProfile loginStudent = null;
                try {
                    loginStudent = studentLogin.validateCredentials(username, password);
                } catch (InvalidUsernameException | InvalidPasswordException u) {
                    System.out.println("Exception: " + u.getMessage());
                }

                if (loginStudent == null)
                    continue LOGIN;

                while (true) {
                    System.out.println("Choose an option:");
                    System.out.println("1. View Profile");
                    System.out.println("2. View Attendance");
                    System.out.println("3. View marks");
                    System.out.println("4. Log out");
                    int choice = scan.nextInt();

                    switch (choice) {
                        case 1:
                            System.out.println("Profile Details:");
                            studentLogin.displayProfile(loginStudent);
                            break;
                        case 2:
                            System.out.println("Attendance Details:");
                            System.out.print("1. OOP\n2. DS\n3. DSCO\n4. PDC\n5. Maths\n" +
                                    "6. OOP Lab\n7. DS Lab\n8. DSCO Lab\nEnter choice: ");
                            int subject = scan.nextInt();
                            if (subject > courses || subject < 1) {
                                System.out.println("Invalid subject");
                                continue;
                            }
                            studentLogin.displayAttendance(loginStudent, attendance[subject]);
                            break;
                        case 3:
                            if (loginStudent.marks == null) {
                                System.out.println("Marks not yet uploaded");
                                break;
                            }
                            int[] marksArr = loginStudent.marks.getMarks();
                            System.out.println("OOP\tDS\tDSCO\tPDC\tMaths\tOOPLab\tDSLab\tDSCOLab");
                            for (int i = 0; i < courses; i++)
                                System.out.printf("%d\t", marksArr[i]);
                            System.out.println("\nGPA: " + loginStudent.marks.GPA());
                            break;
                        case 4:
                            System.out.println("Logged out successfully!");
                            continue LOGIN;
                        default:
                            System.out.println("Invalid Choice. Try again.");
                            break;
                    }
                }
            } else if (accChoice == '3') {
                System.out.print("Enter registration number: ");
                int regno = scan.nextInt();
                scan.nextLine();
                for (StudentProfile student : students) {
                    if (student.getRegistrationNumber() == regno) {
                        System.out.println("Student already exists");
                        continue LOGIN;
                    }
                }
                System.out.print("Enter name: ");
                String name = scan.nextLine(), email, password, address, course, dept;
                System.out.print("Enter email: ");
                email = scan.nextLine();
                boolean found_at = false;
                for (int i = 0; i < email.length(); i++) {
                    if (email.charAt(i) == '@') {
                        found_at = true;
                        break;
                    }
                }
                if (!found_at) {
                    System.out.println("Invalid email");
                    continue LOGIN;
                }
                System.out.print("Enter password: ");
                password = scan.nextLine();
                System.out.print("Enter address: ");
                address = scan.nextLine();
                System.out.print("Enter phone number: ");
                long phno = scan.nextLong();
                System.out.print("Enter department, course, semester and year of admission: ");
                dept = scan.next();
                course = scan.next();
                int semester = scan.nextInt(), year = scan.nextInt();
                students.add(new StudentProfile(regno, name, email, password, address, phno, course,
                                                semester, year, dept, students.size()+1));
                System.out.print("Registered successfully!");
            } else if (accChoice == '4') {
                return;
            } else {
                System.out.println("Invalid choice");
            }
        }
    }
}