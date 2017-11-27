package view;

import java.util.List;
import java.util.Scanner;

import util.Const;
import models.User;
import bus.UserBus;

public class UI {
	private static Scanner scanner;
	static String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

	static void menu() {
		System.out.println(Const.MENU);
	}

	static void insert() {
		try {
			Scanner sc = new Scanner(System.in);
			System.out.println("Number User Input: ");
			int num = sc.nextInt();
			for (int i = 0; i < num; i++) {
				User user = new User();
				System.out.println("User " + (i + 1));
				System.out.println("Email: ");
				String email = sc.next();
				if (email.matches(EMAIL_REGEX)) {
					user.setEmail(email);
				} else {
					System.out.println("Wrong type Email!");
					break;
				}
				System.out.println("First Name: ");
				user.setFirstName(sc.next());
				System.out.println("Last Name: ");
				user.setLastName(sc.next());
				if (UserBus.insert(user)) {
					System.out.println("Insert User Success !");
				} else {
					System.out.println("Insert User Fail !");
				}
			}
		} catch (Exception e) {
			System.out.println("Number only !");
		}
	}

	static void getListUser() {
		List<User> list = UserBus.getList();
		if (list.size() > 0) {
			for (User user : list) {
				System.out.println("------------------------------------------");
				System.out.println("Email: " + user.getEmail());
				System.out.println("First Name: " + user.getFirstName());
				System.out.println("Last Name: " + user.getLastName());
			}
		} else {
			System.out.println("Database empty !");
		}
	}

	static void getUserByEmail() {
		try {
			Scanner sc = new Scanner(System.in);
			System.out.println("Email of User: ");
			String email = sc.next();
			if (email.matches(EMAIL_REGEX)) {
				List<User> list = UserBus.getListByEmail(email);
				if (list.size() > 0) {
					for (User user : list) {
						System.out.println("------------------------------------------");
						System.out.println("Email: " + user.getEmail());
						System.out.println("First Name: " + user.getFirstName());
						System.out.println("Last Name: " + user.getLastName());
					}
				} else {
					System.out.println("Email not found !");
				}
			} else {
				System.out.println("Wrong type Email or Database empty!");
			}
		} catch (Exception e) {

		}
	}

	static void deleteuser() {
		try {
			Scanner sc = new Scanner(System.in);
			System.out.println("Email of User: ");

			String email = sc.next();
			if (email.matches(EMAIL_REGEX)) {
				if (UserBus.delete(email)) {
					System.out.println("Delete User Sucess!");
				} else {
					System.out.println("Delete User Fail!");
				}
				;
			} else {
				System.out.println("Wrong type Email!");
			}

		} catch (Exception e) {

		}
	}

	static void updateUser() {
		try {
			Scanner sc = new Scanner(System.in);
			System.out.println("Email of User update: ");
			String email = sc.next();
			if (email.matches(EMAIL_REGEX)) {
				User user = new User();
				System.out.println("New Email: ");
				String newemail = sc.next();
				if (newemail.matches(EMAIL_REGEX)) {
					user.setEmail(newemail);
				} else {
					System.out.println("Wrong type new email !");
					return;
				}
				System.out.println("New First Name: ");
				user.setFirstName(sc.next());
				System.out.println("New Last Name: ");
				user.setLastName(sc.next());
				if (UserBus.update(user, email)) {
					System.out.println("Update User Success !");
				} else {
					System.out.println("Update User Fail !");
				}
				;
			} else {
				System.out.println("Wrong Email Type !");
			}
		} catch (Exception e) {
		}
	}

	public static void main(String[] args) throws Exception {
		User user = new User();

		int num = 0;
		scanner = new Scanner(System.in);
		menu();
		while (true) {
			try {
				num = Integer.parseInt(scanner.next());
				switch (num) {
				case 1:
					insert();
					menu();
					break;
				case 2:
					updateUser();
					menu();
					break;
				case 3:
					getUserByEmail();
					menu();
					break;
				case 4:
					getListUser();
					menu();
					break;
				case 5:
					deleteuser();
					menu();
					break;
				case 6:

					return;
				default:
					System.out.print(Const.EXCEPTION);
					menu();
					break;
				}
			} catch (Exception e) {
				System.out.print(Const.EXCEPTION);
				menu();
			}
		}

	}
}
