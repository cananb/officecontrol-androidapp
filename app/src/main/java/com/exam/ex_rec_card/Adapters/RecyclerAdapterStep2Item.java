package com.exam.ex_rec_card.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.exam.ex_rec_card.Activities.Step3Activity;
import com.exam.ex_rec_card.Model.RecylerStep2ItemModel;
import com.exam.ex_rec_card.R;

import java.util.List;

/**
 * Created by Ahmet on 24.4.2018.
 */

public class RecyclerAdapterStep2Item  extends RecyclerView.Adapter<RecyclerAdapterStep2Item.ViewHolder>{
    Context context;
    List<RecylerStep2ItemModel> list;
    public RecyclerAdapterStep2Item(List<RecylerStep2ItemModel> list){
        this.list=list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_second_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final RecylerStep2ItemModel model=list.get(position);
        holder.tv_ofis_adi.setText(model.getOfisAdi());
        holder.tv_gurultu.setText(model.getGurultu());
        holder.tv_nem.setText(model.getNem());
        holder.tv_sicaklik.setText(model.getSicaklik());
        holder.rl_ofis_arkaplan.setBackgroundResource(model.getLayoutbg());

        if (model.getSicaklikBelirle()!=null )
        if ( model.getSicaklikBelirle().equals("1")){
            holder.rl_redorgreen.setBackgroundColor(Color.parseColor("#5fff0000"));

        }else {
            holder.rl_redorgreen.setBackgroundColor(Color.parseColor("#5F00FF37"));
        }


        holder.rl_ofis_arkaplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, Step3Activity.class)
                .putExtra("nem",model.getNem())
                .putExtra("sicaklik",model.getSicaklik())
                .putExtra("ofisAdi",model.getOfisAdi())
                .putExtra("gurultu",model.getGurultu()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout rl_redorgreen,rl_ofis_arkaplan;
        TextView tv_ofis_adi,tv_gurultu,tv_nem,tv_sicaklik;

        public ViewHolder(View itemView) {
            super(itemView);
            context=itemView.getContext();
            rl_redorgreen=itemView.findViewById(R.id.rl_sicaklik_belirle);
            rl_ofis_arkaplan=itemView.findViewById(R.id.rl_adapter_second_item_bg);
            tv_gurultu=itemView.findViewById(R.id.tv_adapter_sec_gurultu);
            tv_nem=itemView.findViewById(R.id.tv_adapter_sec_nem);
            tv_sicaklik=itemView.findViewById(R.id.tv_adapter_sec_sicaklik);
            tv_ofis_adi=itemView.findViewById(R.id.tv_adapter_sec_ofis_adi);
            //tv_title=itemView.findViewById(R.id.tv_adapter_item_title);
            //tv_descp=itemView.findViewById(R.id.tv_adapter_item_descp);

        }
    }
}
