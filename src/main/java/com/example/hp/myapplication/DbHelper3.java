package com.example.hp.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 2/14/2019.
 */

public class DbHelper3 extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "MyDBName3.db";
    public static final String gas = null;
    public static final String date= null;
    public static final String time= null;
    public static final String TABLE_Information= null;
    public static final String TABLE_CONTACTS="info2";
    public DbHelper3(Context context) {
        super(context,DATABASE_NAME , null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table info2" +
                "(date text,time text,gas text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS info2");
        onCreate(db);
    }


    public void addDataModel(DataModel2 d){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("gas", d.getGas());
        contentValues.put("date", d.getDate());
        contentValues.put("time", d.getTime());
        db.insert("info2", null, contentValues);
        db.close();
    }

    public List<DataModel2> getAll() {
        List<DataModel2> contactList = new ArrayList<DataModel2>();
        // Select All Query
        //  String selectQuery =  "SELECT  * FROM  info1" ;
        String selectQuery = "SELECT *FROM  info2";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                //String NO2, String CO2,String PT, String date
                DataModel2 pollution_item= new DataModel2();

                pollution_item.setDate(cursor.getString(0));
                pollution_item.setTime(cursor.getString(1));
                pollution_item.setGas(cursor.getString(2));
                // Adding contact to list
                contactList.add(pollution_item);
            } while (cursor.moveToNext());
        }
        // return contact list
        return contactList;
    }
    //  SQLiteDatabase db = this.getWritableDatabase();
    //  return db.delete("contacts",
    //        "id = ? ",
    //      new String[] { Integer.toString(id) });
    public void deleteDataModel(DataModel2 m) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("info2", date + " = ?",
                new String[] { String.valueOf(m.getDate()) });
        db.close();
    }
}
