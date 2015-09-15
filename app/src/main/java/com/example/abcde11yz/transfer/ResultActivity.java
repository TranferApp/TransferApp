package com.example.abcde11yz.transfer;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
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
    private JSONObject jsonObj;
    private TextView textSta;
    private TextView textSta2;
    private TextView textSta3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        setViewObject();
        setListener();
        Intent intent = getIntent();
        Log.v("leaveSta",intent.getStringExtra("leaveSta"));
        set_Leave_Station_name(intent.getStringExtra("leaveSta"));
       //getJson(getJSONObjectfromApi());
        getJSONObjectfromApi_Take();
    }


    private void setViewObject() {
        returnButton = (Button) findViewById(R.id.returnButton);
        //edit_text_leave=(EditText)findViewById(R.id.leaveSta);
        textSta=(TextView)findViewById(R.id.textSta);
    }

    private void set_Leave_Station_name(String leaveSta) {
        textSta.setText(leaveSta);
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
    //えきすぱーとAPIのJSON-JSO情報から必要なエレベーター情報抜き出し
    private void getJson(JSONObject result) {
        try {
            // 各 ATND イベントのタイトルを配列へ格納
            JSONObject resultObj = result.getJSONObject("ResultSet");
           // Log.d("------------JSON-------", resultObj.get(0).toString());
            JSONArray InformationArray = resultObj.getJSONArray("Information");
            JSONObject ExitObj = (JSONObject)InformationArray.get(2);
            JSONObject welfareFacilitiesObj= (JSONObject)InformationArray.get(3);
            //JSONArray elevatorArray = welfareFacilitiesObj.getJSONArray("1");
            //Log.d("------------JSON-------", elevatorArray.getString(1));
            String type = welfareFacilitiesObj.getString("Type");
            JSONArray welfareFacilitiesArray = welfareFacilitiesObj.getJSONArray("WelfareFacilities");

            //countここで表示
            int count = welfareFacilitiesArray.length();
            for (int c = 0;c<count ;c++) {
                JSONObject welFarrObj = (JSONObject) welfareFacilitiesArray.get(c);
                String name = welFarrObj.getString("Name");
                String comment = welFarrObj.getString("Comment");

                Log.d("------JSON--------","name:"+name+"||comment:"+comment);


            }

        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("------JSON--------", "データを取得できませんでした。");
        }
    }
    //internet接続の場合はMythreadGetListDat実行
    private void getJSONObjectfromApi_Take () {
        String uri = "http://api.ekispert.com/v1/json/station/info?key=Tz7zMBQarrxLSZf3&code=22828&type=rail:nearrail:exit:welfare";

        if (Util.isConnectedNetwork(this) > 0) {
            Thread threadNews = new Thread(new MythreadGetListData(uri));
            threadNews.start();
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "ネットワークに繋がっておりません。", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER, 0, 0);
            toast.show();
        }
    }

    /**
     */
    private class MythreadGetListData implements Runnable {

        private String responce_json;
        private String url = "";

        protected Handler mHandlerLatLng = new Handler() {
            @Override
            public void handleMessage(Message msg) {
//                v("api_responce", "::::" + (String) msg.obj + ":");


            }
        };

        //
        public MythreadGetListData(String url) {
            // TODO 自動生成されたコンストラクター・スタブ
            this.url = url;
        }

        //Httprequestと同期し、JSONの値を返す
        public void run() {

            HttpRequest hr = new HttpRequest();
            responce_json = hr.doGet(this.url);


            // ハンドラにメッセージを通知
            mHandlerLatLng.sendEmptyMessage(0);
            mHandlerLatLng.post(new Runnable() {

                public void run() {

                    try {

                        if (responce_json != null) {
                            jsonObj = new JSONObject(responce_json);

                            getJson(jsonObj);

//                            JSONObject resultsObj = addr_json.getJSONObject("Results");
//                            JSONArray resultsArg = resultsObj.getJSONArray("Data");
                        }

                    } catch (JSONException e) {
                        // TODO 自動生成された catch ブロック
                        e.printStackTrace();

                    }


                }

            });

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
