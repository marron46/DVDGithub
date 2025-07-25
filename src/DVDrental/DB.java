package DVDrental;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DB {
	//DBアクセス＆ユーザー名とパスワード
	private static final String URL = "jdbc:mysql://localhost/dvdrental";
	private static final String USER = "root";
	private static final String PASS = "";
	
	//会員の登録
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
	
	//DVDの登録
	public static void insertDVDs(String Code, String Title,String total) {
		try(Connection conn = DriverManager.getConnection(URL,USER,PASS);
				PreparedStatement ps = conn.prepareStatement("INSERT INTO dvd(Code,Title,total)VALUES(?,?,?)")){
				ps.setString(1,Code);
				ps.setString(2,Title);
				ps.setString(3,total);
				ps.executeUpdate();
			}catch(SQLException e) {
				e.printStackTrace();
			}
	}
	
	//DVDの貸出処理
	public static boolean insertRent(String Id, String Code) {
        try (Connection conn = DriverManager.getConnection(URL,USER,PASS);
             PreparedStatement checkStmt = conn.prepareStatement("SELECT total FROM dvd WHERE code = ?");
             PreparedStatement updateStmt = conn.prepareStatement("UPDATE dvd SET count = count + 1, total = total - 1 WHERE code = ? AND total > 0")) {

            checkStmt.setString(1, Code);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next()) {
                int total = rs.getInt("total");
                if (total <= 0) {
                    return false;
                }
                updateStmt.setString(1, Code);
                updateStmt.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
	
	//DVDの返却処理
	public static void insertBackDVD(String dvdCode) {
        try (Connection conn = DriverManager.getConnection(URL,USER,PASS);
             PreparedStatement stmt = conn.prepareStatement("UPDATE dvd SET total = total + 1 WHERE code = ?")) {
            stmt.setString(1, dvdCode);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	//登録済みのDVDの一覧表示
	public static ArrayList<String> listAll() {
		ArrayList<String> list = new ArrayList<>();
		try(Connection conn = DriverManager.getConnection(URL,USER,PASS);
				Statement stmt = conn.createStatement();
				ResultSet result = stmt.executeQuery("SELECT * FROM dvd")){
			while (result.next()) {
                String line = "コード: " + result.getString("code") +
                              ", タイトル: " + result.getString("title") +
                              ", 在庫: " + result.getInt("total");
                list.add(line);
            }
				
			}catch(SQLException e) {
				e.printStackTrace();
				
			}
		return list;
	}

}