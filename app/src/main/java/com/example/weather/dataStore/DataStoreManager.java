package com.example.weather.dataStore;

import android.content.Context;
import androidx.datastore.core.DataStore;
import androidx.datastore.core.Serializer;
import androidx.datastore.preferences.core.MutablePreferences;
import androidx.datastore.preferences.core.Preferences;

import androidx.datastore.preferences.core.PreferencesKeys;
import androidx.datastore.preferences.rxjava3.RxPreferenceDataStoreBuilder;
import androidx.datastore.rxjava3.*;

import java.io.File;
import java.util.concurrent.Callable;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

public class DataStoreManager {

    private final RxDataStore<Preferences> dataStore;
    private static DataStoreManager instance;

    public DataStoreManager(Context context) {
        this.dataStore =
                new RxPreferenceDataStoreBuilder(context, "settings-weather").build();
    }

    public static synchronized DataStoreManager getInstance(Context context) {
        if (instance == null) {
            instance = new DataStoreManager(context.getApplicationContext());
        }
        return instance;
    }

    public Flowable<Integer> getPreferencesIntKey(String name) {
        Preferences.Key<Integer> varInt = PreferencesKeys.intKey(name);

        return dataStore.data().map(prefs -> prefs.get(varInt));
    }

    public Flowable<String> getPreferencesStringKey(String name) {
        Preferences.Key<String> varStr = PreferencesKeys.stringKey(name);

        return dataStore.data().map(prefs -> prefs.get(varStr));
    }

    public Flowable<Double> getPreferencesDoubleKey(String name) {
        Preferences.Key<Double> varDbl = PreferencesKeys.doubleKey(name);

        return dataStore.data().map(prefs -> prefs.get(varDbl));
    }

    public Flowable<Long> getPreferencesLongKey(String name) {
        Preferences.Key<Long> varLng = PreferencesKeys.longKey(name);

        return dataStore.data().map(prefs -> prefs.get(varLng));
    }





    public Single<Preferences> saveInt(String name, int myInt)
    {
        return this.dataStore.updateDataAsync(prefsIn -> {
            MutablePreferences mutablePreferences = prefsIn.toMutablePreferences();
            //String current = prefsIn.get(USERNAME_KEY);
            mutablePreferences.set(PreferencesKeys.intKey(name), myInt);
            return Single.just(mutablePreferences);
        });
    }

    public Single<Preferences> saveStr(String name, String myStr)
    {
        return this.dataStore.updateDataAsync(prefsIn -> {
            MutablePreferences mutablePreferences = prefsIn.toMutablePreferences();
            //String current = prefsIn.get(USERNAME_KEY);
            mutablePreferences.set(PreferencesKeys.stringKey(name), myStr);
            return Single.just(mutablePreferences);
        });
    }

    public Single<Preferences> saveLng(String name, Long myLng)
    {
        return this.dataStore.updateDataAsync(prefsIn -> {
            MutablePreferences mutablePreferences = prefsIn.toMutablePreferences();
            //String current = prefsIn.get(USERNAME_KEY);
            mutablePreferences.set(PreferencesKeys.longKey(name), myLng);
            return Single.just(mutablePreferences);
        });
    }

    public Single<Preferences> saveDbl(String name, Double myDbl)
    {
        return this.dataStore.updateDataAsync(prefsIn -> {
            MutablePreferences mutablePreferences = prefsIn.toMutablePreferences();
            //String current = prefsIn.get(USERNAME_KEY);
            mutablePreferences.set(PreferencesKeys.doubleKey(name), myDbl);
            return Single.just(mutablePreferences);
        });
    }
}