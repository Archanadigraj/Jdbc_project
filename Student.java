package jdbccomplete;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Student {

    private static Connection con = null;

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        String url = "jdbc:oracle:thin:@localhost:1521:orcl";
        String username = "scott";
        String password = "tiger";

        try {
			   Class.forName("oracle.jdbc.driver.OracleDriver");
		 con = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return con;
    }
}
