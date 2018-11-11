/**
 * Service.java is an object representing a service that can be provided by a service provider.
 * The specific service is created by the administrator who defines the service and it's rate per
 * hour. note: service input and rate input will need to be checked to see if they are valid before
 * a service is made.
 *
 * @version 1.0
 */

package com.project.seg.homeservices;

public class Service {

    private String serviceOffered;
    private double ratePerHour;

    /**
     * Constructor for Service class
     *
     * @param service service being created
     * @param rate rate of pay for hour for the service
     */
    public Service(String service, double rate) {
        serviceOffered = service;
        ratePerHour = rate;
    }

    /**
     * setter for service
     *
     * @param service name of service offered
     */
    public void setService(String service) {
        serviceOffered = service;
    }

    /**
     * setter for rate
     *
     * @param rate rate per hour of pay
     */
    public void setRate(double rate) {
        ratePerHour = rate;
    }

    /**
     * getter for service
     *
     * @return String name of service offered
     */
    public String getService() {
        return serviceOffered;
    }

    /**
     * getter for rate
     *
     * @return double rate of pay per hour for service
     */
    public double getRate() {
        return ratePerHour;
    }
}
