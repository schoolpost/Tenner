package cmput301w18t22.com.tenner;

import android.media.Image;
import java.util.ArrayList;

/**
 * Created by Schoolpost on 2018-02-26.
 */

/**
 * The User class represents a task requester or provider with email, name, phone number, photo,
 * a list of requested and provided tasks, and bids.
 *
 * @author Team 22
 * @version 1.1
 * @see User
 */
public class User {

    private String email;
    private String firstName;
    private String lastName;
    private String phoneNum;
    private Photo photo;
    private ArrayList<Task> requestedTasks;
    private ArrayList<Task> providedTasks;
    private ArrayList<Bid> bids;

    public User(String email, String firstName, String lastName, String phoneNum) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNum = phoneNum;
        //TODO implement photo
        //this.photo = photo;
        this.requestedTasks = new ArrayList<Task>();
        this.providedTasks = new ArrayList<Task>();
        this.bids = new ArrayList<Bid>();
    }

    /**
     * @return this User's email
     * @see User # setEmail
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the email of this User
     * @param email, new String value for this User
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return this User's first name
     * @see User # setFirstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set the first name of this User
     * @param firstName, new String value for this User
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return this User's last name
     * @see User # setLastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set the latitude of this User
     * @param lastName, new String value for this User
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return this User's phone number
     * @see User # setPhoneNum
     */
    public String getPhoneNum() {
        return phoneNum;
    }

    /**
     * Set the latitude of this User
     * @param phoneNum, new String value for this User
     */
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    /**
     * @return this User's photo
     * @see User # setPhoto
     */
    public Photo getPhoto() {
        return photo;
    }

    /**
     * Set the latitude of this User
     * @param photo, new Photo value for this User
     */
    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    /**
     * @return this User's requested tasks
     * @see User # setRequestedTasks
     */
    public ArrayList<Task> getRequestedTasks() {
        return requestedTasks;
    }

    /**
     * Set the latitude of this User
     * @param requestedTasks, new ArrayList<Task> value for this User
     */
    public void setRequestedTasks(ArrayList<Task> requestedTasks) {
        this.requestedTasks = requestedTasks;
    }

    /**
     * @return this User's latitude
     * @see User # setProvidedTasks
     */
    public ArrayList<Task> getProvidedTasks() {
        return providedTasks;
    }

    /**
     * Set the latitude of this User
     * @param providedTasks, new ArrayList<Task> value for this User
     */
    public void setProvidedTasks(ArrayList<Task> providedTasks) {
        this.providedTasks = providedTasks;
    }

    /**
     * @return this Users's bids
     * @see User # setBids
     */
    public ArrayList<Bid> getBids() {
        return bids;
    }

    /**
     * Set the latitude of this User
     * @param bids, new ArrayList<Bid> value for this User
     */
    public void setBids(ArrayList<Bid> bids) {
        this.bids = bids;
    }

    public String toDisplayName() {
        String displayName = this.firstName + ' ' + this.lastName.substring(0,1) + ".";
        return displayName;
    }
}