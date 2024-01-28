package com.example.weather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

        Button btn = findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText userInputEditText = findViewById(R.id.editTextText);
                String userInput = userInputEditText.getText().toString();
                onButtonClick(userInput);
            }
        });

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
                        String test = weatherResponse.getCurrent().getWind_dir();
                        int cloud = weatherResponse.getCurrent().getCloud();

                        Log.d("Weather", "Weather City: " + cityName + ", Cloud: " + cloud);

                        // Uncomment and complete the following code if you want to update the UI
                         TextView tvResult = findViewById(R.id.textViewWeather);
                         tvResult.setText(test);

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