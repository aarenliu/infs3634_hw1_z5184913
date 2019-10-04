package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import android.widget.Button;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity  {
    Calendar current;
    TextView txtSydneyTime, txtHongKongTime,txtLondonTime, txtJapanTime, txtNewYorkTime;
    long millSeconds;
    SimpleDateFormat sdf;
    Date Date;
    int offset;
    Button btn12,btn24;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtSydneyTime = (TextView) findViewById(R.id.txtSydneyTime); //Sydney Time
        txtHongKongTime = (TextView) findViewById(R.id.txtHongKongTime); // HongKong Time
        txtLondonTime = (TextView) findViewById(R.id.txtLondonTime); //London Time
        txtJapanTime = (TextView) findViewById(R.id.txtJapanTime);//Japan Time
        txtNewYorkTime = (TextView) findViewById(R.id.txtNewYorkTime); //NewYork Time
        btn12  = (Button)findViewById(R.id.btn12); //Button - 12-hour am-pm clock
        btn24 = (Button)findViewById(R.id.btn24);// Button - 24-hour military time
        sdf = new SimpleDateFormat("EEEE, dd MMMM yyyy hh:mm aa");
        getbtn(); //start button
        getGMTtime(); // start: get the world time from the world clock
}
    private void getbtn(){ //12H - button function
        btn12.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){ //when the (12H)button is clicked
                sdf = new SimpleDateFormat("EEEE, dd MMMM yyyy hh:mm aa"); // sdf will change the time format to 12 hour am-pm clock
                getGMTtime(); // run the GMTtime - get the latest time
            }
        });
        btn24.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){ //24H - button function
                sdf = new SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm:ss"); // sdf will change the time format to 24-hour military time
                getGMTtime();  // run the GMTtime - get the latest time
            }
        });
    }

    public void getGMTtime(){ //run the program - get the latest time for each city
        getGMT();//get the latest time
        getSyd(); //Sydney Time
        getGMT();
        gethk();//HongKong Time
        getGMT();
        getJp();//Japan Time
        getGMT();
        getNY();//New York Time
        getGMT();
        getLondon(); //London Time
    }

    public void getGMT() { // get the latest time from world clock
        current = Calendar.getInstance();
        millSeconds = current.getTimeInMillis();
        TimeZone tzCurrent = current.getTimeZone();
        offset = tzCurrent.getRawOffset();
        if (tzCurrent.inDaylightTime(new Date())) {
            offset = offset + tzCurrent.getDSTSavings();
        }
        millSeconds = millSeconds - offset;
    }

    private void gethk(){
        String hk = "Hongkong"; //HongKong Time
        TimeZone hktz = TimeZone.getTimeZone(hk);
        millSeconds = millSeconds +hktz.getRawOffset();
        Date = new Date(millSeconds);
        System.out.println(sdf.format(Date)); //Print out the latest time
        txtHongKongTime.setText(""+sdf.format(Date));  //set the 12/24 hours format to the lstest time
        millSeconds = 0;
    }

    private void getSyd(){
        String Syd = "Australia/Sydney"; //Sydney Time
        TimeZone sydtz = TimeZone.getTimeZone(Syd);
        millSeconds = millSeconds +sydtz.getRawOffset();
        Date = new Date(millSeconds);
        System.out.println(sdf.format(Date)); //Print out the latest time
        txtSydneyTime.setText(""+sdf.format(Date));  //set the 12/24 hours format to the lstest time
        millSeconds = 0;
    }


    private void getJp(){
        String jp = "Japan"; //Japan Time
        TimeZone jptz = TimeZone.getTimeZone(jp);
        millSeconds = millSeconds +jptz.getRawOffset();
        Date = new Date(millSeconds);
        System.out.println(sdf.format(Date)); //Print out the latest time
        txtJapanTime.setText(""+sdf.format(Date));  //set the 12/24 hours format to the lstest time
        millSeconds = 0;
    }


    private void getNY(){
        String ny = "GMT-4"; //New York Time
        TimeZone nytz = TimeZone.getTimeZone(ny);
        millSeconds = millSeconds +nytz.getRawOffset();
        Date = new Date(millSeconds);
        System.out.println(sdf.format(Date)); //Print out the latest time
        txtNewYorkTime.setText(""+sdf.format(Date));  //set the 12/24 hours format to the lstest time
        millSeconds = 0;
    }

    private void getLondon(){
        String London = "GMT+1"; //London Time
        TimeZone Londontz = TimeZone.getTimeZone(London);
        millSeconds = millSeconds +Londontz.getRawOffset();
        Date = new Date(millSeconds);
        System.out.println(sdf.format(Date)); //Print out the latest time
        txtLondonTime.setText(""+sdf.format(Date));  //set the 12/24 hours format to the lstest time
        millSeconds = 0;
    }

}
