package com.luca.lucabrasi.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.luca.lucabrasi.Activity.Arbeit1Activity;
import com.luca.lucabrasi.Interfaces.OnDataResponceListner;
import com.luca.lucabrasi.Model.Carlistmodel;
import com.luca.lucabrasi.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FilterAdapter extends  RecyclerView.Adapter<FilterAdapter.ViewHolder>  {

    List<Carlistmodel.Datum> carlist = new ArrayList<>();
    Context context;
    OnDataResponceListner onDataResponceListner;

    public FilterAdapter(List<Carlistmodel.Datum> carlist, Context context, OnDataResponceListner onDataResponceListner) {
        this.carlist = carlist;
        this.context = context;
        this.onDataResponceListner = onDataResponceListner;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemlist, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.e("TAG", "onBindViewHolder: " );
        holder.tvname.setText(carlist.get(position).carNoplate);


            holder.tvname.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onDataResponceListner.OnClick(carlist.get(position).carNoplate,carlist.get(position).carId);
                }
            });

    }

    @Override
    public int getItemCount() {
        return carlist.size();
    }
    public void filterList( List<Carlistmodel.Datum> filteredList) {
        carlist = filteredList;
        notifyDataSetChanged();
    }
    public  static  class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textview)
        TextView tvname;


       public ViewHolder(@NonNull View itemView) {

           super(itemView);
           ButterKnife.bind(this, itemView);
       }
   }
}
