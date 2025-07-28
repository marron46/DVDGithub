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
		try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {

			// 在庫確認
			PreparedStatement checkStmt = conn.prepareStatement("SELECT total FROM dvd WHERE code = ?");
			checkStmt.setString(1, Code);
			ResultSet rs = checkStmt.executeQuery();

			if (rs.next() && rs.getInt("total") > 0) {
				// 在庫更新
				PreparedStatement updateStmt = conn.prepareStatement(
						"UPDATE dvd SET count = count + 1, total = total - 1 WHERE code = ?");
				updateStmt.setString(1, Code);
				updateStmt.executeUpdate();

				// レンタル記録
				PreparedStatement insertStmt = conn.prepareStatement(
						"INSERT INTO rental (member_id, dvd_code, rent_date) VALUES (?, ?, NOW())");
				insertStmt.setString(1, Id);
				insertStmt.setString(2, Code);
				insertStmt.executeUpdate();

				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	/*try (Connection conn = DriverManager.getConnection(URL,USER,PASS);
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
        return false;*/


	//DVDの返却処理
	public static boolean insertBackDVD(String dvdCode) {
		try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
	        
	        // 最新の未返却のレンタルを取得
	        PreparedStatement selectStmt = conn.prepareStatement(
	            "SELECT id, rent_date FROM rental WHERE dvd_code = ? AND returned = FALSE ORDER BY rent_date LIMIT 1");
	        selectStmt.setString(1, dvdCode);
	        ResultSet rs = selectStmt.executeQuery();
	        
	        if (rs.next()) {
	            int rentalId = rs.getInt("id");
	            java.sql.Timestamp rentDate = rs.getTimestamp("rent_date");
	            
	            // レンタル返却を記録
	            PreparedStatement updateRental = conn.prepareStatement(
	                "UPDATE rental SET return_date = NOW(), returned = TRUE WHERE id = ?");
	            updateRental.setInt(1, rentalId);
	            updateRental.executeUpdate();
	            
	            // DVDの在庫を戻す
	            PreparedStatement updateDvd = conn.prepareStatement(
	                "UPDATE dvd SET total = total + 1 WHERE code = ?");
	            updateDvd.setString(1, dvdCode);
	            updateDvd.executeUpdate();

	            // 延滞判定（7日以内に返却しなければ延滞）
	            long millis = System.currentTimeMillis() - rentDate.getTime();
	            long days = millis / (1000 * 60 * 60 * 24);
	            if (days > 7) {
	                return false; // 延滞あり
	            }
	            
	            return true; // 延滞なし
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return true; // 延滞記録なしでも成功とする
		/*
		try (Connection conn = DriverManager.getConnection(URL,USER,PASS);
				PreparedStatement stmt = conn.prepareStatement("UPDATE dvd SET total = total + 1 WHERE code = ?")) {
			stmt.setString(1, dvdCode);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		*/
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