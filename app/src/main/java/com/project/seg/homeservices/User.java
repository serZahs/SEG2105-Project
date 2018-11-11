/**
 * User.java is an abstract class containing attributes and functions that all users will have
 * access to.
 *
 * @version 1.0
 */

package com.project.seg.homeservices;

public class User {
    private String email;
    private String username;
    private String password;

    /**
     * constructor for abstract user class. email and username have already
     * been verified at the point of this objects creation.
     *
     * @param email email input field
     * @param username username input field
     * @param password password input field
     */
    public User(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    /**
     * setter for email
     *
     * @param input input email
     */
    public void setEmail(String input) {
        email = input;
    }

    /**
     * setter for username
     *
     * @param input input username
     */
    public void setUsername(String input) {
        username = input;
    }

    /**
     * setter for password
     *
     * @param input input password
     */
    public void setPassword(String input) {
        password = input;
    }

    /**
     * getter for email
     *
     * @return String email
     */
    public String getEmail() {
        return email;
    }

    /**
     * getter for username
     *
     * @return String username
     */
    public String getUsername() {
        return username;
    }

    /**
     * getter for password
     *
     * @return String password
     */
    public String getPassword() {
        return password;
    }
}
