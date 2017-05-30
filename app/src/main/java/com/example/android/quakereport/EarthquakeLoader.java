package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.List;

/**
 * Created by Android on 20/05/2017.
 */
class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {

    private static final String USGS_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query";

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
        Context ctx = getContext();
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(ctx);

        String minMagnitude = sharedPrefs.getString(
                ctx.getString(R.string.settings_min_magnitude_key),
                ctx.getString(R.string.settings_min_magnitude_default));

        String orderBy = sharedPrefs.getString(
                ctx.getString(R.string.settings_order_by_key),
                ctx.getString(R.string.settings_order_by_default)
        );

        Uri baseUri = Uri.parse(USGS_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();

        uriBuilder.appendQueryParameter("format", "geojson");
        uriBuilder.appendQueryParameter("limit", "10");
        uriBuilder.appendQueryParameter("minmag", minMagnitude);
        uriBuilder.appendQueryParameter("orderby", orderBy);

        Log.i(LOG_TAG, "loadInBackground executed!");
        return QueryUtils.fetchEarthquakeData(uriBuilder.toString());
    }
}
