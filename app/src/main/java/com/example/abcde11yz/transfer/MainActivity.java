package com.example.abcde11yz.transfer;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


//test
public class MainActivity extends ActionBarActivity {
    private Button search_button;
    private EditText edit_text_leave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViewObject();
        setListner();
    }

    private  View.OnClickListener search_Clickistener = new View.OnClickListener() {
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
    }

    private void setListner(){
        search_button.setOnClickListener(search_Clickistener);
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
