package model.service.impl;

import dao.impl.MetadataDao;
import exception.DaoException;
import model.entity.Metadata;
import model.service.AbstractEntityService;

public class MetadataService extends AbstractEntityService<Metadata, MetadataDao> {
	public MetadataService(final MetadataDao entityDao) throws DaoException {
		super(entityDao);
	}


}
