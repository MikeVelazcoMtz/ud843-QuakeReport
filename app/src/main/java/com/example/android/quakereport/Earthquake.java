package com.example.android.quakereport;

/**
 * class for every Earthquake item
 */
class Earthquake {
    private String location;
    private Long timeInMiliseconds;
    private Double magnitude;
    private String url;

    /**
     * Instantiates a new Earthquake.
     *
     * @param location  the location of the Earthquake
     * @param magnitude the magnitude
     * @param date      the timeInMiliseconds
     * @param url       the URL from the event
     */
    public Earthquake(String location, Double magnitude, Long date, String url) {
        this.location = location;
        this.timeInMiliseconds = date;
        this.magnitude = magnitude;
        this.url = url;
    }

    /**
     * Gets the location.
     *
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Gets time in miliseconds.
     *
     * @return the time in miliseconds
     */
    public Long getTimeInMiliseconds() {
        return timeInMiliseconds;
    }

    /**
     * Gets the magnitude.
     *
     * @return the magnitude
     */
    public Double getMagnitude() {
        return magnitude;
    }

    /**
     * Gets url.
     *
     * @return the url
     */
    public String getUrl() {
        return url;
    }
}
