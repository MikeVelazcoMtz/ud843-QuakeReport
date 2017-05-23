package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

/**
 * Created by Android on 20/05/2017.
 */
class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {

    private static final String USGS_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";

    private static final String LOG_TAG = EarthquakeLoader.class.getName();

    EarthquakeLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        Log.i(LOG_TAG, "onStartLoading executed!");
        forceLoad();
        super.onStartLoading();
    }

    @Override
    public List<Earthquake> loadInBackground() {
        Log.i(LOG_TAG, "loadInBackground executed!");
        return QueryUtils.fetchEarthquakeData(USGS_URL);
    }
}
