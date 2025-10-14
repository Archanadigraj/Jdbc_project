package Jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Insensitive_readonly {
	public static void main(String[] args) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
		Statement st= con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		
		ResultSet rs=st.executeQuery("SELECT *FROM  EMP");
		rs.last();System.out.println("last employee "+ rs.getString("ENAME"));
		rs.first();System.out.println("first employee "+ rs.getString("ENAME"));
		rs.beforeFirst();System.out.println("cursor move before first  employee");
		rs.close();
		st.close();
		con.close();
	}

}
