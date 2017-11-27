package bus;

import java.util.ArrayList;
import java.util.List;

import util.Const;
import models.User;
import DAO.UserContext;

/**
 * @author User
 *
 */
public class UserBus {
	public static List<User> getList() {
		List<User> list = new ArrayList<User>();
		UserContext context = new UserContext();
		try {
			list = context.getUsers("Select * from "+Const.DB_NAME);
		} catch (Exception e) {

		}
		return list;

	}

	/**
	 * @param email
	 * @return
	 */
	public static List<User> getListByEmail(String email) {
		List<User> list = new ArrayList<User>();
		UserContext context = new UserContext();
		try {
			list = context.getUserByEmail(email);
		} catch (Exception e) {
		}
		return list;
	}

	/**
	 * @return
	 */
	public static List<User> getUserProc() {
		List<User> list = new ArrayList<User>();
		UserContext context = new UserContext();
		try {
			list = context.getUserProc();
		} catch (Exception e) {
		}
		return list;
	}

	/**
	 * @param user
	 * @return
	 */
	public static boolean insert(User user) {
		return new UserContext().insertUser(user);
	}

	/**
	 * @param email
	 * @return
	 */
	public static boolean delete(String email) {
		return new UserContext().deleteUser(email);
	}

	/**
	 * @param user
	 * @param email
	 * @return
	 */
	public static boolean update(User user, String email) {
		return new UserContext().updateUser(user, email);
	}
}
