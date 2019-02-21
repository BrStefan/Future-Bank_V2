package com.example.brstefan.futurebank;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JSONPlaceHolderApi {

    String BASE_URL = "http://data.fixer.io/api/";
    @GET("latest?access_key=23384a50ae773eaea300fb5dba263fa1")
    Call<Exchange> getExchanges();

}
