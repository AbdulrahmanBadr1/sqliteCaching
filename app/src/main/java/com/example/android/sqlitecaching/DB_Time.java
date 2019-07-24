package com.example.android.sqlitecaching;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DB_Time extends SQLiteOpenHelper {
    private final String TABLE_NAME = "timing";
//    private  int UPDATE_PERIOD=1;

   /* private int lastUpdateMonth;
    private int lastUpdateDay;
    private int lastUpdateHour;
    private int lastUpdateMiniute;
    private int newUpdateMonth;
    private int newUpdateday;
    private int newUpdateHour;
    private int newUpdateMiniute;

    public int getLastUpdateMonth() {
        return lastUpdateMonth;
    }

    public void setLastUpdateMonth(int lastUpdateMonth) {
        this.lastUpdateMonth = lastUpdateMonth;
    }

    public int getLastUpdateDay() {
        return lastUpdateDay;
    }

    public void setLastUpdateDay(int lastUpdateDay) {
        this.lastUpdateDay = lastUpdateDay;
    }

    public int getLastUpdateHour() {
        return lastUpdateHour;
    }

    public void setLastUpdateHour(int lastUpdateHour) {
        this.lastUpdateHour = lastUpdateHour;
    }

    public int getLastUpdateMiniute() {
        return lastUpdateMiniute;
    }

    public void setLastUpdateMiniute(int lastUpdateMiniute) {
        this.lastUpdateMiniute = lastUpdateMiniute;
    }

    public int getNewUpdateMonth() {
        return newUpdateMonth;
    }

    public void setNewUpdateMonth(int newUpdateMonth) {
        this.newUpdateMonth = newUpdateMonth;
    }

    public int getNewUpdateday() {
        return newUpdateday;
    }

    public void setNewUpdateday(int newUpdateday) {
        this.newUpdateday = newUpdateday;
    }

    public int getNewUpdateHour() {
        return newUpdateHour;
    }

    public void setNewUpdateHour(int newUpdateHour) {
        this.newUpdateHour = newUpdateHour;
    }

    public int getNewUpdateMiniute() {
        return newUpdateMiniute;
    }

    public void setNewUpdateMiniute(int newUpdateMiniute) {
        this.newUpdateMiniute = newUpdateMiniute;
    }*/

    public DB_Time(@Nullable Context context) {
        super(context, "TEST.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //defining database tables
        //timting  lastUpdateTime , Update time , updatePeriod
       // sqLiteDatabase.execSQL("create table '" + TABLE_NAME + "'(tableName text primary key  ,lastUpdateMonth INTEGER ,lastUpdateDay INTEGER ,lastUpdateHour INTEGER,lastUpdateMiniute INTEGER);");
        sqLiteDatabase.execSQL("create table '" + TABLE_NAME + "'(tableName text primary key  ,lastUpdateDate String,updateMonthPeriod int,updateDayPeriod int ,updateHourPeriod int ,updateMinutePeriod int ,newUpdateDate String );");

        Cursor cursor = this.getReadableDatabase().rawQuery("select * from '" + TABLE_NAME + "' where  tableName='" + "branches" + "' ", null);

        /*while (cursor.moveToNext() != false) {
            lastUpdateMonth = cursor.getInt(1);
            lastUpdateDay = cursor.getInt(2);
            lastUpdateHour = cursor.getInt(3);
            lastUpdateMiniute = cursor.getInt(4);
        }
        newUpdateMonth = lastUpdateMonth;
        newUpdateday = lastUpdateDay;
        newUpdateHour = lastUpdateHour;
        newUpdateMiniute = lastUpdateMiniute + UPDATE_PERIOD;*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("drop table if exists '" + TABLE_NAME + "';");
        onCreate(sqLiteDatabase);

    }

    public void insert(TimingModel timingModel) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("tableName", timingModel.getTableName());
        contentValues.put("lastUpdateDate", timingModel.getLastUpdateDate());
       /* contentValues.put("lastUpdateDay", timingModel.getLastUpdateDay());
        contentValues.put("lastUpdateHour", timingModel.getLastUpdateHour());
        contentValues.put("lastUpdateMiniute", timingModel.getLastUpdateMiniute());*/
        this.getWritableDatabase().insertOrThrow(TABLE_NAME, "", contentValues);


    }

    public void update(TimingModel timingModel) {

        this.getWritableDatabase().execSQL("update '" + TABLE_NAME + "' set lastUpdateDate='" + timingModel.getLastUpdateDate()  + "'     where tableName ='" + timingModel.getTableName() + "'");

    }
    public  String timeAdder(String date ,int month , int day ,int hour , int min )
    {

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.HOUR_OF_DAY,hour);
        c.add(Calendar.MINUTE,min);
        c.add(Calendar.DAY_OF_MONTH, day);  // number of days to add, can also use Calendar.DAY_OF_MONTH in place of Calendar.DATE
        c.add(Calendar.MONTH,month);
        Log.d("newtime", "new time : "+sdf.format(c.getTime()));
        return sdf.format(c.getTime());

    }

    public TimingModel select(String tableName) {
        Cursor cursor = this.getReadableDatabase().rawQuery("select * from '" + TABLE_NAME + "' where  tableName='" + tableName + "' ", null);
        TimingModel timingModel = null;
        while (cursor.moveToNext() != false) {
            String table_Name = cursor.getString(0);
            String lastUpdateDate = cursor.getString(1);

            timingModel = new TimingModel(table_Name, lastUpdateDate);
        }
        return timingModel;

    }
}



