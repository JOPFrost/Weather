package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.weather.dataStore.DataStoreManager;

import io.reactivex.rxjava3.disposables.Disposable;

public class SecondActivity extends AppCompatActivity {

    private DataStoreManager dataStoreManager;
    private Disposable cloudDisposable;
    private Disposable cityDisposable;
    private Disposable windDirDisposable;
    private Disposable degreesCelsDisposable;
    private Disposable conditionDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        findViewById(R.id.btnNavigateMain).setOnClickListener(view -> {
            Intent intent = new Intent(SecondActivity.this, MainActivity.class);
            startActivity(intent);
        });

        dataStoreManager = DataStoreManager.getInstance(this);

        TextView cloudHistoryResult = findViewById(R.id.textHistoryCloud);
        TextView cityHistoryResult = findViewById(R.id.textHistoryCity);
        TextView windDirHistoryResult = findViewById(R.id.textHistoryWindDir);
        TextView degreesCelsHistoryResult = findViewById(R.id.textHistoryDegreesC);
        TextView conditionHistoryResult = findViewById(R.id.textHistoryCondition);


        cloudDisposable = dataStoreManager.getPreferencesIntKey("cloud")
                .subscribe(
                        value -> {
                            System.out.println("Cloud value: " + value);
                            cloudHistoryResult.setText("cloud percentage: " + value + "%");
                        },
                        throwable -> System.err.println("Error loading int: " + throwable)
                );

        cityDisposable = dataStoreManager.getPreferencesStringKey("cityName")
                .subscribe(
                        value -> {
                            System.out.println("City value: " + value);
                            cityHistoryResult.setText("City: " + value);
                        },
                        throwable -> System.err.println("Error loading int: " + throwable)
                );
        windDirDisposable = dataStoreManager.getPreferencesStringKey("windDir")
                .subscribe(
                        value -> {
                            System.out.println("Wind direction value: " + value);
                            windDirHistoryResult.setText("Wind direction: " + value);
                        },
                        throwable -> System.err.println("Error loading int: " + throwable)
                );
        degreesCelsDisposable = dataStoreManager.getPreferencesDoubleKey("degreesC")
                .subscribe(
                        value -> {
                            System.out.println("Temperature value: " + value + "°C");
                            degreesCelsHistoryResult.setText("Temperature: " + value + "°C");
                        },
                        throwable -> System.err.println("Error loading int: " + throwable)
                );
        conditionDisposable = dataStoreManager.getPreferencesStringKey("condition")
                .subscribe(
                        value -> {
                            System.out.println("Condition value: " + value);
                            conditionHistoryResult.setText("Condition: " + value);
                        },
                        throwable -> System.err.println("Error loading int: " + throwable)
                );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Dispose subscriptions to avoid memory leaks
        if (cloudDisposable != null && !cloudDisposable.isDisposed()) {
            cloudDisposable.dispose();
        }
        if (cityDisposable != null && !cityDisposable.isDisposed()) {
            cityDisposable.dispose();
        }
        if (windDirDisposable != null && !windDirDisposable.isDisposed()) {
            windDirDisposable.dispose();
        }
        if (degreesCelsDisposable != null && !degreesCelsDisposable.isDisposed()) {
            degreesCelsDisposable.dispose();
        }
        if (conditionDisposable != null && !conditionDisposable.isDisposed()) {
            conditionDisposable.dispose();
        }
    }
}
