package com.example.brstefan.futurebank;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.circularreveal.CircularRevealWidget;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ExchangeFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<ExchangeItem> currency = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_exchange,container,false);
    }


    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(JSONPlaceHolderApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JSONPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JSONPlaceHolderApi.class);

        jsonPlaceHolderApi.getExchanges().enqueue(new Callback<ExchangeCurrency>() {
            @Override
            public void onResponse(Call<ExchangeCurrency> call, Response<ExchangeCurrency> response) {
                //Log.e("TAG",response.body().getRates().getAUD()+"");
                currency.add(new ExchangeItem("Dolar Australian",response.body().getRates().getAUD()+""));
                currency.add(new ExchangeItem("Dolar Canadian",response.body().getRates().getCAD()+""));
                currency.add(new ExchangeItem("Franc Elvetian",response.body().getRates().getCHF()+""));
                currency.add(new ExchangeItem("Peso Chilian",response.body().getRates().getCLF()+""));
                currency.add(new ExchangeItem("Coroana Ceha",response.body().getRates().getCZK()+""));
                currency.add(new ExchangeItem("Coroana Daneza",response.body().getRates().getDKK()+""));
                currency.add(new ExchangeItem("Dinar Algerian",response.body().getRates().getDZD()+""));
                currency.add(new ExchangeItem("Lira Egipteana",response.body().getRates().getEGP()+""));
                currency.add(new ExchangeItem("Leu Romanesc",response.body().getRates().getRON()+""));
                currency.add(new ExchangeItem("Lira Sterlina",response.body().getRates().getGBP()+""));
                currency.add(new ExchangeItem("Yen",response.body().getRates().getJPY()+""));
                currency.add(new ExchangeItem("Dolar american",response.body().getRates().getUSD()+""));


                mRecyclerView = view.findViewById(R.id.rw_exchange);
                mLayoutManager = new LinearLayoutManager(getContext());
                mAdapter = new ExchangeAdapter(currency);

                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<ExchangeCurrency> call, Throwable t) {
                //textViewResult.setText(t.getMessage());
            }
        });

    }
}
