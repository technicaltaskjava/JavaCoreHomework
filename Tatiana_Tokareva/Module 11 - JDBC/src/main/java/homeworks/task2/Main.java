package homeworks.task2;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {


	private Main() {
	}


	public static void main(String[] args) throws InterruptedException, SQLException {


		Factory factory = new ConnectionFactory("jdbc:h2:D:\\homeworks\\Module11-JDBC\\test", "sa", "");

		Connection connection = factory.getConnection();
		DaoManager manager = factory.getUserDao(connection);



		User user1 = new User( "Yurash", "Yurik", "misterY@mail.com", "853254dsf");
		int rows = manager.insert(user1);
		System.out.println(rows);

		manager.delete();

		List<User> users = manager.selectAll("users");

		for (User user : users) {
			System.out.print(user.toString());
		}



		factory.closePool();

	}


}
