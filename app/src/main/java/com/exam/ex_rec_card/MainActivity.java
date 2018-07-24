package com.exam.ex_rec_card;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.exam.ex_rec_card.Adapters.RcyclerAdapterItem;
import com.exam.ex_rec_card.Model.RecylerItemModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    RecyclerView rcyItemList;
    RecyclerView.LayoutManager manager;
    RcyclerAdapterItem adapterItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rcyItemList=findViewById(R.id.rcycItemList);
        rcyItemList.setHasFixedSize(true);
        manager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        rcyItemList.setLayoutManager(manager);

        List<RecylerItemModel> ls=new ArrayList<>();
        RecylerItemModel rm=new RecylerItemModel() ;
        rm.setIcon(R.drawable.teknoloji);
        rm.setTitle("teknoloji");
        rm.setDescp("3");
        ls.add(rm);
        rm=new RecylerItemModel();
        rm.setTitle("elektronik");
        rm.setDescp("4");
        rm.setIcon(R.drawable.elecronic);
        ls.add(rm);
        rm=new RecylerItemModel();
        rm.setTitle("enerji");
        rm.setDescp("3");
        rm.setIcon(R.drawable.enerji);
        ls.add(rm);

        adapterItem=new RcyclerAdapterItem(ls);
        rcyItemList.setAdapter(adapterItem);

    }
}
