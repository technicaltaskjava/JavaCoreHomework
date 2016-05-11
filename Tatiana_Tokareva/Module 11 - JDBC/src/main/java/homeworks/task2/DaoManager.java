package homeworks.task2;

import java.util.List;

public interface DaoManager {


	List<User> selectAll(String tableName);

	int insert(User user);

	int delete();


}
