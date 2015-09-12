package com.example.abcde11yz.transfer;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;


public class ResultActivity extends ActionBarActivity {
    private Button returnButton;
    private TextView result_3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        setViewObject();
        setListener();
        getJson(getJSONObjectfromApi());

    }

    private void setViewObject() {
        returnButton = (Button) findViewById(R.id.returnButton);
        //edit_text_leave=(EditText)findViewById(R.id.leaveSta);
    }

    private View.OnClickListener return_Clicklistener = new View.OnClickListener() {
        public void onClick(View v) {
            submit_Click2(v);
        }
    };

    private void submit_Click2(View v) {
        Log.d("hello000", "45");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void setListener() {
        returnButton.setOnClickListener(return_Clicklistener);
    }

    private JSONObject getJSONObjectfromApi() {
        //APのURI
        Log.d("-----JSON----","getJSONObjectfromApi() is called");
        String uri = "http://api.ekispert.com/v1/json/station/info?key=Tz7zMBQarrxLSZf3&code=22828&type=rail:nearrail:exit:welfare";
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(uri);
        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                httpResponse.getEntity().writeTo(outputStream);
                outputStream.close();
                return new JSONObject(outputStream.toString());
            } else {
                httpResponse.getEntity().getContent().close();
                throw new IOException();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void getJson(JSONObject result) {
        try {
            // 各 ATND イベントのタイトルを配列へ格納
            JSONArray resultArray = result.getJSONArray("ResultSet");
            Log.d("------------JSON-------", resultArray.get(0).toString());
            JSONObject informationObj = resultArray.getJSONObject(2);
            JSONArray welfareArray = informationObj.getJSONArray("3");
            JSONObject welfareFacilitiesObj = welfareArray.getJSONObject(2);
            JSONArray elevatorArray = welfareFacilitiesObj.getJSONArray("1");
            Log.d("------------JSON-------", elevatorArray.getString(1));
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("------JSON--------","データを取得できませんでした。");
        }
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
