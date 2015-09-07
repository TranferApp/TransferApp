package com.example.abcde11yz.transfer;

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
import android.widget.Toast;

import java.util.ArrayList;


//Test
public class MainActivity extends ActionBarActivity {
    private Button search_button;
    private Button select_arrive;
    private Button select_leave;
    private Button select_now;
    private EditText edit_text_leave;
    private ArrayList<String> arrayList ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViewObject();
        setListener();
        setArrayBt();
    }

    private  View.OnClickListener search_Clicklistener = new View.OnClickListener() {
            public void onClick(View v) {
                submit_Click(v);
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

       /* select_leave=(Button)findViewById(R.id.selectLeave);
        select_arrive=(Button)findViewById(R.id.selectArrive);
        select_now=(Button)findViewById(R.id.selectNow);*/
    }

    private void setListener(){
        search_button.setOnClickListener(search_Clicklistener);

     /*   select_leave.setOnClickListener(this);
        select_arrive.setOnClickListener(this);
        select_now.setOnClickListener(this);*/
    }

    private void setArrayBt() {
        //kokokara
       /* arrayList = new ArrayList<Button>();
        arrayList.add(threadTest);
        arrayList.add(customlistview);

        Button select_arrive = arrayList.get(0);
        Button select_leave = arrayList.get(1);
        Button select_now = arrayList.get(2);

        select_arrive.setBackgroundColor(Color.rgb(0, 0, 0));
        select_leave.setBackgroundColor(Color.rgb(127, 127, 127));
        select_now.setBackgroundColor(Color.rgb(127, 127, 127));
        */
//kokomade
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
