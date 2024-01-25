package com.example.weather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.weather.api.WeatherApi;
import com.example.weather.model.WeatherResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    private static final String BASE_URL = "https://api.weatherapi.com/v1/";
    private WeatherApi weatherApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        weatherApi = retrofit.create(WeatherApi.class);

        onButtonClick("Brno");
    }

    private void onButtonClick(String userInput) {
        // Call the API
        Call<WeatherResponse> call = weatherApi.getCurrent("b7e18683343f4da98a7100504242501", userInput);

        // Log the request URL for debugging
        Log.d("Weather", "Request URL: " + call.request().url().toString());

        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.isSuccessful()) {
                    WeatherResponse weatherResponse = response.body();

                    if (weatherResponse != null) {
                        String cityName = weatherResponse.getLocation().getName();
                        int cloud = weatherResponse.getCurrent().getCloud();

                        Log.d("Weather", "Weather City: " + cityName + ", Cloud: " + cloud);

                        // Uncomment and complete the following code if you want to update the UI
                        // TextView tvResult = findViewById(R.id.result);
                        // tvResult.setText("City: " + cityName + ", Cloud: " + cloud);
                    }
                } else {
                    Log.e("Weather", "Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                Log.e("Weather", "Failure: " + t.getMessage());
            }
        });
    }
}