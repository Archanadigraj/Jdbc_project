package jdbccomplete;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class StudentManagement {
    public static void main(String[] args) throws NumberFormatException, IOException, SQLException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.println("\n------------ Student Management System -----------");
            System.out.println("1. Create Student Table");
            System.out.println("2. Insert Student Records");
            System.out.println("3. Print Student Records");
            System.out.println("4. Print by Percentage");
            System.out.println("5. Update Student Records");
            System.out.println("6. Delete Student Records");
            System.out.println("7. Drop Table");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            int choice = Integer.parseInt(br.readLine());
            switch (choice) {
                case 1 -> StudentDAO.createTable(br);
                case 2 -> StudentDAO.insertRecords(br);
                case 3 -> StudentDAO.printRecords(br);
                case 4 -> StudentDAO.printPercentage(br);
                case 5 -> StudentDAO.updateRecords(br);
                case 6 -> StudentDAO.deleteRecords(br);
                case 7 -> StudentDAO.dropTable(br);
                case 8 -> {
                    System.out.println("👋 Thank you! Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again!");
            }
        }
    }
}
