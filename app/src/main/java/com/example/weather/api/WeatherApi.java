package com.example.weather.api;

import com.example.weather.model.WeatherResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {
    @GET("current.json")
    Call<WeatherResponse> getCurrent(@Query("key") String key,
                                        @Query("q") String q);
}
