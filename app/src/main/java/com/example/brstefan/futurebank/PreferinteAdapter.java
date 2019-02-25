package com.example.brstefan.futurebank;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PreferinteAdapter extends RecyclerView.Adapter<PreferinteAdapter.PreferinteHolder> {

    private List<Preferinte> preferinte = new ArrayList<>();

    @NonNull
    @Override
    public PreferinteHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.preferinte_item,viewGroup,false);
        return new PreferinteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PreferinteHolder preferinteHolder, int i) {
        Preferinte currentePreferinte = preferinte.get(i);
        preferinteHolder.textViewActivitate.setText(currentePreferinte.getActivitate());
        preferinteHolder.textViewCategorie.setText(currentePreferinte.getCategorie());
    }

    @Override
    public int getItemCount() {
        return preferinte.size();
    }

    public void setPreferinte(List<Preferinte> preferinte){
        this.preferinte = preferinte;
        notifyDataSetChanged();
    }

    class PreferinteHolder extends RecyclerView.ViewHolder{
        private TextView textViewCategorie;
        private TextView textViewActivitate;

        public PreferinteHolder(@NonNull View itemView) {
            super(itemView);
            textViewCategorie = itemView.findViewById(R.id.preferinte_1);
            textViewActivitate  =itemView.findViewById(R.id.preferinte_2);
        }
    }
}
