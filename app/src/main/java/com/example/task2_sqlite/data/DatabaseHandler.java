package com.example.task2_sqlite.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.task2_sqlite.model.Data;
import com.example.task2_sqlite.util.Util;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    public DatabaseHandler(Context context) {
        super(context, Util.DATABASE_NAME, null, Util.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + Util.DATABASE_TABLE + "("
                + Util.KEY_ID + " INTEGER PRIMARY KEY,"
                + Util.DATA + " TEXT,"
                + Util.DATE + " TEXT,"
                + Util.TIME + " TEXT" + ")";
        String CREATE_CONTACTS_TABLE1 = "CREATE TABLE " + Util.DATABASE_TABLE2 + "("
                + Util.KEY_ID_FAV + " INTEGER PRIMARY KEY,"
                + Util.DATA_FAV + " TEXT,"
                + Util.FAV_DATE + " TEXT,"
                + Util.FAV_TIME + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
        db.execSQL(CREATE_CONTACTS_TABLE1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Util.DATABASE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Util.DATABASE_TABLE2);

        // Create tables again
        onCreate(db);
    }

    // code to add the new contact
   public void addData(Data data) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Util.DATA, data.getData()); // Contact Name
        values.put(Util.DATE, data.getDate()); // Contact Name
        values.put(Util.TIME, data.getTime()); // Contact Name

        // Inserting Row
        db.insert(Util.DATABASE_TABLE, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    // code to get the single contact
   public Data getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Util.DATABASE_TABLE, new String[] { Util.KEY_ID,
                        Util.DATA ,Util.DATE,Util.TIME}, Util.KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Data data = new Data(Integer.parseInt(cursor.getString(0)),
                 cursor.getString(2));
        cursor.close();
        // return contact
        return data;
    }

    // code to get all contacts in a list view
    public List<Data> getAllContacts() {
        List<Data> datalist = new ArrayList<Data>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + Util.DATABASE_TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Data data = new Data();
                data.setId(Integer.parseInt(cursor.getString(0)));
                data.setData(cursor.getString(1));
                data.setDate(cursor.getString(2));
                data.setTime(cursor.getString(3));

                // Adding contact to list
                datalist.add(data);
            } while (cursor.moveToNext());
        }

        // return contact list
        cursor.close();
        return datalist;
    }

    // Deleting single contact
    public void deleteData(Data data) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Util.DATABASE_TABLE,
                "date =? AND time = ?",new String[]{data.getDate(),data.getTime()} );
        deleteFavData(data);
        db.close();
    }

    public void deleteFavData(Data data) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete( Util.DATABASE_TABLE2,
                "date =? AND time = ?",new String[]{data.getDate(),data.getTime()} );
        db.close();
    }
    public void addDataToFav(Data data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Util.DATA_FAV, data.getData()); // Contact Name
        values.put(Util.FAV_DATE, data.getDate()); // Contact Name
        values.put(Util.FAV_TIME, data.getTime()); // Contact Name

        // Inserting Row
        db.insert(Util.DATABASE_TABLE2, null, values);

        db.close(); // Closing database connection
    }

    public List<Data> getAllDetailFave() {
        List<Data> datalist = new ArrayList<Data>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + Util.DATABASE_TABLE2;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Data data = new Data();
                data.setId(Integer.parseInt(cursor.getString(0)));
                data.setData(cursor.getString(1));
                data.setDate(cursor.getString(2));
                data.setTime(cursor.getString(3));

                // Adding contact to list
                datalist.add(data);
            } while (cursor.moveToNext());
        }

        // return contact list
        cursor.close();
        return datalist;
    }


   public Boolean checkDatabaseForFavAndUnFav(String date, String time){

           SQLiteDatabase db = this.getReadableDatabase();

           Cursor cursor = db.query( Util.DATABASE_TABLE2,
                   new String[]{Util.KEY_ID_FAV, Util.FAV_TIME},
                  "date =? AND time = ?" , new String[]{date,time},
                   null, null, null );

       cursor.getCount();

       if (cursor.getCount() > 0 && cursor.moveToFirst()){
           cursor.close();;
           return true;
       }else{
           return false;
       }
    }


}
