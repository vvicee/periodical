package com.vvicee.service;

import com.vvicee.db.implDao.EditionDAOImpl;
import com.vvicee.db.implDao.SubscriptionDAOImpl;
import com.vvicee.db.implDao.UserDAOImpl;
import com.vvicee.entity.edition.Edition;
import com.vvicee.entity.edition.Theme;
import com.vvicee.entity.user.User;
import com.vvicee.exception.DBException;
import com.vvicee.util.Validator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.function.Predicate;

import static com.vvicee.constant.entity.EditionConstant.*;
import static com.vvicee.constant.servlet.ErrorsConstant.*;
import static com.vvicee.constant.servlet.MailConstant.EDITIONS;
import static com.vvicee.constant.servlet.MailConstant.EDITION_MESSAGE;


public class EditionService {

    Logger logger = Logger.getLogger(EditionService.class);

    private List<Edition> currentEditions;
    private int countEditions;
    private final EditionDAOImpl editionDAO;
    private final SubscriptionDAOImpl subscriptionDAO;
    private final UserDAOImpl userDAO;
    private final MailService mailService;

    public EditionService() {
        editionDAO = new EditionDAOImpl();
        subscriptionDAO = new SubscriptionDAOImpl();
        userDAO = new UserDAOImpl();
        mailService = new MailService();
    }

    public void loadEditionsFromDB() throws DBException {
        List<Edition> editions = getEditions();
        setCurrentEditions(editions);
    }

    public void notifySubscribers(Theme theme) throws DBException {
        Set<Integer> usersId = new HashSet<>();
        subscriptionDAO.findAll().stream()
                .filter((subscription -> subscription.getEdition().getTheme().equals(theme)))
                .forEach(subscription -> usersId.add(subscription.getUserId()));

        for (Integer i : usersId) {
            User user = userDAO.find(i);
            if (user.isMailings() && user.isActive()) {
                mailService.sendMessage(user.getEmail(), EDITIONS, EDITION_MESSAGE);
            }
        }

    }

    public int getCountEditions() {
        return countEditions;
    }

    public List<Edition> getEditions() throws DBException {
        List<Edition> editions = editionDAO.findAll();
        countEditions = editions.size();
        return editions;
    }

    public List<Edition> getCurrentEditions() {
        return currentEditions;
    }

    public void setCurrentEditions(List<Edition> currentEditions) {
        this.currentEditions = currentEditions;
    }

    public Predicate<Edition> getPredicateForCategories(String[] categories) {
        if (categories.length != 1) {
            return edition -> Arrays.asList(categories)
                    .contains(String.valueOf(edition.getCategory()).toLowerCase());
        }
        return edition -> true;
    }

    public void setParametersOfEdition(Map<String, String> errors, HttpServletRequest req, Edition edition) {
        String title = req.getParameter(EDITION_TITLE);
        double price = Double.parseDouble(req.getParameter(EDITION_PRICE));
        String description = req.getParameter(EDITION_DESCRIPTION);
        String publisher = req.getParameter(EDITION_PUBLISHER);

        if (!Validator.validateName(title)) {
            errors.put(EDITION_TITLE, INCORRECT_TITLE_ERROR);
        } else if (!Validator.validateName(description)) {
            errors.put(EDITION_DESCRIPTION, INCORRECT_DESCRIPTION_ERROR);
        } else if (!Validator.validateName(publisher)) {
            errors.put(EDITION_PUBLISHER, INCORRECT_PUBLISHER_ERROR);
        } else if (!Validator.validatePrice(price)) {
            errors.put(EDITION_PRICE, INCORRECT_PRICE_ERROR);
        }

        if (errors.isEmpty()) {
            edition.setTitle(title);
            edition.setDescription(description);
            edition.setPublisher(publisher);
            edition.setPrice(price);
            edition.setCategory(req.getParameter(EDITION_CATEGORY));
            edition.setTheme(req.getParameter(EDITION_THEME));
        }
    }

    public Predicate<Edition> getPredicateForThemes(String[] themes) {
        if (themes.length != 1) {
            return edition -> Arrays.asList(themes).contains(String.valueOf(edition.getTheme()).toLowerCase());
        }
        return edition -> true;
    }

    public Predicate<Edition> getPredicateForTitle(String title) {
        return edition -> title.trim().compareToIgnoreCase(edition.getTitle()) == 0;
    }

    public Predicate<Edition> getPredicateForPublisher(String publisher) {
        return edition -> publisher.trim().compareToIgnoreCase(edition.getPublisher()) == 0;
    }

    public void sortCurrentEditions(Comparator<Edition> editionComparator) {
        getCurrentEditions().sort(editionComparator);
    }


    public void filterEditions(Predicate<Edition> editionPredicate) {
        List<Edition> result = new ArrayList<>();
        try {
            getEditions().stream().filter(editionPredicate).forEach(result::add);
        } catch (DBException exception) {
            exception.printStackTrace();
        }

        setCurrentEditions(result);
    }

    public void resetFilters() {
        try {
            setCurrentEditions(getEditions());
        } catch (DBException exception) {
            exception.printStackTrace();
        }
    }

}
