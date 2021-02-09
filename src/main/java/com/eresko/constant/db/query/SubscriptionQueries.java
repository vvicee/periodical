package com.eresko.constant.db.query;

public class SubscriptionQueries {
    //language=SQL
    public static final String SQL_FIND_SUBS_BY_USER_ID = "SELECT * FROM subscription WHERE us_id=?";
    //language=SQL
    public static final String SQL_ADD_SUBSCRIPTION = "INSERT INTO subscription (edit_id, us_id, year, months) VALUES (?,?,?,?)";
    // language=SQL
    public static final String SQL_SELECT_ALL_EDITION_ID_BY_USER_SUBS = "SELECT edit_id FROM subscription WHERE us_id=?";
}
