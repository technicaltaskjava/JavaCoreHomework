package com.epam.facade.impl;

import com.epam.facade.CookieFacade;
import com.epam.util.ConnectionManager;
import com.epam.dao.AbstractMetaDataDao;
import com.epam.dao.AbstractUserDao;
import com.epam.dao.DaoFactory;
import com.epam.dao.RepositoryDao;
import com.epam.dto.PredictionDTO;
import com.epam.model.FortuneCookie;
import com.epam.model.MetaData;
import com.epam.model.User;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Olga Kramska on 28-May-16.
 */
public class CookieFacadeImpl implements CookieFacade {
    private AbstractUserDao<User, Integer> userDao;
    private RepositoryDao<FortuneCookie, Integer> cookieDao;
    private AbstractMetaDataDao<MetaData, Integer> metaDataDao;

    public CookieFacadeImpl() {
        DataSource dataSource = ConnectionManager.getInstance().getDataSource();
        userDao = DaoFactory.getUserDao(dataSource);
        cookieDao = DaoFactory.getCookieDao(dataSource);
        metaDataDao = DaoFactory.getMetaDataDao(dataSource);
    }

    @Override
    public void addUser(User user) {
        userDao.add(user);
    }

    @Override
    public MetaData addCookie(User user, FortuneCookie cookie) {
        cookieDao.add(cookie);
        MetaData metaData = new MetaData(cookie.getId(), user.getId(), new Date());
        metaDataDao.add(metaData);
        return metaData;
    }

    @Override
    public User findUser(String username) {
        return userDao.get(username);
    }

    @Override
    public List<PredictionDTO> findPredictions(int userId) {
        List<MetaData> metaDatas = metaDataDao.getByUser(userId);
        List<PredictionDTO> predictions = new ArrayList<>(metaDatas.size());
        for (MetaData metaData : metaDatas) {
            FortuneCookie cookie = cookieDao.get(metaData.getCookieId());
            predictions.add(new PredictionDTO(cookie.getId(), cookie.getName(),
                    cookie.getPrediction(), metaData.getTimeAdded()));
        }
        return predictions;
    }

    @Override
    public List<FortuneCookie> getAllPredictions() {
        return cookieDao.getAll();
    }

    @Override
    public void updatePrediction(FortuneCookie cookie) {
        cookieDao.update(cookie);
    }

    @Override
    public void deletePrediction(int cookieId) {
        cookieDao.delete(cookieId);
    }
}
