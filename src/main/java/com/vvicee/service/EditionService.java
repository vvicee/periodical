package com.vvicee.service;

import com.vvicee.db.implDao.EditionDAOImpl;
import com.vvicee.entity.edition.Edition;
import com.vvicee.exception.DBException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import static com.vvicee.constant.entity.EditionConstant.*;


public class EditionService {

    Logger logger = Logger.getLogger(EditionService.class);

    private List<Edition> currentEditions;
    private int countEditions;
    EditionDAOImpl editionDAO;

    public EditionService() {
        editionDAO = new EditionDAOImpl();
    }

    public void loadEditionsFromDB() throws DBException {
        List<Edition> editions = getEditions();
        setCurrentEditions(editions);
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

    public void setParametersOfEdition(HttpServletRequest req, Edition edition) {
        edition.setTitle(req.getParameter(EDITION_TITLE));
        edition.setPrice(Double.parseDouble(req.getParameter(EDITION_PRICE)));
        edition.setDescription(req.getParameter(EDITION_DESCRIPTION));
        edition.setPublisher(req.getParameter(EDITION_PUBLISHER));
        edition.setCategory(req.getParameter(EDITION_CATEGORY));
        edition.setTheme(req.getParameter(EDITION_THEME));
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
