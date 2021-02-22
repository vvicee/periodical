package com.vvicee.entity;

import com.vvicee.entity.subscription.Month;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class MonthTest {

    private List<Month> monthList;

    @Before
    public void setUp() {
        monthList = new ArrayList<>();
        monthList.add(Month.JANUARY);
        monthList.add(Month.FEBRUARY);
    }

    @Test
    public void convertArrayToListTest() {
        String[] months = new String[]{"JANUARY", "FEBRUARY"};

        assertEquals(Month.convertArrayToList(months), monthList);
    }

    @Test
    public void getAvailableMonthsTest() {
        assertNotEquals(monthList, Month.getAvailableMonths());
    }
}
