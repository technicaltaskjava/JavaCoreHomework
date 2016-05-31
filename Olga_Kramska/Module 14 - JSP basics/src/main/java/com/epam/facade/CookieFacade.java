package com.epam.facade;

import com.epam.dto.PredictionDTO;
import com.epam.model.FortuneCookie;
import com.epam.model.MetaData;
import com.epam.model.User;

import java.util.List;

/**
 * Created by Olga Kramska on 31-May-16.
 */
public interface CookieFacade {
    void addUser(User user);

    MetaData addCookie(User user, FortuneCookie cookie);

    User findUser(String username);

    List<PredictionDTO> findPredictions(int userId);

    List<FortuneCookie> getAllPredictions();

    void updatePrediction(FortuneCookie cookie);

    void deletePrediction(int cookieId);
}
