package DVDrental;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DB {
	
	private static final String URL = "jdbc:mysql://localhost/dvdrental";
	private static final String USER = "root";
	private static final String PASS = "";
	
	public static void insertMember(String Id, String Name) {
		try(Connection conn = DriverManager.getConnection(URL,USER,PASS);
				PreparedStatement ps = conn.prepareStatement("INSERT INTO member(Id,Name)VALUES(?,?)")){
				ps.setString(1, Id);
				ps.setString(2, Name);
				ps.executeUpdate();
			}catch(SQLException e) {
				e.printStackTrace();
			}
	}
	
	public static void insertDVDs(String CodeField, String TitleField) {
		try(Connection conn = DriverManager.getConnection(URL,USER,PASS);
				PreparedStatement ps = conn.prepareStatement("INSERT INTO dvd(CodeField,TitleField)VALUES(?,?)")){
				ps.setString(1,CodeField);
				ps.setString(2,TitleField);
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
	
	public static void insertBackDVD(String DVDcode) {
		try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement ps = conn.prepareStatement("UPDATE book SET is_lent = false WHERE code = ?")) {
			ps.setString(1, DVDcode);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}