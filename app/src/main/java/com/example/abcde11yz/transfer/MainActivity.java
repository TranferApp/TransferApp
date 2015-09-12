package com.example.abcde11yz.transfer;

import android.app.ActionBar;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

//Test
public class MainActivity extends ActionBarActivity {
    private Button search_button;
    private Button select_arrive;
    private Button select_leave;
    private Button select_now;
    private EditText edit_text_leave;
    private EditText yearText;
    private EditText monthText;
    private EditText dayText;
    private EditText dateText;
    private EditText hourText;
    private EditText minText;
    private EditText secText;
    private ArrayList<String> arrayList ;
    private String args;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViewObject();
        setListener();
        getTime();
        // setArrayBt();

    }

    private  View.OnClickListener search_Clicklistener = new View.OnClickListener() {
            public void onClick(View v) {
                submit_Click(v);
            }
    };

    private  View.OnClickListener leave_Clicklistener = new View.OnClickListener() {
        public void onClick(View v) {
                    select_leave.setBackgroundColor(Color.rgb(219, 112, 147));
                    select_arrive.setBackgroundColor(Color.rgb(255, 182, 193));
                    select_now.setBackgroundColor(Color.rgb(255, 182, 193));
            }
    };
    private  View.OnClickListener arrive_Clicklistener = new View.OnClickListener() {
        public void onClick(View v) {
                    select_arrive.setBackgroundColor(Color.rgb(219, 112, 147));
                    select_leave.setBackgroundColor(Color.rgb(255, 182, 193));
                    select_now.setBackgroundColor(Color.rgb(255, 182, 193));
        }
    };
    private  View.OnClickListener now_Clicklistener = new View.OnClickListener() {
        public void onClick(View v) {
                    select_arrive.setBackgroundColor(Color.rgb(255, 182, 193));
                    select_leave.setBackgroundColor(Color.rgb(255, 182, 193));
                    select_now.setBackgroundColor(Color.rgb(219, 112, 147));
        }
    };

    private void submit_Click(View v) {
        Log.d("hellohhlo","33");
        Intent intent = new Intent(this, ResultActivity.class);
        startActivity(intent);
    }

    private void setViewObject (){
        search_button=(Button)findViewById(R.id.searchButton);
        edit_text_leave=(EditText)findViewById(R.id.leaveSta);
        select_leave=(Button)findViewById(R.id.selectLeave);
        select_arrive=(Button)findViewById(R.id.selectArrive);
        select_now=(Button)findViewById(R.id.selectNow);
        yearText =(EditText)findViewById(R.id.year);
        monthText =(EditText)findViewById(R.id.month);
        dayText =(EditText)findViewById(R.id.day);
        dateText =(EditText)findViewById(R.id.date);
        hourText =(EditText)findViewById(R.id.hour);
        minText =(EditText)findViewById(R.id.minutes);
        secText =(EditText)findViewById(R.id.second);
    }

    private void setListener(){
        search_button.setOnClickListener(search_Clicklistener);
        select_leave.setOnClickListener(leave_Clicklistener);
        select_arrive.setOnClickListener(arrive_Clicklistener);
        select_now.setOnClickListener(now_Clicklistener);
    }



    private void setArrayBt() {
        ArrayList<Button> arrayList = new ArrayList<Button>();
        arrayList.add(select_leave);
        arrayList.add(select_arrive);
        arrayList.add(select_now);

        Button select_arrive = arrayList.get(0);
        Button select_leave = arrayList.get(1);
        Button select_now = arrayList.get(2);

    }

    private void getTime(){
        Calendar calendar = Calendar.getInstance(); //インスタンス化
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int day   = calendar.get(Calendar.DAY_OF_MONTH);
        String date = "";
        date = getDayOfTheWeekShort(calendar);
        //int week = calendar.get(Calendar.DAY_OF_WEEK);
        int hour  = calendar.get(Calendar.HOUR_OF_DAY);
        int min   = calendar.get(Calendar.MINUTE);
        int sec   = calendar.get(Calendar.SECOND);
        //int ms    = calendar.get(Calendar.MILLISECOND);
        yearText.setText(""+year);
        monthText.setText(""+month);
        dayText.setText(""+day);
        dateText.setText(""+date);
        hourText.setText(""+hour);
        minText.setText(""+min);
        secText.setText(""+sec);
    }

    public static String getDayOfTheWeekShort(Calendar cal) {
        switch (cal.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.SUNDAY: return "日";
            case Calendar.MONDAY: return "月";
            case Calendar.TUESDAY: return "火";
            case Calendar.WEDNESDAY: return "水";
            case Calendar.THURSDAY: return "木";
            case Calendar.FRIDAY: return "金";
            case Calendar.SATURDAY: return "土";
        }
        throw new IllegalStateException();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
