package Jdbc_file_database;

import java.io.File;
import java.io.FileWriter;
import java.sql.*;

public class HtmlFile {
    public static void main(String[] args) {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            // Load Oracle JDBC driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Connect to Oracle DB
            con = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");

            // Create Statement and Execute Query
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM employee");

            // Create File object with absolute path
            File file = new File("employee.html");
            FileWriter fw = new FileWriter(file);

            // Write HTML structure
            fw.write("<html><head><title>Employee Data</title></head><body>");
            fw.write("<h2>Employee Details</h2>");
            fw.write("<table border='1' cellpadding='8' cellspacing='0'>");

            // Table Header
            ResultSetMetaData meta = rs.getMetaData();
            int columnCount = meta.getColumnCount();
            fw.write("<tr style='background-color:#f2f2f2;'>");
            for (int i = 1; i <= columnCount; i++) {
                fw.write("<th>" + meta.getColumnName(i) + "</th>");
            }
            fw.write("</tr>");

            // Table Rows
            while (rs.next()) {
                fw.write("<tr>");
                for (int i = 1; i <= columnCount; i++) {
                    fw.write("<td>" + rs.getString(i) + "</td>");
                }
                fw.write("</tr>");
            }

            // Close HTML
            fw.write("</table></body></html>");
            fw.close();

            // Force update: Print absolute path
            System.out.println("✅ Data exported successfully!");
            System.out.println("📁 File saved at: " + file.getAbsolutePath());

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
