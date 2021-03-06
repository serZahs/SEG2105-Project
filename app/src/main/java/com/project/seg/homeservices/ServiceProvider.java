/**
 * ServiceProvider.java is a child of the user class. It contains functions and attributes specific
 * to a service provider along with inherited attributes and functions from user.
 *
 * @version 1.0
 */

package com.project.seg.homeservices;

import java.util.ArrayList;
import java.util.Iterator;

public class ServiceProvider extends User {

    private int numberOfRatings; // starts with 0 ratings
    private int sumRating; // sum of rating home owner has gotten (starts at 0)
    private boolean available; // state of avaiability of service provider
    private ArrayList<String> servicesProvided; // list of services provided
    private boolean serviceLicense; //whether or not the service is licensed
    private Admin admin;


    private String address;
    private String phoneNumber;
    private String companyName;
    private String mondayTime;
    private String tuesdayTime;
    private String wednesdayTime;
    private String thursdayTime;
    private String fridayTime;
    private String saturdayTime;
    private String sundayTime;
    private ArrayList<String> availabilityTime;

    /**
     * constructor for service provider class. (no availability specified)
     *  @param email email input field
     * @param username username input field
     * @param admin admin of this service provider
     */
    public ServiceProvider(String email, String username,String password, Admin admin) {
        super(email, username, password);

        sumRating = 0;
        numberOfRatings = 0;
        available = true; // by default, a service provider is available
        this.admin = admin;
        servicesProvided = admin.getListOfServicesInArrayList();

    }

    /**
     * constructor for service provider class with availability input.
     *
     * @param email email input field
     * @param username username input field
     * @param password password input field
     * @param available availability of service provider
     * @param admin admin if this service provider
     */
    public ServiceProvider(String email, String username, String password,
                           boolean available, Admin admin) {
        super(email, username, password);

        sumRating = 0;
        numberOfRatings = 0;
        this.available = available;
        this.admin = admin;
        servicesProvided = admin.getListOfServicesInArrayList();
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

    /**
     * Attempts to add a service to the services provided by this user. If the
     * service exists, it is added and true is returned, otherwise false is returned and
     * it is not added.
     *
     * @param service service being added
     * @return boolean whether or not the service was added.
     */
    public boolean editServiceRate(String service, double rate) {
        if (!admin.isAvailableService(service))
            return false;
        Iterator<Service> serviceIterator = admin.getListOfServices().iterator();

        while (serviceIterator.hasNext()) {
            if (serviceIterator.next().getService().equals(service)) {
               serviceIterator.next().setRate(rate);
            }
        }

        return true;
    }


    /**
     * Attempts to remove the input service from this service provider. If the service is present
     * the service is removed and true is returned. Otherwise false is returned.
     *
     * @param service service to be removed.
     * @return boolean whether or not the service was removed.
     */
    public boolean removeService(String service) {
        Iterator<String> serviceIterator = servicesProvided.iterator();

        while (serviceIterator.hasNext()) {
            if (serviceIterator.next().equals(service)) {
                servicesProvided.remove(serviceIterator.next());
            }
        }

        return false;
    }

    public boolean changeHourlyRate(String service,Double rate){

        return true;
    }


    public boolean addAvailabilityTime(
    	String mondayTime, String tuesdayTime, String wednesdayTime, String thursdayTime, 
    	String fridayTime, String saturdayTime, String sundayTime) {

    	this.mondayTime = mondayTime;
    	this.tuesdayTime = tuesdayTime;
    	this.wednesdayTime = wednesdayTime;
    	this.thursdayTime = thursdayTime;
    	this.fridayTime = fridayTime;
    	this.saturdayTime = saturdayTime;
    	this.sundayTime = sundayTime;

    	return true;

    }



    /**
     * Returns an arraylist of the services this service provider provides
     *
     * @return ArrayList<String> an list of services provided
     */
    public ArrayList<String > getServices() { return servicesProvided;}

    /**
     * Methods getters and setters for Service Provider
     *
     *
     */

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public Admin getAdmin(){return admin;}

    public boolean Licensed() { return serviceLicense;}

    public void setLicensed(boolean Licensed) {this.serviceLicense = Licensed;}
}
