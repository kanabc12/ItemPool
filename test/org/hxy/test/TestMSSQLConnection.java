package org.hxy.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TestMSSQLConnection {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(
					"jdbc:sqlserver://127.0.0.1:1433;DatabaseName="
							+ "itempool", "sa", "7758520");
			System.out.println(con);
			String sql = "select count(*) SpecialID from T_Article";

			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				int ID = rs.getInt("SpecialID");
				System.out.println(ID);

			}

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		System.out.println(con);

	}

}
