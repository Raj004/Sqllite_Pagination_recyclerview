package com.example.sqlite_recyclerview_pagination.retrofit_api;

import com.example.sqlite_recyclerview_pagination.model.DataModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("retrofit.json")
    Call<List<DataModel>> getBookDetail();
}
