package model;

import model.datastore.DataFactory;
import model.datastore.UserData;
import model.datastore.impl.DataList;
import model.datastore.impl.DataSet;

public class DataStore implements DataFactory {
	@Override
	public UserData getListData() {
		return new DataList();
	}

	@Override
	public UserData getSetData() {
		return new DataSet();
	}
}
