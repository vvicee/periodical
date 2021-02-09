package com.vvicee.constant.db.query;

public class EditionQueries {
    //language=SQL
    public static final String SQL_FIND_ALL_EDITIONS = "SELECT * FROM  edition";
    //language=SQL
    public static final String SQL_FIND_EDITION_BY_ID = "SELECT * FROM edition WHERE edition_id=?";
    //language=SQL
    public static final String SQL_ADD_EDITION = "INSERT INTO edition (title, publisher, description, price, category, theme) VALUES (?,?,?,?,?,?)";
    //language=SQL
    public static final String SQL_FIND_EDITION_BY_TITLE = "SELECT * FROM edition WHERE title=?";
    //language=SQL
    public static final String SQL_FIND_EDITION_BY_THEME = "SELECT * FROM edition WHERE theme=?";
    //language=SQL
    public static final String SQL_UPDATE_EDITION = "UPDATE edition SET title=?, publisher=?, description=?, price=?," +
            "category = ?, theme=? WHERE edition_id=?";
    //language=SQL
    public static final String SQL_DELETE_EDITION = "DELETE FROM edition WHERE edition_id=?";

}
