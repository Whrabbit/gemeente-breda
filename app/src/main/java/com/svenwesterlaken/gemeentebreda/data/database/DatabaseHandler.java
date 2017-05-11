package com.svenwesterlaken.gemeentebreda.data.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Whrabbit on 5/9/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper{
    //CLASSES
    private static final String TAG = "GemeenteDBHandler";

    private static final int DB_VERSION = 5;
    private static final String DB_NAME = "gemeente.db";

    private static final String USER_TABLE_NAME = "user";
        private static final String USER_COLUMN_ID = "_userId";
        private static final String USER_COLUMN_NAME = "name";
        private static final String USER_COLUMN_PHONE = "phone";
        private static final String USER_COLUMN_EMAIL = "email";

    private static final String REPORT_TABLE_NAME = "report";
        private static final String REPORT_COLUMN_ID = "_reportId";
        private static final String REPORT_COLUMN_CATEGORYID = "categoryId";
        private static final String REPORT_COLUMN_DESCRIPTION = "description";
        private static final String REPORT_COLUMN_MEDIAID = "mediaId";
        private static final String REPORT_COLUMN_LOCATIONID = "locationId";


    private static final String USER_REPORT_TABLE_NAME = "user_report";
        private static final String USER_REPORT_COLUMN_USERID = "_userId";
        private static final String USER_REPORT_COLUMN_REPORTID = "_reportId";

    private static final String LOCATION_TABLE_NAME = "location";
        private static final String LOCATION_COLUMN_ID = "_locationId";
        private static final String LOCATION_COLUMN_LATITUDE = "latitude";
        private static final String LOCATION_COLUMN_LONGITUDE = "longitude";
        private static final String LOCATION_COLUMN_STREET = "steet";
        private static final String LOCATION_COLUMN_STREETNR = "streetnr";
        private static final String LOCATION_COLUMN_POSTALCODE = "postalcode";
        private static final String LOCATION_COLUMN_CITY = "city";

    private static final String CATEGORY_TABLE_NAME = "category";
        private static final String CATEGORY_COLUMN_ID = "_categoryId";
        private static final String CATEGORY_COLUMN_NAAM = "categoryName";
        private static final String CATEGORY_COLUMN_SUMMARY = "summary";

    private static final String MEDIA_TABLE_NAME = "media";
        private static final String MEDIA_COLUMN_ID = "_mediaId";

    private static final String VIDEO_TABLE_NAME = "video";
        private static final String VIDEO_COLUMN_ID = "_mediaId";
        private static final String VIDEO_COLUMN_LENGTH = "length";

    private static final String PHOTO_TABLE_NAME = "photo";
        private static final String PHOTO_COLUMN_ID = "_mediaId";

    public DatabaseHandler (Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, DB_NAME, factory, DB_VERSION);

    }
    //CREATE DATABASE WITH SQL
    public void onCreate(SQLiteDatabase db){
        Log.i(TAG, "creating database");
        String CREATE_USER_TABLE = "CREATE TABLE " + USER_TABLE_NAME + "(" +
                USER_COLUMN_ID + " TEXT PRIMARY KEY AUTOINCREMENT," +
                USER_COLUMN_NAME + " TEXT," +
                USER_COLUMN_PHONE + " TEXT," +
                USER_COLUMN_EMAIL + " TEXT" +
                ");";

        String CREATE_REPORT_TABLE = "CREATE TABLE " + REPORT_TABLE_NAME + "(" +
                REPORT_COLUMN_ID + " TEXT PRIMARY KEY AUTOINCREMENT," +
                REPORT_COLUMN_CATEGORYID + " TEXT, " +
                REPORT_COLUMN_DESCRIPTION + " TEXT," +
                REPORT_COLUMN_MEDIAID + " TEXT," +
                REPORT_COLUMN_LOCATIONID + " TEXT," +

                "FOREIGN KEY (" + REPORT_COLUMN_CATEGORYID + ")" +
                "REFERENCES " + CATEGORY_TABLE_NAME + "(" + CATEGORY_COLUMN_ID + ")," +

                "FOREIGN KEY (" + REPORT_COLUMN_LOCATIONID + ")" +
                "REFERENCES " + REPORT_TABLE_NAME +  "(" + REPORT_COLUMN_ID + ")," +

                "FOREIGN KEY (" + REPORT_COLUMN_MEDIAID + ")" +
                "REFERENCES " + MEDIA_TABLE_NAME +  "(" + MEDIA_COLUMN_ID + ")" +

                ");";
        String CREATE_USER_REPORT_TABLE = "CREATE TABLE " + USER_REPORT_TABLE_NAME + "(" +
                USER_REPORT_COLUMN_USERID + " TEXT, " +
                USER_REPORT_COLUMN_REPORTID + " TEXT, " +

                "FOREIGN KEY (" + USER_REPORT_COLUMN_USERID + ")" +
                "REFERENCES " + USER_TABLE_NAME + "(" + USER_COLUMN_ID + ")," +

                "FOREIGN KEY (" + USER_REPORT_COLUMN_REPORTID + ")" +
                "REFERENCES " + REPORT_TABLE_NAME +  "(" + REPORT_COLUMN_ID + ")" +

                ");";

        String CREATE_LOCATION_TABLE = "CREATE TABLE " + LOCATION_TABLE_NAME + "(" +
                LOCATION_COLUMN_ID + " TEXT PRIMARY KEY AUTOINCREMENT," +
                LOCATION_COLUMN_LATITUDE + " DECIMAL(5,10)," +
                LOCATION_COLUMN_LONGITUDE + " DECIMAL(5,10)," +
                LOCATION_COLUMN_STREET + " TEXT," +
                LOCATION_COLUMN_STREETNR + " TEXT," +
                LOCATION_COLUMN_POSTALCODE + " TEXT," +
                LOCATION_COLUMN_CITY + " TEXT" +
                ");";

        String CREATE_CATEGORY_TABLE = "CREATE TABLE " + CATEGORY_TABLE_NAME + "(" +
                CATEGORY_COLUMN_ID + " TEXT PRIMARY KEY AUTOINCREMENT," +
                CATEGORY_COLUMN_NAAM + " TEXT, " +
                CATEGORY_COLUMN_SUMMARY + " TEXT " +
                ");";

        String CREATE_MEDIA_TABLE = "CREATE TABLE " + MEDIA_TABLE_NAME +  "(" +
                MEDIA_COLUMN_ID + " TEXT PRIMARY KEY AUTOINCREMENT" +
                ");";

        String CREATE_VIDEO_TABLE = "CREATE TABLE " + VIDEO_TABLE_NAME + "(" +
                VIDEO_COLUMN_ID + " TEXT PRIMARY KEY AUTOINCREMENT, " +
                VIDEO_COLUMN_LENGTH + " TEXT, " +

                "FOREIGN KEY (" + VIDEO_COLUMN_ID + ")" +
                "REFERENCES " + MEDIA_TABLE_NAME +  "(" + MEDIA_COLUMN_ID + ")," +

                ");";

        String CREATE_PHOTO_TABLE = "CREATE TABLE " + PHOTO_TABLE_NAME + "(" +
                PHOTO_COLUMN_ID + " TEXT PRIMARY KEY AUTOINCREMENT" +

                "FOREIGN KEY (" + PHOTO_COLUMN_ID + ")" +
                "REFERENCES " + MEDIA_TABLE_NAME +  "(" + MEDIA_COLUMN_ID + ")" +

                ");";
        db.execSQL("PRAGMA foreign_keys = ON");
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_REPORT_TABLE);
        db.execSQL(CREATE_USER_REPORT_TABLE);
        db.execSQL(CREATE_LOCATION_TABLE);
        db.execSQL(CREATE_CATEGORY_TABLE);
        db.execSQL(CREATE_MEDIA_TABLE);
        db.execSQL(CREATE_VIDEO_TABLE);
        db.execSQL(CREATE_PHOTO_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

//    //ADD USERS
//    public void addUser(User user){
//        ContentValues values = new ContentValues();
//        values.put(USER_COLUMN_NAME, user.getName());
//        values.put(USER_COLUMN_PHONE, user.getPhone());
//        values.put(USER_COLUMN_EMAIL, user.getEmail());
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.insert(USER_TABLE_NAME, null, values);
//        Log.i("TAG", "added user");
//        db.close();
//    }
//
//    //ADD REPORTS
//    public void addReport(Report report){
//        ContentValues values = new ContentValues();
//        values.put(REPORT_COLUMN_CATEGORYID, report.getCategoryId());
//        values.put(REPORT_COLUMN_DESCRIPTION, report.getDescription());
//        values.put(REPORT_COLUMN_MEDIAID, report.getMediaId());
//        values.put(REPORT_COLUMN_LOCATIONID, report.getLocationId());
//        values.put(REPORT_COLUMN_USERID, report.getUserId());
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.insert(REPORT_TABLE_NAME, null, values);
//        Log.i("TAG", "added report");
//        db.close();
//    }
//
//    //ADD LOCATIONS
//    public void addLocation(Location location){
//        ContentValues values = new ContentValues();
//        values.put(LOCATION_COLUMN_LATITUDE, location.getLatitude());
//        values.put(LOCATION_COLUMN_LONGITUDE, location.getLongitude());
//        values.put(LOCATION_COLUMN_STREET, location.getStreet());
//        values.put(LOCATION_COLUMN_STREETNR , location.getStreetNr());
//        values.put(LOCATION_COLUMN_POSTALCODE, location.getPostalcode());
//        values.put(LOCATION_COLUMN_CITY, location.getCity());
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.insert(LOCATION_TABLE_NAME, null, values);
//        Log.i("TAG", "added location");
//        db.close();
//    }
//
//    //ADD CATEGORIES
//    public void addCategory(Category category){
//        ContentValues values = new ContentValues();
//        values.put(CATEGORY_COLUMN_NAAM, category.getCategoryName());
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.insert(CATEGORY_TABLE_NAME, null, values);
//        db.close();
//    }
//    //ADD MEDIA
//    public void addMedia(Media media){
//        ContentValues values = new ContentValues();
//        values.put(MEDIA_COLUMN_ID, media.getMediaId());
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.insert(MEDIA_TABLE_NAME, null, values);
//        db.close();
//    }
//    //ADD PHOTO
//    public void addPhoto(Photo photo){
//        ContentValues values = new ContentValues();
//        values.put(MEDIA_COLUMN_ID, photo.getMediaId());
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.insert(PHOTO_TABLE_NAME, null, values);
//        db.close();
//    }
//
//    //ADD VIDEO
//    public void addVideo(Video video){
//        ContentValues values = new ContentValues();
//        values.put(VIDEO_COLUMN_ID, video.getMediaId());
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.insert(VIDEO_TABLE_NAME, null, values);
//        db.close();
//    }
//
//    //GET USERS
//    public User getUser(String name){
//        User user = new User();
//
//        String query = "SELECT " + USER_COLUMN_NAME + " FROM " + USER_TABLE_NAME +
//                USER_COLUMN_NAME + "=" + "\"" + name + "\"";
//
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        Cursor cursor = db.rawQuery(query, null);
//
//        //cursor.moveToFirst();
//        Log.i("TAG", "before while");
//
//        while(cursor.moveToNext() ) {
//            user.setName(cursor.getString(cursor.getColumnIndex(USER_COLUMN_NAME)));
//            Log.i("TAG", "got user");
//        };
//
//        db.close();
//
//        return user;
//    }
//
//
//
//    //ARRAYLIST FOR REPORTS
//    public ArrayList<Report> getReport(String name){
//        ArrayList<Report> reports = new ArrayList<>();
//
//        String query = "SELECT * FROM " + REPORT_TABLE_NAME + " WHERE " +
//                REPORT_COLUMN_USERID + "=" + "\"" + name + "\"";
//
//        String queryALL = "SELECT * FROM " + REPORT_TABLE_NAME;
//
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        Cursor cursor = db.rawQuery(query, null);
//
//        while(cursor.moveToNext() ) {
//
//            Report report = new Report();
//
//            report.setReportId(cursor.getInt(cursor.getColumnIndex(REPORT_COLUMN_ID)));
//            report.setCategoryId(cursor.getInt(cursor.getColumnIndex(REPORT_COLUMN_CATEGORYID)));
//            report.setDescription(cursor.getString(cursor.getColumnIndex(REPORT_COLUMN_DESCRIPTION)));
//            report.setMediaId(cursor.getInt(cursor.getColumnIndex(REPORT_COLUMN_MEDIAID)));
//            report.setLocationId(cursor.getInt(cursor.getColumnIndex(REPORT_COLUMN_LOCATIONID)));
//            report.setUserId(cursor.getInt(cursor.getColumnIndex(REPORT_COLUMN_USERID)));
//
//            reports.add(report);
//        }
//
//        db.close();
//
//
//
//        return reports;
//    }


}
