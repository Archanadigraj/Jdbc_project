package Jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class sensitive_update {
	public static void main(String[] args) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
		Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ResultSet rs = st.executeQuery("SELECT empno, ename, sal, deptno FROM emp");
		rs.absolute(2);
		float oldSal = rs.getFloat("sal");
		System.out.println("Old salary: " + oldSal);
		rs.updateFloat("sal", oldSal + 1000);
		rs.updateRow();
		rs.refreshRow();
		float newSal = rs.getFloat("sal");
		System.out.println("Updated salary: " + newSal);
		rs.close();
		st.close();
		con.close();
	}
}
