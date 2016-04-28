package model.datastore;

import model.UserForm;

import java.util.Collection;

public interface UserData {
	UserForm getByUserName(final String userName);

	Collection<UserForm> getAll();

	void add(UserForm userForm);

	void update(UserForm userForm);

	void delete(final String userName);
}
