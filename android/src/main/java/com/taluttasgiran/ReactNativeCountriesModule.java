package com.taluttasgiran;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeArray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ReactNativeCountriesModule extends ReactContextBaseJavaModule {

    private boolean listsLoaded = false;
    private ArrayList<String> _cachedCountryCodes = new ArrayList<String>();
    private ArrayList<String> _cachedCountryNames = new ArrayList<String>();
    private ArrayList<Map<String, String>> _cachedCountryNamesWithCodes = new ArrayList<Map<String, String>>();

    ReactNativeCountriesModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    private void loadCountryLists() {
        if (listsLoaded)
            return;

        Locale[] locales = Locale.getAvailableLocales();
        _cachedCountryCodes = new ArrayList<String>();
        _cachedCountryNames = new ArrayList<String>();
        _cachedCountryNamesWithCodes = new ArrayList<Map<String, String>>();
        for (Locale locale : locales) {
            String countryName = locale.getDisplayCountry();
            String countryIso = locale.getCountry();
            if (countryIso.trim().length() > 0 && countryName.trim().length() > 0 && !_cachedCountryCodes.contains(countryIso) && !_cachedCountryNames.contains(countryName)) {
                _cachedCountryCodes.add(countryIso);
                _cachedCountryNames.add(countryName);

                Map<String, String> map = new HashMap();
                map.put("code", countryIso);
                map.put("name", countryName);
                _cachedCountryNamesWithCodes.add(map);
            }
        }

        listsLoaded = true;
    }


    @ReactMethod
    public void getCountryCodes(Promise promise) {
        loadCountryLists();

        WritableNativeArray countryCodeListNative = Arguments.makeNativeArray(_cachedCountryCodes);
        promise.resolve(countryCodeListNative);
    }

    @ReactMethod
    public void getCountryNames(Promise promise) {
        loadCountryLists();

        WritableNativeArray countryNameListNative = Arguments.makeNativeArray(_cachedCountryNames);
        promise.resolve(countryNameListNative);
    }

    @ReactMethod
    public void getCountryNamesWithCodes(Promise promise) {
        loadCountryLists();

        WritableNativeArray countryNameWithCodeNative = Arguments.makeNativeArray(_cachedCountryNamesWithCodes);
        promise.resolve(countryNameWithCodeNative);
    }


    @Override
    public String getName() {
        return "ReactNativeCountries";
    }
}