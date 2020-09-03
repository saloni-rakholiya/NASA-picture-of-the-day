package com.example.nasa;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface placeholderapi {

  //  @GET("apod")
    @GET("apod")
    Call<List<Photo>> getphotos(@Query("api_key") String key,
                                @Query("start_date") String date);
}
