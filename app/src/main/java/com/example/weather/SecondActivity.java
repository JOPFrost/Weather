package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.weather.dataStore.DataStoreManager;

import io.reactivex.rxjava3.disposables.Disposable;

public class SecondActivity extends AppCompatActivity {

    private DataStoreManager dataStoreManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        findViewById(R.id.btnNavigateMain).setOnClickListener(view -> {
            Intent intent = new Intent(SecondActivity.this, MainActivity.class);
            startActivity(intent);
        });

        dataStoreManager = new DataStoreManager(this);

        TextView cloudHistoryResult = findViewById(R.id.textHistoryCloud);
        TextView cityHistoryResult = findViewById(R.id.textHistoryCity);

        Disposable cloudDisposable = dataStoreManager.getPreferencesIntKey("cloud")
                .subscribe(
                        value -> cloudHistoryResult.setText("cloud percentage: " + value + "%"),
                        throwable -> System.err.println("Error loading int: " + throwable)
                );

        Disposable cityDisposable = dataStoreManager.getPreferencesStringKey("cityName")
                .subscribe(
                        value -> cityHistoryResult.setText("city: " + value),
                        throwable -> System.err.println("Error loading int: " + throwable)
                );

        cityDisposable.dispose();
        cloudDisposable.dispose();
    }

    @Override
    protected void onStart() {
        super.onStart();
        setContentView(R.layout.activity_second);

        findViewById(R.id.btnNavigateMain).setOnClickListener(view -> {
            Intent intent = new Intent(SecondActivity.this, MainActivity.class);
            startActivity(intent);
        });

        TextView cloudHistoryResult = findViewById(R.id.textHistoryCloud);
        TextView cityHistoryResult = findViewById(R.id.textHistoryCity);

        Disposable cloudDisposable = dataStoreManager.getPreferencesIntKey("cloud")
                .subscribe(
                        value -> cloudHistoryResult.setText("cloud percentage: " + value + "%"),
                        throwable -> System.err.println("Error loading int: " + throwable)
                );

        Disposable cityDisposable = dataStoreManager.getPreferencesStringKey("cityName")
                .subscribe(
                        value -> cityHistoryResult.setText("city: " + value),
                        throwable -> System.err.println("Error loading int: " + throwable)
                );

        cityDisposable.dispose();
        cloudDisposable.dispose();
    }
}
