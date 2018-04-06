package com.example.kelvin.mychat.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.example.kelvin.mychat.Model.User;

/**
 * Created by kelvin on 4/2/18.
 */

public class UserDB {

    private static  UserDBHelper mDbHelper = null;


    private UserDB(){

    }

    private static  UserDB instance = null;

    private  static  UserDB getInstance(Context context){
        if (instance == null){
            instance = new UserDB();
            mDbHelper = new UserDBHelper(context);
        }
        return instance;
    }
    public long addUser(User user) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FeedEntry.COLUMN_NAME_ID, user.id);
        values.put(FeedEntry.COLUMN_NAME_NAME, user.name);
        values.put(FeedEntry.COLUMN_NAME_EMAIL, user.email);
        values.put(FeedEntry.COLUMN_NAME_AVATA, user.avata);
        // Insert the new row, returning the primary key value of the new row
        return db.insert(FeedEntry.TABLE_NAME, null, values);
    }

    public  void  addListUser(ListUser listUser){
        for(User user: listUser.getListUser()){
            addUser(user);
        }
    }
    public ListUser getListUser(){
        ListUser listUser = new ListUser();
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        // Define a projection that specifies which columns from the database
        // you will actually use after this query
        try {
            Cursor cursor = db.rawQuery("select * from " + FeedEntry.TABLE_NAME, null);
            while (cursor.moveToNext()) {
                User user = new User();
                user.id = cursor.getString(0);
                user.name = cursor.getString(1);
                user.email = cursor.getString(2);
                user.avata = cursor.getString(4);
                listUser.getListUser().add(user);
            }
            cursor.close();
        }catch (Exception e){
            return new ListUser();
        }
        return listUser;

    }
    public void  dropDB(){
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        db.execSQL(SQL_CREATE_ENTRIES);
        db.execSQL(SQL_DELETE_ENTRIES);
    }


    /* Inner class that defines the table contents */
    public static class FeedEntry implements BaseColumns {
        static final String TABLE_NAME = "User";
        static final String COLUMN_NAME_ID = "userID";
        static final String COLUMN_NAME_NAME = "name";
        static final String COLUMN_NAME_EMAIL = "email";
        static final String COLUMN_NAME_AVATA = "avata";
    }
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedEntry.TABLE_NAME + " (" +
                    FeedEntry.COLUMN_NAME_ID + " TEXT PRIMARY KEY," +
                    FeedEntry.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
                    FeedEntry.COLUMN_NAME_EMAIL + TEXT_TYPE + COMMA_SEP +
                    FeedEntry.COLUMN_NAME_AVATA + TEXT_TYPE + " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;

    private static  class UserDBHelper extends SQLiteOpenHelper{
        // If you change the database schema, you must increment the database version.
        static final int DATABASE_VERSION = 1;
        static final String DATABASE_NAME = "UserChat.db";

        UserDBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_ENTRIES);
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // This database is only a cache for online data, so its upgrade policy is
            // to simply to discard the data and start over
            db.execSQL(SQL_DELETE_ENTRIES);
            onCreate(db);
        }

        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }
    }

}
