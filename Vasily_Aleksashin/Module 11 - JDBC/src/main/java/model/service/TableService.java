package model.service;

import dao.impl.TableDao;
import exception.DaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TableService {
	private static final Logger logger = LoggerFactory.getLogger(TableService.class);
	private final TableDao tableDao;

	public TableService(TableDao tableDao) {
		this.tableDao = tableDao;
	}

	public boolean create(final String name) {
		try {
			tableDao.create(name);
			return true;
		} catch (DaoException e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}

	public boolean delete(final String name) {
		try {
			tableDao.delete(name);
			return true;
		} catch (DaoException e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}
}
