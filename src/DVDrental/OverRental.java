package DVDrental;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.swing.JPanel;

public class OverRental extends JPanel{
	private String dvdTitle;
	private LocalDate rentalDate;
	private LocalDate dueDate;
	private LocalDate returnDate;
	private double lateFeePerDay = 100.0; // 1日あたりの延滞料金（例）

	private Connection conn;


	public OverRental(String dvdTitle, LocalDate rentalDate, int rentalDays) {
		this.dvdTitle = dvdTitle;
		this.rentalDate = rentalDate;
		this.dueDate = rentalDate.plusDays(rentalDays);
	}

	public void returnDVD(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	public boolean isOverdue() {
		if (returnDate == null) return LocalDate.now().isAfter(dueDate);
		return returnDate.isAfter(dueDate);
	}

	public long getLateDays() {
		if (returnDate == null) return 0;
		return Math.max(0, ChronoUnit.DAYS.between(dueDate, returnDate));
	}

	public double calculateLateFee() {
		return getLateDays() * lateFeePerDay;
	}

	public OverRental() throws SQLException {
		conn = DriverManager.getConnection("jdbc:mysql://localhost/dvdrental");
	}

	public void addRental(String dvdTitle, LocalDate rentalDate, int days) throws SQLException {
		String sql = "INSERT INTO rentals (dvdTitle, rental_date, due_date) VALUES (?, ?, ?)";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, dvdTitle);
			stmt.setDate(2, Date.valueOf(rentalDate));
			stmt.setDate(3, Date.valueOf(rentalDate.plusDays(days)));
			stmt.executeUpdate();
		}
	}

	public void returnRental(int rentalId, LocalDate returnDate) throws SQLException {
		String sql = "UPDATE rentals SET return_date = ? WHERE id = ?";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setDate(1, Date.valueOf(returnDate));
			stmt.setInt(2, rentalId);
			stmt.executeUpdate();
		}
	}
	public void checkOverdue() throws SQLException {
		String sql = "SELECT * FROM rentals";
		try (Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("dvdTitle");
				LocalDate due = rs.getDate("due_date").toLocalDate();
				Date retDateRaw = rs.getDate("return_date");
				LocalDate retDate = retDateRaw != null ? retDateRaw.toLocalDate() : null;

				boolean overdue = retDate != null && retDate.isAfter(due);
				long daysLate = retDate != null ? Math.max(0, ChronoUnit.DAYS.between(due, retDate)) : 0;
				double fee = daysLate * rs.getDouble("late_fee_per_day");

				System.out.printf("ID: %d, タイトル: %s, 延滞日数: %d, 延滞料金: %.0f円\n", id, title, daysLate, fee);
			}
		}
	}
}

