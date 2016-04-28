package model.datastore.impl;

import model.UserForm;
import model.datastore.UserData;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class DataSet implements UserData {
	private final Set<UserForm> dataStore = new HashSet<>();

	@Override
	public UserForm getByUserName(final String userName) {
		isNull(userName);
		for (UserForm element : dataStore) {
			if (userName.equals(element.getUserName())) {
				return element;
			}
		}
		return null;
	}

	@Override
	public Collection<UserForm> getAll() {
		return dataStore.isEmpty() ? Collections.<UserForm>emptySet() : Collections.unmodifiableSet(dataStore);
	}

	@Override
	public void add(final UserForm userForm) {
		isNull(userForm);
		dataStore.add(userForm);
	}

	@Override
	public void update(final UserForm userForm) {
		isNull(userForm);
		for (UserForm element : dataStore) {
			if (userForm.equals(element)) {
				dataStore.remove(element);
				dataStore.add(userForm);
			}
		}
	}

	@Override
	public void delete(final String userName) {
		isNull(userName);
		for (UserForm element : dataStore) {
			if (userName.equals(element.getUserName())) {
				dataStore.remove(element);
			}
		}
	}

	private void isNull(final Object object) {
		if (object == null) {
			throw new IllegalArgumentException("Parameter can not be NULL");
		}
	}
}
