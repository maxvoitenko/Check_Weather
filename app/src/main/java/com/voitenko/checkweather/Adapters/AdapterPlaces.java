package com.voitenko.checkweather.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.voitenko.checkweather.Data.Marker;
import com.voitenko.checkweather.Interface.OnPositionClickListener;
import com.voitenko.checkweather.R;

import java.util.List;

public class AdapterPlaces extends RecyclerView.Adapter<AdapterPlaces.HolderPlace> {

    private List<Marker> places;
    private Context context;
    private OnPositionClickListener onPositionClickListener;

    public void setOnPositionClickListener(OnPositionClickListener onPositionClickListener) {
        this.onPositionClickListener = onPositionClickListener;
    }

    public AdapterPlaces(List<Marker> places, Context context) {
        this.places = places;
        this.context= context;
    }

    @Override
    public AdapterPlaces.HolderPlace onCreateViewHolder(ViewGroup parent, int viewType) {
        AdapterPlaces.HolderPlace holder = null;
        holder = new HolderPlace(LayoutInflater.from(parent.getContext()).inflate(R.layout.conteiner_places, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(HolderPlace holder, int position) {
        Marker marker = places.get(holder.getAdapterPosition());
        holder.latText.setText(marker.getLatitude() + "|" + marker.getLongitude());
        holder.lotText.setText(marker.getCityInMap());
    }

    @Override
    public int getItemCount() {
        if(places!=null){
            return places.size();}else return 0;
    }

    public class HolderPlace extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView latText;
        public TextView lotText;

        public HolderPlace(View itemView) {
            super(itemView);
            latText = itemView.findViewById(R.id.latText);
            lotText = itemView.findViewById(R.id.lotText);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(onPositionClickListener!=null){
                onPositionClickListener.onPositionClickListener(places.get(getAdapterPosition()).getId());
            }
        }
    }
}
