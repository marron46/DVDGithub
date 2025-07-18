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
	
	public static void insertDVDs(String Code, String Title,String total) {
		try(Connection conn = DriverManager.getConnection(URL,USER,PASS);
				PreparedStatement ps = conn.prepareStatement("INSERT INTO dvd(Code,Title,total,Is_lent)VALUES(?,?,?,false)")){
				ps.setString(1,Code);
				ps.setString(2,Title);
				ps.setString(3,total);
				ps.executeUpdate();
			}catch(SQLException e) {
				e.printStackTrace();
			}
	}
	
	public static void insertRent(String memberid,String DVDcode) {
		try(Connection conn = DriverManager.getConnection(URL,USER,PASS);
				PreparedStatement ps = conn.prepareStatement("UPDATE dvd SET Is_lent = true WHERE code = ?")){
				ps.setString(1,DVDcode);
				ps.executeUpdate();
			}catch(SQLException e){
				e.printStackTrace();
			}
	}
	
	public static void insertBackDVD(String DVDcode) {
		try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement ps = conn.prepareStatement("UPDATE dvd SET Is_lent = false WHERE code = ?")) {
			ps.setString(1, DVDcode);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}