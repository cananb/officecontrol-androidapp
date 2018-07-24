package com.exam.ex_rec_card.Activities;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.exam.ex_rec_card.HttpGetRequest;
import com.exam.ex_rec_card.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Step3Activity extends AppCompatActivity {


    String nem,sicaklik,gurultu,ofisAdi;
    Toolbar toolbar;
    TextView tv_nem,tv_sicaklik,tv_gurultu,tv_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step3);

        toolbar=(Toolbar)findViewById(R.id.toolbar_step3);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tv_nem=findViewById(R.id.tv_step3_nem);
        tv_gurultu=findViewById(R.id.tv_step3_gurultu);
        tv_sicaklik=findViewById(R.id.tv_step3_sicaklik);
        tv_title=findViewById(R.id.toolbar_title_step3);

        Intent i=getIntent();
        nem=i.getStringExtra("nem");
        sicaklik=i.getStringExtra("sicaklik");
        gurultu=i.getStringExtra("gurultu");
        ofisAdi=i.getStringExtra("ofisAdi");

        tv_title.setText(ofisAdi);
        tv_sicaklik.setText(sicaklik);
        tv_gurultu.setText(gurultu);
        tv_nem.setText(nem);
        sacmaKod ();
        Thread timerThread= new Thread(){
            public void run(){
                try {
                    sleep(1000);

                    //sacmaKod ();

                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        //timerThread.start();
    }

    private void sacmaKod() {
        String myUrl = "https://empa32-node.eu-gb.mybluemix.net/api/last";
        JSONObject result = null;
        HttpGetRequest getRequest = new HttpGetRequest();
        try {
            result = getRequest.execute(myUrl).get().getJSONObject (0);
            //Object o =result.get ("temp");

            tv_sicaklik.setText(result.get ("temp").toString ());
            Log.e ("------------------","cikti yaz bakim  "+result.get ("temp").toString ());
            String zaman= result.getString ("time");
        } catch (Exception e){
            Log.e ("step3","Hata : "+e);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }


}
