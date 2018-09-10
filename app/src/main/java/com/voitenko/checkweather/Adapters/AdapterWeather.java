package com.voitenko.checkweather.Adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.voitenko.checkweather.Data.AllWeather;
import com.voitenko.checkweather.R;

import java.util.Calendar;
import java.util.List;

public class AdapterWeather extends RecyclerView.Adapter<AdapterWeather.HolderWeather> {

    List<AllWeather> items;

    public AdapterWeather(List<AllWeather> items) {
        this.items = items;
    }

    @Override
    public HolderWeather onCreateViewHolder(ViewGroup parent, int viewType) {
        AdapterWeather.HolderWeather holder = null;
        holder = new HolderWeather(LayoutInflater.from(parent.getContext()).inflate(R.layout.conteiner_weather, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(HolderWeather holder, int position) {
holder.city.setText(items.get(holder.getAdapterPosition()).getSysCountry()+" | "
                        + items.get(holder.getAdapterPosition()).getName());

holder.temp_main.setText(items.get(holder.getAdapterPosition()).getWeatherDescription()+" | "
                        + items.get(holder.getAdapterPosition()).getWeatherMain());

holder.temp_min.setText(items.get(holder.getAdapterPosition()).getMainTemp());
holder.temp_max.setText(items.get(holder.getAdapterPosition()).getTime());

    }

    @Override
    public int getItemCount() {
        if(items!=null){
        return items.size();}else{return 0;}
    }

    public class HolderWeather extends RecyclerView.ViewHolder{
        TextView city;
        TextView temp_main;
        TextView temp_min;
        TextView temp_max;

        public HolderWeather(View itemView) {
            super(itemView);
            city = itemView.findViewById(R.id.city);
            temp_main = itemView.findViewById(R.id.temp_main);
            temp_min = itemView.findViewById(R.id.temp_min);
            temp_max = itemView.findViewById(R.id.temp_max);
        }
    }
}
