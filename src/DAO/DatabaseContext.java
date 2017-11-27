package DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseContext {

	public static Connection getConnection() throws Exception {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		return DriverManager.getConnection(
				"jdbc:sqlserver://localhost:1433;databaseName=jdbc;", "sa", "123456");
	}
}
