package Jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Forwardonly_readonly {
	public static void main(String[] args) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
		Statement st= con.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY);
		
		ResultSet rs=st.executeQuery("SELECT *FROM  EMP");
		System.out.println("employee details");
		while(rs.next()) {
			System.out.println(rs.getInt(1)+" " +rs.getString(2));
		}
		rs.close();
		st.close();
		con.close();
		
	}

}
