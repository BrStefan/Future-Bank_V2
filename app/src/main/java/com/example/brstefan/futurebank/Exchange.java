package com.example.brstefan.futurebank;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Exchange {

    private String base;
    private List<String> rates;

    public Exchange(String base, List<String> rates) {
        this.base = base;
        this.rates = rates;
    }

    public String getBase() {
        return base;
    }

    public List<String> getRates() {
        return rates;
    }
}
