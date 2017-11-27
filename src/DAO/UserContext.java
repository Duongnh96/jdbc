package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.Const;
import models.User;

/**
 * @author User Create time Nov 27, 2017+2:51:41 PM
 */
public class UserContext {

	/**	Insert new user
	 * @param user
	 * @return
	 */
	public static boolean insertUser(User user) {
		Connection conn = null;
		try {
			conn = DatabaseContext.getConnection();
			String query = "INSERT INTO " + Const.DB_NAME + "(" + Const.FIRST_COLUMN
					+ "," + Const.SECOND_COLUMN + "," + Const.THIRD_COLUMN
					+ ") VALUES (?,?,?)";
			try (PreparedStatement ps = conn.prepareStatement(query)) {
				ps.setString(1, user.getEmail());
				ps.setString(2, user.getFirstName());
				ps.setString(3, user.getLastName());
				return ps.executeUpdate() > 0;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		} finally {
			if (conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}}else {
					System.out.println("Database empty !");
				}
		}
	}

	/** Delete user by email
	 * @param email
	 * @return
	 */
	public static boolean deleteUser(String email) {
		Connection conn = null;
		try {
			conn = DatabaseContext.getConnection();
			String query = "DELETE FROM " + Const.DB_NAME + " WHERE email=?";
			try (PreparedStatement ps = conn.prepareStatement(query)) {
				ps.setString(1, email);
				return ps.executeUpdate() > 0;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	/**Get list user by email
	 * @param email
	 * @return List user by email
	 */
	public static List<User> getUserByEmail(String email) {
		Connection conn = null;
		List<User> list = new ArrayList<User>();
		try {
			conn = DatabaseContext.getConnection();
			// String query get all user with email
			String query = "SELECT * FROM " + Const.DB_NAME + " WHERE email=?";
			try (PreparedStatement ps = conn.prepareStatement(query)) {
				ps.setString(1, email);
				ResultSet resultSet = ps.executeQuery();
				while (resultSet.next()) {
					User user = new User();
					user.setEmail(resultSet.getString(1));
					user.setFirstName(resultSet.getString(2));
					user.setLastName(resultSet.getString(3));
					list.add(user);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return list;
	}

	/**
	 * @return List user with Proc
	 */
	public static List<User> getUserProc() {
		Connection conn = null;
		List<User> list = new ArrayList<User>();
		try {
			conn = DatabaseContext.getConnection();
			String query = "{call getall}";
			try (CallableStatement cs = conn.prepareCall(query)) {
				ResultSet resultSet = cs.executeQuery();
				while (resultSet.next()) {
					User user = new User();
					user.setEmail(resultSet.getString(1));
					user.setFirstName(resultSet.getString(2));
					user.setLastName(resultSet.getString(3));
					list.add(user);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return list;
	}

	/**Update user by email
	 * @param user
	 * @param email
	 * @return boolean
	 */
	public static boolean updateUser(User user, String email) {
		Connection conn = null;
		try {
			conn = DatabaseContext.getConnection();
			String query = "	UPDATE " + Const.DB_NAME + " SET " + Const.FIRST_COLUMN
					+ "=?," + Const.SECOND_COLUMN + "=?," + Const.THIRD_COLUMN
					+ "=? WHERE [email]=?";
			try (PreparedStatement ps = conn.prepareStatement(query)) {
				ps.setString(1, user.getEmail());
				ps.setString(2, user.getFirstName());
				ps.setString(3, user.getLastName());
				ps.setString(4, email);
				return ps.executeUpdate() > 0;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	/**Get list user 
	 * @param query
	 * @return List user 
	 */
	public static List<User> getUsers(String query) {
		List<User> userList = new ArrayList<User>();
		Connection conn = null;
		try {
			conn = DatabaseContext.getConnection();
			try (PreparedStatement ps = conn.prepareStatement(query)) {
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					User user = new User();
					user.setEmail(rs.getString(1));
					user.setFirstName(rs.getString(2));
					user.setLastName(rs.getString(3));
					userList.add(user);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return userList;
	}
}
