package jdbccomplete;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.*;

public class StudentDAO {
    static Connection conn = null;
    static Statement stm = null;

    static {
        try {
            conn = Student.getConnection();
            stm = conn.createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("❌ Database connection failed: " + e.getMessage());
        }
    }

    // 1️⃣ CREATE TABLE
    public static void createTable(BufferedReader br) throws SQLException {
        stm.execute("CREATE TABLE Student1 (" +
                "S_regNo NUMBER PRIMARY KEY, " +
                "S_name VARCHAR2(25), " +
                "S_per NUMBER(10,2), " +
                "S_address VARCHAR2(20))");
        System.out.println("✅ Table Created Successfully...");
    }

    // 2️⃣ INSERT RECORDS
    public static void insertRecords(BufferedReader br) throws IOException, SQLException {
        do {
            System.out.print("Enter Student regno: ");
            int regno = Integer.parseInt(br.readLine());
            System.out.print("Enter Student name: ");
            String name = br.readLine();
            System.out.print("Enter the Student percentage: ");
            float per = Float.parseFloat(br.readLine());
            System.out.print("Enter the Student Address: ");
            String add = br.readLine();

            String sql = "INSERT INTO Student1 (S_regNo, S_name, S_per, S_address) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, regno);
            pst.setString(2, name);
            pst.setFloat(3, per);
            pst.setString(4, add);
            pst.executeUpdate();

            System.out.println("✅ Student inserted successfully...");
            System.out.print("Add one more student [Yes/No]?: ");
        } while (br.readLine().equalsIgnoreCase("Yes"));
        StudentManagement.main(null);
    }

    // 3️⃣ PRINT RECORDS
    public static void printRecords(BufferedReader br) throws SQLException, IOException {
        do {
            System.out.println("\nWhich records do you want to print?");
            System.out.println("1. S_regNo");
            System.out.println("2. S_name");
            System.out.println("3. S_per");
            System.out.println("4. S_address");
            System.out.println("5. All Student Records");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(br.readLine());

            ResultSet rs;
            switch (choice) {
                case 1 -> rs = stm.executeQuery("SELECT S_regNo FROM Student1");
                case 2 -> rs = stm.executeQuery("SELECT S_name FROM Student1");
                case 3 -> rs = stm.executeQuery("SELECT S_per FROM Student1");
                case 4 -> rs = stm.executeQuery("SELECT S_address FROM Student1");
                case 5 -> rs = stm.executeQuery("SELECT * FROM Student1");
                default -> {
                    System.out.println("Invalid choice!");
                    return;
                }
            }

            System.out.println("\n--- Student Records ---");
            while (rs.next()) {
                if (choice == 5)
                    System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getFloat(3) + " " + rs.getString(4));
                else
                    System.out.println(rs.getString(1));
            }

            System.out.print("Do you want to continue [Yes/No]?: ");
        } while (br.readLine().equalsIgnoreCase("Yes"));
        StudentManagement.main(null);
    }

    // 4️⃣ PRINT BY PERCENTAGE
    public static void printPercentage(BufferedReader br) throws IOException, SQLException {
        do {
            System.out.println("\n1. Greater than given percentage");
            System.out.println("2. Less than given percentage");
            System.out.println("3. Equal to given percentage");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(br.readLine());
            System.out.print("Enter the percentage: ");
            float perc = Float.parseFloat(br.readLine());

            String query = switch (choice) {
                case 1 -> "SELECT * FROM Student1 WHERE S_per > " + perc;
                case 2 -> "SELECT * FROM Student1 WHERE S_per < " + perc;
                case 3 -> "SELECT * FROM Student1 WHERE S_per = " + perc;
                default -> null;
            };

            if (query == null) {
                System.out.println("Invalid choice!");
                return;
            }

            ResultSet rs = stm.executeQuery(query);
            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getFloat(3) + " " + rs.getString(4));
            }
            if (!found)
                System.out.println("No student found for given condition.");

            System.out.print("Do you want to continue [Yes/No]?: ");
        } while (br.readLine().equalsIgnoreCase("Yes"));
        StudentManagement.main(null);
    }

    // 5️⃣ UPDATE RECORDS
    public static void updateRecords(BufferedReader br) throws IOException, SQLException {
        do {
            System.out.println("\n--- Which record do you want to update ---");
            System.out.println("1. S_name");
            System.out.println("2. S_per");
            System.out.println("3. S_address");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(br.readLine());

            System.out.print("Enter RegNo: ");
            int regno = Integer.parseInt(br.readLine());
            int rowsAffected = 0;

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter new Name: ");
                    String name = br.readLine();
                    rowsAffected = stm.executeUpdate("UPDATE Student1 SET S_name = '" + name + "' WHERE S_regNo = " + regno);
                }
                case 2 -> {
                    System.out.print("Enter new Percentage: ");
                    float per = Float.parseFloat(br.readLine());
                    rowsAffected = stm.executeUpdate("UPDATE Student1 SET S_per = " + per + " WHERE S_regNo = " + regno);
                }
                case 3 -> {
                    System.out.print("Enter new Address: ");
                    String add = br.readLine();
                    rowsAffected = stm.executeUpdate("UPDATE Student1 SET S_address = '" + add + "' WHERE S_regNo = " + regno);
                }
                default -> System.out.println("Invalid choice!");
            }

            System.out.println(rowsAffected == 1 ? "✅ Updated successfully." : "❌ Invalid RegNo");
            System.out.print("Do you want to continue [Yes/No]?: ");
        } while (br.readLine().equalsIgnoreCase("Yes"));
        StudentManagement.main(null);
    }

    // 6️⃣ DELETE RECORDS
    public static void deleteRecords(BufferedReader br) throws IOException, SQLException {
        do {
            ResultSet rs = stm.executeQuery("SELECT S_regNo FROM Student1");
            System.out.println("\nAvailable RegNos:");
            while (rs.next()) System.out.println(rs.getInt(1));

            System.out.print("Enter RegNo to delete: ");
            int regno = Integer.parseInt(br.readLine());
            int rows = stm.executeUpdate("DELETE FROM Student1 WHERE S_regNo = " + regno);
            System.out.println(rows == 1 ? "✅ Deleted successfully." : "❌ Invalid RegNo");

            System.out.print("Do you want to delete more [Yes/No]?: ");
        } while (br.readLine().equalsIgnoreCase("Yes"));
        StudentManagement.main(null);
    }

    // 7️⃣ DROP TABLE
    public static void dropTable(BufferedReader br) throws SQLException {
        stm.execute("DROP TABLE Student1");
        System.out.println("✅ Table dropped successfully!");
        conn.close();
        stm.close();
    }
}
