package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.User;

/**
 * @author User Create time Nov 27, 2017+2:51:41 PM
 */
public class UserContext {

	/**
	 * @param user
	 * @return
	 */
	public static boolean insertUser(User user) {
		Connection conn = null;
		try {
			conn = DatabaseContext.getConnection();
			String query = "INSERT INTO [dbo].[User]([email],[first_name],[last_name]) VALUES (?,?,?)";
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
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	/**
	 * @param email
	 * @return
	 */
	public static boolean deleteUser(String email) {
		Connection conn = null;
		try {
			conn = DatabaseContext.getConnection();
			String query = "DELETE FROM [dbo].[User] WHERE email=?";
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

	/**
	 * @param email
	 * @return
	 */
	public static List<User> getUserByEmail(String email) {
		Connection conn = null;
		List<User> list = new ArrayList<User>();
		try {
			conn = DatabaseContext.getConnection();
			String query = "SELECT * FROM [dbo].[User] WHERE email=?";
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
	 * @param user
	 * @param email
	 * @return
	 */
	public static boolean updateUser(User user, String email) {
		Connection conn = null;
		try {
			conn = DatabaseContext.getConnection();
			String query = "	UPDATE [dbo].[User] SET [email]=?,[first_name]=?,[last_name]=? WHERE [email]=?";
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

	/**
	 * @param query
	 * @return
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
