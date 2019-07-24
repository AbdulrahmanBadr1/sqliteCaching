package com.example.android.sqlitecaching;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    EditText etName, etMail, etAge, id;
    TextView content;
    DB_Students student;
    DB_Time time;
    Context mContext;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm");


    private String name, mailid, age;
    String currentDate,dbDate;
    /**remove these int variable and replace it with above strings*/
    int currentMonth, currentDay, currentHour, currentminiute, dbMonth = 7, dbDay = 9, dbHour = 19, dbMiniute = 31;
    private int ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = getApplicationContext();
        etName = findViewById(R.id.name);
        etMail = findViewById(R.id.mailID);
        etAge = findViewById(R.id.age);
        id = findViewById(R.id.id);
        content = findViewById(R.id.content);
        student = new DB_Students(mContext);
        if (isNetworkAvailable()) {
            Log.d("time", "onCreate: internet");
            Toast.makeText(mContext, "internet", Toast.LENGTH_SHORT).show();
        }




       /* Log.d("date", "date: ");
        currentMonth = Integer.parseInt(String.valueOf(calendar.get(Calendar.MONTH))) + 1;
        currentDay = Integer.parseInt(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
        currentHour = Integer.parseInt(String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)));
        currentminiute = Integer.parseInt(String.valueOf(calendar.get(Calendar.MINUTE)));
*/
         time = new DB_Time(mContext);

       /* if (currentDate>= time.getNewUpdateMonth())
        {
            //update data

            time.setLastUpdateMonth(currentMonth);


        }
        if (currentDay>=time.getNewUpdateday())
        {
            //update data

            time.setLastUpdateDay(currentDay);

        }
        if (currentHour>=time.getNewUpdateHour())
        {
            //update data

            time.setLastUpdateHour(currentHour);

        }
        if (currentminiute>= time.getNewUpdateMiniute()){
            //update data

            time.setNewUpdateMiniute(currentminiute);}*/
        try {
            checkDate(Calendar.getInstance().getTime(),"10-6-2019 10:50");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Log.d("time", "month : " + currentMonth + ",  day : " + currentDay + ", currentHour : " + currentHour + ", currentminiute : " + currentminiute);
dateMaker("25","07","2019","10","25");
        getData1();
    }


    private void getData1() {
        ArrayList<SModel> mList = new ArrayList<>();
        if (currentMonth >= dbMonth) {
            if (currentDay >= dbDay) {
                if (currentHour >= dbHour) {
                    if (currentminiute >= dbMiniute) {
//                        //get data from DB
                        for (int i = 0; i < 10; i++) {
                            mList.add(new SModel(i, "name", "mail", "age"));
                            student.insert( new SModel(i, "name", "mail", "age"));
                            Toast.makeText(mContext, "from DB " + mList.size(), Toast.LENGTH_SHORT).show();

                        }
                        return;
                    }
                }
            }
        }
        else {
            ArrayList<SModel> sqList = new ArrayList<>();

        }
    }
    public void dateMaker( String day,  String month ,String year  , String hour, String minute)
    {
        String x =day+"-"+month+"-"+year+" "+hour+":"+minute;
        Date daa = null;
        try {
             daa = sdf.parse(x);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Log.d("nnn", "dateMaker: "+x);
        Log.d("nnn", "dateMaker: "+daa);
    }
    public  void dateAdder(String date ,int month , int day ,int hour , int min )
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

    }
    public boolean checkDate(Date currentDate, String updateDate) throws ParseException {


        if (currentDate.compareTo(sdf.parse(updateDate))>=0)
        {
            Log.d("check", "checkDate: "+sdf.parse(updateDate));
            Log.d("check", "checkDate: current date greater than update date ");
            return  true;


        }

        return false;
    }

    public void delete(View view) {
        getData();
        if (id.getText().toString().equals("")) {
            Toast.makeText(mContext, "please enter record id to delete", Toast.LENGTH_SHORT).show();
        } else {
            ID = Integer.parseInt(id.getText().toString());
            student.delete(ID);
            Toast.makeText(MainActivity.this, "record deleted", Toast.LENGTH_SHORT).show();
        }

    }


    public void update(View view) {

        try {
            getData();
            if (id.getText().toString().equals("") || etName.equals("") || etMail.getText().toString().equals("") || etAge.getText().toString().equals("")) {
                Toast.makeText(mContext, "please enter record id , new values of name,mail,age", Toast.LENGTH_SHORT).show();
            } else {
                ID = Integer.parseInt(id.getText().toString());
                student.update(new SModel(1, "name", "mail", "age"));
                Toast.makeText(MainActivity.this, "record updated", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception ex) {
            Log.d("update", "update: " + ex.getMessage());
            Toast.makeText(mContext, "update error:" + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    public void selectall(View view) {
        student.listall(content);
    }

    public void select(View view) {

        getData();
        if (id.getText().toString().equals("")) {
            Toast.makeText(mContext, "please enter record id to select", Toast.LENGTH_SHORT).show();
        } else {
            ID = Integer.parseInt(id.getText().toString());
            if (isNetworkAvailable()) {
                student.select(content, ID);
                Toast.makeText(MainActivity.this, "record selected", Toast.LENGTH_SHORT).show();
            } else {
            }
            student.select(content, ID);
            Toast.makeText(MainActivity.this, "record selected but no internet", Toast.LENGTH_SHORT).show();
        }

    }

    public void getData() {
        //   ID = Integer.parseInt(id.getText().toString());
        name = etName.getText().toString().trim();
        mailid = etMail.getText().toString().trim();
        age = etAge.getText().toString().trim();
    }
    public void insert()
    {
              student.insert(new SModel(1,"name","mail","age"));

    }

    public void save(View view) {


//        try {
//            getData();
//            if (etName.equals("") || etMail.getText().toString().equals("") || etAge.getText().toString().equals("")) {
//                Toast.makeText(mContext, "  values of name,mail,age", Toast.LENGTH_SHORT).show();
//            } else {
//                student.insert(5,name, mailid, age);
//                Toast.makeText(mContext, "record saved", Toast.LENGTH_SHORT).show();
//                etName.setText("");
//                etAge.setText("");
//                etMail.setText("");
//            }
//
//        } catch (Exception ex) {
//            Log.d("update", "update: " + ex.getMessage());
//            Toast.makeText(mContext, "update error:" + ex.getMessage(), Toast.LENGTH_SHORT).show();
//        }


    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
