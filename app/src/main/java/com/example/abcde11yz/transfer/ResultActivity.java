package com.example.abcde11yz.transfer;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

//import test.mackiso.tabtest.R;



public class ResultActivity extends ActionBarActivity {
    private Button returnButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        setViewObject();
        setListener();
        //ActionBarをGetしてTabModeをセット
        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        actionBar.addTab(actionBar.newTab()
                .setText("First")
                .setTabListener(new TabListener<FirstTabFragment>(
                        this, "tag1", FirstTabFragment.class)));
        actionBar.addTab(actionBar.newTab()
                .setText("Second")
                .setTabListener(new TabListener<SecondTabFragment>(
                        this, "tag2", SecondTabFragment.class)));
        actionBar.addTab(actionBar.newTab()
                .setText("Third")
                .setTabListener(new TabListener<ThirdTabFragment>(
                        this, "tag3", ThirdTabFragment.class)));
    }

    private void setViewObject (){
        returnButton=(Button)findViewById(R.id.returnButton);
        //edit_text_leave=(EditText)findViewById(R.id.leaveSta);
    }

      private  View.OnClickListener return_Clicklistener = new View.OnClickListener() {
            public void onClick(View v) {
            submit_Click2(v);
            }
    };

    private void submit_Click2(View v) {
        Log.d("hello000", "45");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void setListener(){
        returnButton.setOnClickListener(return_Clicklistener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_result_acvtivity, menu);
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
//フラグメントをタブで切り替える
        return super.onOptionsItemSelected(item);
    }
}
