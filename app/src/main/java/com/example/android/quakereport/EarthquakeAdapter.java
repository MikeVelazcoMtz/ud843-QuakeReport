package com.example.android.quakereport;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * ArrayAdapter for Earthquake list
 */
class EarthquakeAdapter extends ArrayAdapter {


    /**
     * Instantiates a new Earthquake adapter.
     *
     * @param context the context
     * @param items   the items
     */
    public EarthquakeAdapter(@NonNull Context context, ArrayList<Earthquake> items) {
        super(context, 0, items);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Earthquake item = (Earthquake) getItem(position);

        // Inflate the view
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_item, parent, false);
        }

        // Set Magnitude
        TextView magnitude = (TextView) convertView.findViewById(R.id.magnitude);

        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        magnitude.setText(decimalFormat.format(item.getMagnitude()));

        GradientDrawable magnitudeCircle = (GradientDrawable) magnitude.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(item.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        // Set Location and aproximation
        String locationString = item.getLocation();
        int locationSeparator = locationString.indexOf("of");

        String location;
        String locationOffset;

        if (locationSeparator != -1) {
            locationOffset = locationString.substring(0, locationSeparator + 2);
            location = locationString.substring(3 + locationSeparator, locationString.length());
        } else {
            locationOffset = getContext().getString(R.string.near_the);
            location = locationString;
        }

        TextView locationView = (TextView) convertView.findViewById(R.id.primary_location);
        locationView.setText(location);

        TextView offsetView = (TextView) convertView.findViewById(R.id.location_offset);
        offsetView.setText(locationOffset);

        // Set date and time

        Date dateObject = new Date(item.getTimeInMiliseconds());

        TextView dateView = (TextView) convertView.findViewById(R.id.date);
        dateView.setText(formatDate(dateObject));

        TextView timeView = (TextView) convertView.findViewById(R.id.time);
        timeView.setText(formatTime(dateObject));

        final String urlForListener = item.getUrl();


        // Add the click listener

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(urlForListener));
                getContext().startActivity(i);
            }
        });

        return convertView;
    }


    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
}
