package view;

import java.util.List;

import models.User;
import bus.UserBus;

public class UI {
	public static void main(String[] args) throws Exception {

		User user = new User();
		user.setEmail("b@gmail.com");
		user.setFirstName("Test AAA");
		user.setLastName("BBB");

		if (UserBus.insert(user)) {
			System.out.println("Insert success!");
		} else {
			System.out.println("Insert failed!");
		}

		if (UserBus.delete("a@gmail.com")) {
			System.out.println("Delete success!");
		} else {
			System.out.println("Delete failed!");
		}

		User user2 = new User("vddfd@gmail.com", "gtuong", "nhguyen");
		if (UserBus.update(user2,"b@gmail.com")) {
			System.out.println("Update success!");
		} else {
			System.out.println("Update failed!");
		}
		List<User> users=UserBus.getListByEmail("vddfd@gmail.com");
		/*List<User> userList = UserBus.getList();*/
		for (User u : users) {
			System.out.println("email:" + u.getEmail() + ", firstname:"
					+ u.getFirstName() + ", lastname: " + u.getLastName());
		}

	}
}
