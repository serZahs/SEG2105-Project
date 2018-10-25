/**
 * ServiceProvider.java is a child of the user class. It contains functions and attributes specific
 * to a service provider along with inherited attributes and functions from user.
 *
 * @version 1.0
 */

package com.project.seg.homeservices;

public class ServiceProvider extends User {

    private int numberOfRatings; // starts with 0 ratings
    private int sumRating; // sum of rating home owner has gotten (starts at 0)
    private boolean available; // state of avaiability of service provider

    /**
     * constructor for service provider class. (no availability specified)
     *
     * @param email email input field
     * @param username username input field
     * @param password password input field
     */
    public ServiceProvider(String email, String username, String password) {
        super(email, username, password);

        sumRating = 0;
        numberOfRatings = 0;
        available = true; // by default, a service provider is available
    }

    /**
     * constructor for service provider class with availability input.
     *
     * @param email email input field
     * @param username username input field
     * @param password password input field
     * @param available availability of service provider
     */
    public ServiceProvider(String email, String username, String password,
                           boolean available) {
        super(email, username, password);

        sumRating = 0;
        numberOfRatings = 0;
        this.available = available;
    }

    /**
     * switches the availability of the service provider and returns the new state of
     * availability.
     *
     * @return boolean new state of availability
     */
    public boolean changeAvailability() {
        available = !available;

        return available;
    }

    /**
     * returns the availability of the service provider
     *
     * @return boolean whether or not the service provider is available
     */
    public boolean getAvailability() {
        return available;
    }

    /**
     * Adds a rating to the overall rating of a home owner. Can only be
     * an integer value for the time being.
     *
     * @param ratingGiven rating that a service provider is giving a home owner
     * @return double overall rating after adding the rating
     */
    public double giveRating(int ratingGiven) {
        sumRating += ratingGiven;

        return sumRating / numberOfRatings++;
    }

    /**
     * Returns then current rating of the home owner.
     *
     * @return double overall rating of the owner
     */
    public double returnRating() {
        return sumRating / numberOfRatings;
    }
}
