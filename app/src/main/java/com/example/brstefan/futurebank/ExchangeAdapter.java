package com.example.brstefan.futurebank;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ExchangeAdapter extends RecyclerView.Adapter<ExchangeAdapter.ExchangeViewHolder> {

    private ArrayList<ExchangeItem> mExchangeList;
    public static class ExchangeViewHolder extends RecyclerView.ViewHolder{

        public TextView mTextView1;
        public TextView mTextView2;
        public ExchangeViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView1 = itemView.findViewById(R.id.rw_moneda);
            mTextView2=itemView.findViewById(R.id.rw_valoare);
        }
    }

    public ExchangeAdapter(ArrayList<ExchangeItem> exchangeList){
        mExchangeList = exchangeList;
    }

    @NonNull
    @Override
    public ExchangeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.currency_row,viewGroup,false);
        ExchangeViewHolder evh = new ExchangeViewHolder(view);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExchangeViewHolder exchangeViewHolder, int i) {
        ExchangeItem currentItem = mExchangeList.get(i);

        exchangeViewHolder.mTextView1.setText(currentItem.getmText1());
        exchangeViewHolder.mTextView2.setText(currentItem.getmText2());
    }

    @Override
    public int getItemCount() {
        return mExchangeList.size();
    }
}
