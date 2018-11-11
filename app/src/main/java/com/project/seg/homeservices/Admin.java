/**
 * Admin.java is a child of the user class. It contains functions and attributes specific to
 * an admin user along with inherited attributes and functions from user.
 *
 * @version 1.0
 */

package com.project.seg.homeservices;

import java.util.ArrayList;
import java.util.Iterator;

public class Admin extends User {

    // specific attributes of admin to be decided.

    // static values that the service offered can have
    public static final String[] SERVICES_OFFERED = new String[]{"Install Appliance", "Carpet Cleaning",
            "Moving", "Plumbing", "Appliance Repair",
            "Furniture Assembly", "Locksmith", "Painting",
            "Window Cleaning", "Electrical Mould Remediation",
            "Pest Control", "Junk Removal", "Handyman Services"};
    private ArrayList<Service> listOfServices;
    /**
     * constructor for admin class.
     *
     * @param email email input field
     * @param username username input field
     * @param password password input field
     */
    public Admin(String email, String username, String password) {
        super(email, username, password);

        listOfServices = new ArrayList<Service>();
    }

    // specific admin functions to be decided

    /**
     * Function that adds service to the admin's arrayList. Does not check
     * for validity (needs to be implemented).
     *
     * @param service input service name
     * @param rate input rate per hour pay
     * @return boolean whether or not the addition was successful and valid
     */
    public boolean addService(String service, double rate) {
        listOfServices.add(new Service(service, rate));

        return true;
    }

    /**
     * Function that removes a service from the admin's arrayList. Simply checks
     * if the service is valid in the arrayList. (need implementation in database)
     *
     * @param service input service object
     * @return boolean whether or not the service was removed
     */
    public boolean removeService(Service service) {
        if (listOfServices.contains(service)) {
            listOfServices.remove(listOfServices.indexOf(service));
            return true;
        }

        return false;
    }

    /**
     * Function that changes the rate of pay per hour of a service
     * (need implementation in database)
     *
     * @param service input service object
     * @param rate boolean whether or not the rate was changed
     * @return
     */
    public boolean changeRateOfService(Service service, double rate) {
        if (listOfServices.contains(service)) {
            listOfServices.set(listOfServices.indexOf(service), new Service(service.getService(),
                    rate));
            return true;
        }

        return false;
    }

    /**
     * Checks whether or not the input service is one of the services set as available by
     * the admin and returns whether or not it is.
     *
     * @param service service being checked
     * @return boolean whether or not the service is available
     */
    public boolean isAvailableService(String service) {
        Iterator<Service> serviceIterator = listOfServices.iterator();

        while (serviceIterator.hasNext())
            if (serviceIterator.next().equals(service))
                return true;

        return false;
    }
}
