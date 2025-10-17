package Jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PreparedStmtWithBatch {
	public static void main(String[] args) throws ClassNotFoundException, SQLException 
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
		 java.sql.PreparedStatement pst=con.prepareStatement("insert into emp1 values(?,?,?,?)");
		 pst.setInt(1, 124);
		 pst.setString(2, "aru");
		 pst.setFloat(3, 600);
		 pst.setString(4, "bnglr");
		 pst.addBatch();
		 
		 pst.setInt(1, 125);
		 pst.setString(2, "abhi");
		 pst.setFloat(3, 800);
		 pst.setString(4, "dvg");
		 pst.addBatch();
		 
		 pst.setInt(1, 126);
		 pst.setString(2, "charu");
		 pst.setFloat(3, 900);
		 pst.setString(4, "blgm");
		 pst.addBatch();
		 
		 int [] row=pst.executeBatch();
		 for(int i=0;i<row.length;i++) {
			 System.out.println("records manupulited"+ row[i]);
		 }
		 con.close();
		 
}

}
