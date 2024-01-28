package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.datastore.core.DataStore;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.weather.api.WeatherApi;
import com.example.weather.dataStore.DataStoreManager;
import com.example.weather.model.WeatherResponse;

import java.util.prefs.Preferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    private static final String BASE_URL = "https://api.weatherapi.com/v1/";
    private WeatherApi weatherApi;

    private DataStoreManager dataStoreManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        weatherApi = retrofit.create(WeatherApi.class);
        dataStoreManager = DataStoreManager.getInstance(this);

        // Navigate to SecondActivity



        Button btn = findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText userInputEditText = findViewById(R.id.editTextText);
                String userInput = userInputEditText.getText().toString();
                onButtonClick(userInput);
            }
        });

        findViewById(R.id.btnNavigateSecond).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        });

    }

    private void onButtonClick(String userInput) {
        //use DataStoreManager to save the user input


        // Call the API
        Call<WeatherResponse> call = weatherApi.getCurrent("b7e18683343f4da98a7100504242501", userInput);

        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.isSuccessful()) {
                    WeatherResponse weatherResponse = response.body();

                    if (weatherResponse != null) {
                        String cityName = weatherResponse.getLocation().getName();
                        dataStoreManager.saveStr("cityName", cityName);
                        
                        String windDir = weatherResponse.getCurrent().getWind_dir();
                        dataStoreManager.saveStr("windDir", windDir);
                        
                        int cloud = weatherResponse.getCurrent().getCloud();
                        dataStoreManager.saveInt("cloud", cloud);
                        
                        String condition = weatherResponse.getCurrent().getCondition().getText();
                        dataStoreManager.saveStr("condition", condition);
                        
                        Double degreesC = weatherResponse.getCurrent().getTemp_c();
                        dataStoreManager.saveDbl("degreesC", degreesC);
                        

                        // Uncomment and complete the following code if you want to update the UI
                        TextView tvCity = findViewById(R.id.textViewWeatherCity);
                        TextView tvWindDir = findViewById(R.id.textViewWeatherWindir);
                        TextView tvCloud = findViewById(R.id.textViewWeatherCloud);
                        TextView tvCondition = findViewById(R.id.textViewWeatherCondition);
                        TextView tvDegreesC = findViewById(R.id.textViewWeatherDegreeC);
                        tvCity.setText("City: " + cityName);
                        tvWindDir.setText("Wind direction: " + windDir);
                        tvCloud.setText("Cloud percentage: " + cloud  +"%");
                        tvCondition.setText("Condition: " + condition);
                        tvDegreesC.setText("Temperature: " + degreesC + "°C");
                        



                    } else {
                        Log.e("Weather", "Error: " + response.code());
                        TextView tvResult = findViewById(R.id.textViewWeatherCity);
                        tvResult.setText("Not found");
                    }
                }
            }
            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                Log.e("Weather", "Failure: " + t.getMessage());
            }
        });
    }

}