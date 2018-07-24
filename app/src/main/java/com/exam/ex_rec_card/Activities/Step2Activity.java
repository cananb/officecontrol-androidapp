package com.exam.ex_rec_card.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.exam.ex_rec_card.Adapters.RecyclerAdapterStep2Item;
import com.exam.ex_rec_card.HttpGetRequest;
import com.exam.ex_rec_card.Model.RecylerStep2ItemModel;
import com.exam.ex_rec_card.R;

import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Step2Activity extends AppCompatActivity {


    RecyclerView rcyStep2;

    RecyclerView.LayoutManager manager;
    RecyclerAdapterStep2Item adapter;
    String kim;
    TextView tv_title;
    Toolbar toolbar;

    List<String > sicakliklar;
    List<String > gurultuler;
    List<String> nemler;
    List<String> ofisIsimleri;
    List<String > cerceveBelirle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_step2);
        toolbar=findViewById(R.id.toolbar_step2);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tv_title=findViewById(R.id.toolbar_title_step2);
        rcyStep2=findViewById(R.id.recycler_step2);
        rcyStep2.setHasFixedSize(true);
        manager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        rcyStep2.setLayoutManager(manager);

        Intent i=getIntent();
        kim=i.getStringExtra("kim");
        tv_title.setText(kim.toUpperCase());

        ofisIsimleri=new ArrayList<>();
        ofisIsimleri.add("Büyük Toplantı Odası");
        ofisIsimleri.add("Küçük Toplantı Odası");
        ofisIsimleri.add("Orta Toplantı Odasi");
        ofisIsimleri.add("Elektronik Ofis");
        ofisIsimleri.add("Teknoloji Ofis");
        ofisIsimleri.add("Enerji  Ofis");

        nemler=new ArrayList<>();
        nemler.add("%29");
        nemler.add("%25");
        nemler.add("%19");
        nemler.add("%23");

        gurultuler=new ArrayList<>();
        gurultuler.add("%30");
        gurultuler.add("%23");
        gurultuler.add("%18");
        gurultuler.add("%21");

        sicakliklar=new ArrayList<>();
        sicakliklar.add("0");
        sicakliklar.add("1");
        sicakliklar.add("0");
        sicakliklar.add("0");

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    while(true) {
                        sleep(2000);

                        String myUrl = "https://empa32-node.eu-gb.mybluemix.net/api/last";
                        JSONObject result = null;
                        HttpGetRequest getRequest = new HttpGetRequest();
                        try {
                            result = getRequest.execute(myUrl).get().getJSONObject (0);
                            BigDecimal bd = new BigDecimal (Float.parseFloat (result.get ("temp").toString ()));
                            sicakliklar.set (0, (bd.setScale(2,BigDecimal.ROUND_HALF_EVEN) + " C"));
                            Log.i ("*****************", result.get ("temp").toString ());

                            adapter.notifyDataSetChanged ();
                        } catch (Exception e) {

                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        //thread.start();

        cerceveBelirle=new ArrayList<>();
        cerceveBelirle.add("1");
        cerceveBelirle.add("0");
        cerceveBelirle.add("1");
        cerceveBelirle.add("0");

        refreshAdapter ();
    }
    private class  veriCek extends AsyncTask<Void,Void,Void>{



        @Override
        protected Void doInBackground(Void... voids) {

            String myUrl = "https://empa32-node.eu-gb.mybluemix.net/api/last";
            JSONObject result = null;
            HttpGetRequest getRequest = new HttpGetRequest();
            try {
                result = getRequest.execute(myUrl).get().getJSONObject (0);
                BigDecimal bd = new BigDecimal (Float.parseFloat (result.get ("temp").toString ()));
                sicakliklar.set (0, (bd.setScale(2,BigDecimal.ROUND_HALF_EVEN) + " C"));

                Log.i ("*****************", result.get ("temp").toString ());

                adapter.notifyDataSetChanged ();
            } catch (Exception e) {

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute (aVoid);
        }
    }
    public void refreshAdapter(){

        if (kim!=null){
            if (kim.equals("elektronik")){
                List<RecylerStep2ItemModel> rls=new ArrayList<>();

                for (int j = 0; j < 4; j++) {
                    RecylerStep2ItemModel rm=new RecylerStep2ItemModel();
                    rm.setGurultu(gurultuler.get(j));
                    rm.setNem(nemler.get(j));
                    rm.setOfisAdi(ofisIsimleri.get(j));
                    rm.setSicaklikBelirle(cerceveBelirle.get(j));
                    rm.setSicaklik(sicakliklar.get(j));
                    rm.setLayoutbg(R.drawable.blured);

                    rls.add(rm);
                }
                adapter=new RecyclerAdapterStep2Item(rls);
                rcyStep2.setAdapter(adapter);
            }else if (kim.equals("teknoloji")
                    || kim.equals("enerji")){
                List<RecylerStep2ItemModel> rls=new ArrayList<>();

                for (int j = 0; j < 3; j++) {
                    RecylerStep2ItemModel rm=new RecylerStep2ItemModel();
                    rm.setGurultu(gurultuler.get(j));
                    rm.setNem(nemler.get(j));
                    if (j==2){
                        if (kim.equals("teknoloji")){
                            rm.setOfisAdi("Teknoloji Ofis");

                        }
                        if (kim.equals("enerji")){
                            rm.setOfisAdi("Enerji Ofis");
                        }
                    }

                    if (j<2)
                        rm.setOfisAdi(ofisIsimleri.get(j));

                    rm.setSicaklikBelirle(cerceveBelirle.get(j));
                    rm.setSicaklik(sicakliklar.get(j));
                    rm.setLayoutbg(R.drawable.blured);

                    rls.add(rm);
                }
                adapter=new RecyclerAdapterStep2Item(rls);
                rcyStep2.setAdapter(adapter);
            }
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

    @Override
    protected void onResume() {
        super.onResume ();

    }
}
