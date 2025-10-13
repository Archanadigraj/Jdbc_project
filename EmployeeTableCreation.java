package Jdbc_file_database;

import java.io.FileWriter;
import java.sql.*;

public class EmployeeTableCreation {

    public static void main(String[] args) {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            // 1️⃣ Load Oracle JDBC Driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // 2️⃣ Connect to DB
            String url = "jdbc:oracle:thin:@localhost:1521:orcl";
            String username = "scott";
            String password = "tiger";
            con = DriverManager.getConnection(url, username, password);

            // 3️⃣ Execute Query
            String selectQuery = "SELECT * FROM emp";
            st = con.createStatement();
            rs = st.executeQuery(selectQuery);

            // 4️⃣ Build HTML table
            String data = "<table border='1'><tr>"
                    + "<th>EMPNO</th>"
                    + "<th>ENAME</th>"
                    + "<th>JOB</th>"
                    + "<th>MGR</th>"
                    + "<th>HIREDATE</th>"
                    + "<th>SAL</th>"
                    + "<th>COMM</th>"
                    + "<th>DEPTNO</th>"
                    + "<th>ADDRESS</th>"
                    + "</tr>";

            // 5️⃣ Loop through ResultSet and add table rows
            while (rs.next()) {
            	data += "<tr>"
            	        + "<td>" + rs.getInt("EMPNO") + "</td>"
            	        + "<td>" + rs.getString("ENAME") + "</td>"
            	        + "<td>" + rs.getString("JOB") + "</td>"
            	        + "<td>" + rs.getInt("MGR") + "</td>"
            	        + "<td>" + rs.getDate("HIREDATE") + "</td>"
            	        + "<td>" + rs.getInt("SAL") + "</td>"
            	        + "<td>" + rs.getInt("COMM") + "</td>"
            	        + "<td>" + rs.getInt("DEPTNO") + "</td>"
            	        + "<td>" + rs.getString("ADDRESS") + "</td>"
            	        + "</tr>";

            }

            data += "</table>"; // close table

            // 6️⃣ Write HTML file
            FileWriter fw = new FileWriter("emp.html");
            fw.write("<html><head><title>Employee Table</title></head><body>");
            fw.write("<h2>Employee Details</h2>");
            fw.write(data);
            fw.write("</body></html>");
            fw.close();

            System.out.println("✅ Employee table exported successfully!");
            System.out.println("📁 File saved at: " + new java.io.File("emp.html").getAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
