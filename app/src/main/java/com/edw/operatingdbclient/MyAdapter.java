package com.edw.operatingdbclient;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import com.edw.operatingdbclient.databinding.ItemDatabaseBinding;
import com.edw.operatingdbremoteservice.City;

import java.util.ArrayList;
import java.util.List;

/**
 * **************************************************************************************************
 * Project Name:    OperatingDBClient
 * <p>
 * Date:            2021-05-24
 * <p>
 * Author：         EdwardWMD
 * <p>
 * Github:          https://github.com/Edwardwmd
 * <p>
 * Blog:            https://edwardwmd.github.io/
 * <p>
 * Description：    ToDo
 * <p>
 * **************************************************************************************************
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<City> cities = new ArrayList<>();
    private View view;
    private ItemDatabaseBinding vb;
    private ItemDatabaseBinding bv;

    public void setData(List<City> cities) {
        this.cities.addAll(cities);
        notifyDataSetChanged();
    }

    public void removeAll() {
        if (cities != null && cities.size() > 0) {
            cities.clear();
            Log.e("TAG","adddd----->");
        }
       notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_database, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        holder.tvCity.setText(cities.get(position).getCityName());
        holder.tvProvince.setText(cities.get(position).getCityProvince());
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvCity;
        public TextView tvProvince;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCity = itemView.findViewById(R.id.city_name);
            tvProvince = itemView.findViewById(R.id.province_name);
        }
    }
}
