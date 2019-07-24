package com.example.android.sqlitecaching;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.TextView;

public class DB_Students extends SQLiteOpenHelper {
    private final  String TABLE_NAME="studnets";
    public DB_Students(@Nullable Context context) {
        super(context, "TEST.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //defining database tables
        //timting  lastUpdateTime , Update time , updatePeriod
        sqLiteDatabase.execSQL("create table '"+TABLE_NAME+"'(id integer primary KEY  , firstname text , mail text , age text    );");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("drop table if exists students;");
        sqLiteDatabase.execSQL("drop table if exists timing;");
        onCreate(sqLiteDatabase);

    }


    public void insert(SModel model) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", model.getId());
        contentValues.put("firstname", model.getName());
        contentValues.put("mail", model.getMail());
        contentValues.put("age", model.getAge());
        this.getWritableDatabase().insertOrThrow(TABLE_NAME, "", contentValues);


    }

    public void delete(int id) {

        this.getWritableDatabase().delete(TABLE_NAME, "id='" + id + "'", null);

    }

    public void update(SModel model) {

         this.getWritableDatabase().execSQL("update '"+TABLE_NAME+"' set firstname='" + model.getName() + "' ,  mail='" + model.getMail() + "', age='"+model.getAge()+"'   where id= '"+model.getId()+"' ");

    }

    public void listall(TextView textView) {
        textView.setText("");
        Cursor cursor = this.getReadableDatabase().rawQuery("select * from '"+TABLE_NAME+"'", null);
        while (cursor.moveToNext() != false) {
            textView.append("id : " + cursor.getString(0) + "first name : " + cursor.getString(1) + ", mail : " + cursor.getString(2) + ", age " + cursor.getString(3) + "\n");
        }


    }

    public void select(TextView tv, int id) {
        Cursor cursor = this.getReadableDatabase().rawQuery("select * from '"+TABLE_NAME+"' where id='" + id + "' ", null);
        while (cursor.moveToNext() != false) {
            if (tv.getText().toString() == "") {
                tv.append("id : " + cursor.getString(0) + "first name : " + cursor.getString(1) + ", mail : " + cursor.getString(2) + ", age " + cursor.getString(3) + "\n");
            } else {
                tv.setText("");


                tv.append("id : "+cursor.getString(0)+", first name : " + cursor.getString(1) + ", mail : " + cursor.getString(2) + ", age " + cursor.getString(3) + "\n");
            }

        }
    }
}



