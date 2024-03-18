package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;
import db.DbException;

public class Program {

	public static void main(String[] args) {
			Connection conn = null;
			PreparedStatement preparedSt = null;
			
			try {
				conn = DB.connectDb();
				preparedSt = conn.prepareStatement(
						"UPDATE seller "
						+ "SET BaseSalary = BaseSalary + ? "
						+ "WHERE "
						+ "DepartmentId = ?"
						);
				preparedSt.setDouble(1, 500.50);
				preparedSt.setInt(2, 2);
				
				int changedRows = preparedSt.executeUpdate();
				
				System.out.println("Done! Rowns affected: " + changedRows);
				
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			} finally {
				DB.closeStatement(preparedSt);
				DB.closeConnection();
			}
	}

}
