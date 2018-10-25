/**
 * HomeOwner.java is a child of the user class. It contains functions and attributes specific to
 * a home owner user along with inherited attributes and functions from user.
 *
 * @version 1.0
 */

package com.project.seg.homeservices;

import java.util.ArrayList;

public class HomeOwner extends User {

    private ArrayList<ServiceProvider> serviceProvidersBooked;

    /**
     * constructor for home owner class.
     *
     * @param email email input field
     * @param username username input field
     * @param password password input field
     */
    public HomeOwner(String email, String username, String password) {
        super(email, username, password);

        serviceProvidersBooked = new ArrayList<ServiceProvider>();
    }

    // specific home owner functions to be determined

    public boolean bookServiceProvider(ServiceProvider provider) {
        if (provider.getAvailability()) {
            serviceProvidersBooked.add(provider);
            provider.changeAvailability();
            return true;
        }

        return false;
    }

    public boolean removeServiceProvider(ServiceProvider provider) {
        // implement later
        return false;
    }
}
