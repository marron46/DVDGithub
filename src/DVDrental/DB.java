package DVDrental;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DB {
	
	public static void insertMember(String id, String name) {
		try(Connection conn = DriverManager.getConnection(URL,USER,PASS);
				PreparedStatement ps = conn.prepareStatement("INSERT INTO member(id,name)VALUES(?,?)")){
				ps.setString(1, id);
				ps.setString(2, name);
				ps.executeUpdate();
			}catch(SQLException e) {
				e.printStackTrace();
			}
	}
	
	public static void insertDVD(String id, String name) {
		try(Connection conn = DriverManager.getConnection(URL,USER,PASS);
				PreparedStatement ps = conn.prepareStatement("INSERT INTO member(CodeField,TitleFeild)VALUES(?,?)")){
				ps.setString(1, CodeField);
				ps.setString(2, TitleFeild);
				ps.executeUpdate();
			}catch(SQLException e) {
				e.printStackTrace();
			}
	}
	
	public static void insertRent(String memberid,String DVDcode) {
		try(Connection conn = DriverManager.getConnection(URL,USER,PASS);
				PreparedStatement ps = conn.prepareStatement("UPDATE dvd SET is_lent = true WHERE code = ?")){
				ps.setString(1,DVDcode);
				ps.executeUpdate();
			}catch(SQLException e){
				e.printStackTrace();
			}
	}

}
