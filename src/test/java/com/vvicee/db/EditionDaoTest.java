package com.vvicee.db;

import com.vvicee.db.implDao.EditionDAOImpl;
import com.vvicee.entity.edition.Edition;
import com.vvicee.exception.DBException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class EditionDaoTest {

    private Edition editionTest;
    private EditionDAOImpl dao;

    @Before
    public void setUp() throws Exception {
        editionTest = new Edition();
        dao = new EditionDAOImpl();
        editionTest = initEdition();
        dao.add(editionTest);
    }

    @After
    public void tearDown() throws Exception {
        dao.delete(editionTest);
        editionTest = null;
    }

    @Test
    public void updateTest() throws DBException {
        Edition ed = initEdition();
        editionTest.setPrice(1);
        dao.update(editionTest);
        assertNotEquals(ed, editionTest);
    }

    @Test
    public void findTest() throws DBException {
        List<Edition> editions = new ArrayList<>();
        editions.add(editionTest);

        assertEquals(editions, dao.find(Collections.singletonList(editionTest.getId())));
    }

    private Edition initEdition() {
        return new Edition.Builder()
                .setTitle("Test")
                .setPublisher("Test")
                .setDescription("Test")
                .setTheme("health")
                .setCategory("newspaper")
                .setPrice(0)
                .createEdition();
    }

}
