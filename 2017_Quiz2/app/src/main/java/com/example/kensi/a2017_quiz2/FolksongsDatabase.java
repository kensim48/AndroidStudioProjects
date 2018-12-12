package com.example.kensi.a2017_quiz2;

//- - - copy and paste from this point onwards  - - -

import android.provider.BaseColumns;

public class FolksongsDatabase {



    public static final class FolksongsTable implements BaseColumns{

        public static final String TABLE_NAME = "Folksongs";
        public static final String COL_TITLE = "title";
        public static final String COL_COUNTRY = "country";

        public static String SPACE = " ";
        public static String COMMA = ",";

        //TODO 1 - make the SQL commands to create a table and delete a table
        public static final String SQL_CREATE_TABLE = "CREATE TABLE" + SPACE
                + TABLE_NAME + SPACE + "("
                + _ID + SPACE + "INTEGER PRIMARY KEY AUTOINCREMENT" + COMMA
                + COL_TITLE + SPACE + "TEXT NOT NULL" + COMMA
                + COL_COUNTRY + SPACE + "TEXT NOT NULL" + ");" ;

        public static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

        public static final String SQL_SELECT =  "SELECT" + SPACE + COL_TITLE + SPACE + "FROM" + SPACE +
                TABLE_NAME + SPACE + "WHERE" + SPACE + COL_COUNTRY +SPACE + "=" +SPACE;


    }
}
