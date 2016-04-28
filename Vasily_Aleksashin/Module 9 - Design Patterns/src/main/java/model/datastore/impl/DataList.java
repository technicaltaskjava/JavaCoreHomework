package model.datastore.impl;

import model.UserForm;
import model.datastore.UserData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DataList implements UserData {
	private final List<UserForm> dataStore = new ArrayList<>();

	@Override
	public UserForm getByUserName(final String userName) {
		isNull(userName);
		for (int index = 0; index < dataStore.size(); index++) {
			if (userName.equals(dataStore.get(index).getUserName())) {
				return dataStore.get(index);
			}
		}
		return null;
	}

	@Override
	public Collection<UserForm> getAll() {
		return dataStore.isEmpty() ? Collections.<UserForm>emptyList() : Collections.unmodifiableList(dataStore);
	}

	@Override
	public void add(final UserForm userForm) {
		isNull(userForm);
		dataStore.add(userForm);
	}

	@Override
	public void update(final UserForm userForm) {
		isNull(userForm);
		for (int index = 0; index < dataStore.size(); index++) {
			if (userForm.equals(dataStore.get(index))) {
				dataStore.set(index, userForm);
				break;
			}
		}
	}

	@Override
	public void delete(final String userName) {
		isNull(userName);
		for (int index = 0; index < dataStore.size(); index++) {
			if (userName.equals(dataStore.get(index).getUserName())) {
				dataStore.remove(index);
				break;
			}
		}
	}

	private void isNull(final Object object) {
		if (object == null) {
			throw new IllegalArgumentException("Parameter can not be NULL");
		}
	}
}
