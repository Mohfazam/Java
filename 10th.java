// 10. Write a JDBC program to implement CURD operation


import java.sql.*;
import java.util.Scanner;

public class StudentJDBCApp {
    static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
    static final String USER = "system";
    static final String PASS = "system";  

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();

            while (true) {
                System.out.println("\n1. Add Student");
                System.out.println("2. View Students");
                System.out.println("3. Update Student Grade");
                System.out.println("4. Delete Student");
                System.out.println("5. Exit");
                System.out.print("Choose an option: ");
                int choice = sc.nextInt();
                sc.nextLine(); // consume newline

                switch (choice) {
                    case 1:
						System.out.print("Enter ID: ");
                        int id = sc.nextInt();
						sc.nextLine();
                        System.out.print("Enter name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter grade: ");
                        int grade = sc.nextInt();
                        String insert = "INSERT INTO students (id,name, grade) VALUES ("+id+",'" + name + "', " + grade + ")";
                        stmt.executeUpdate(insert);
                        System.out.println("Student added.");
                        break;

                    case 2:
                        ResultSet rs = stmt.executeQuery("SELECT * FROM students");
                        System.out.println("ID | Name | Grade");
                        while (rs.next()) {
                            System.out.println(rs.getInt("id") + " | " + rs.getString("name") + " | " + rs.getInt("grade"));
                        }
                        break;

                    case 3:
                        System.out.print("Enter student ID: ");
                        int id1 = sc.nextInt();
                        System.out.print("Enter new grade: ");
                        int newGrade = sc.nextInt();
                        stmt.executeUpdate("UPDATE students SET grade = " + newGrade + " WHERE id = " + id1);
                        System.out.println("Grade updated.");
                        break;

                    case 4:
                        System.out.print("Enter student ID to delete: ");
                        int delId = sc.nextInt();
                        stmt.executeUpdate("DELETE FROM students WHERE id = " + delId);
                        System.out.println("Student deleted.");
                        break;

                    case 5:
                        System.out.println("Goodbye!");
                        stmt.close();
                        conn.close();
                        sc.close();
                        return;

                    default:
                        System.out.println("Invalid choice.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
