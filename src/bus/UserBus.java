package bus;

import java.util.ArrayList;
import java.util.List;

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
			list = context.getUsers("Select * from [User]");
		} catch (Exception e) {

		}
		return list;

	}

	public static List<User> getListByEmail(String email) {
		List<User> list = new ArrayList<User>();
		UserContext context = new UserContext();
		try {
			list = context.getUserByEmail(email);
		} catch (Exception e) {
		}
		return list;

	}

	public static boolean insert(User user) {
		return new UserContext().insertUser(user);
	}

	public static boolean delete(String email) {
		return new UserContext().deleteUser(email);
	}

	public static boolean update(User user, String email) {
		return new UserContext().updateUser(user, email);
	}
}
