package com.vvicee.service;

import com.vvicee.db.implDao.EditionDAOImpl;
import com.vvicee.entity.edition.Edition;
import com.vvicee.exception.DBException;
import org.junit.Test;

import java.util.function.Predicate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class EditionServiceTest {

    private final EditionDAOImpl dao = new EditionDAOImpl();
    private final EditionService service = new EditionService();

    @Test
    public void getEditionsTest() throws DBException {
        assertEquals(service.getEditions(), dao.findAll());
        assertEquals(service.getCountEditions(), dao.findAll().size());
    }

    @Test
    public void resetFiltersTest() throws DBException {
        service.resetFilters();
        assertEquals(service.getEditions(), service.getCurrentEditions());
    }

    @Test
    public void emptyPredicateTest(){
        String [] value = new String[]{"1"};
        assertEquals(service.getPredicateForCategories(value), (Predicate<Edition>) edition -> true);
        assertEquals(service.getPredicateForThemes(value), (Predicate<Edition>) edition -> true);
    }

    @Test
    public void filterEditionsTest() throws DBException {
        service.filterEditions(edition -> true);
        assertEquals(service.getEditions(), service.getCurrentEditions());
        service.filterEditions(edition -> false);
        assertNotEquals(service.getEditions(), service.getCurrentEditions());
    }

    @Test
    public void loadEditionsFromDBTest() throws DBException {
        service.loadEditionsFromDB();
        assertEquals(service.getEditions(), dao.findAll());
        assertEquals(service.getEditions(), service.getCurrentEditions());
    }

}
