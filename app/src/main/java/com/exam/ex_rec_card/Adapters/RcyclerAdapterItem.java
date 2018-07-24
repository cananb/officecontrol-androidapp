package com.exam.ex_rec_card.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.exam.ex_rec_card.Activities.Step2Activity;
import com.exam.ex_rec_card.Model.RecylerItemModel;
import com.exam.ex_rec_card.R;

import java.util.List;

/**
 * Created by Ahmet on 24.4.2018.
 */

public class RcyclerAdapterItem extends RecyclerView.Adapter<RcyclerAdapterItem.ViewHolder>{
    List<RecylerItemModel> list;
    Context context;
    public RcyclerAdapterItem(List<RecylerItemModel> list){
        this.list=list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_first_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        //holder.tv_descp.setText("Deneme");
        //holder.tv_title.setText("Deneme");
        holder.iv_icon.setImageResource(list.get(position).getIcon());
        holder.iv_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, Step2Activity.class)
                .putExtra("kim",list.get(position).getTitle()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_icon;
        //TextView tv_title,tv_descp;

        public ViewHolder(View itemView) {
            super(itemView);
           iv_icon=itemView.findViewById(R.id.iv_adapter_first_icon);
           context=itemView.getContext();
          //tv_title=itemView.findViewById(R.id.tv_adapter_item_title);
           //tv_descp=itemView.findViewById(R.id.tv_adapter_item_descp);
        }
    }
}
