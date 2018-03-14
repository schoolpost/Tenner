package cmput301w18t22.com.tenner;

import android.media.Image;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Schoolpost on 2018-02-26.
 */

public class User {

    /* - Email : str min(8)
    - First Name : str max(30)
    - Last Name : str max(30)
    - Phone : int max(10)
    - Photo : Photo (optional)
    */

    private String email;
    private String firstName;
    private String lastName;
    private String phoneNum;
    private Photo photo;
    private ArrayList<Task> requestedTasks;
    private ArrayList<Task> providedTasks;
    private ArrayList<Bid> bids;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public ArrayList<Task> getRequestedTasks() {
        return requestedTasks;
    }

    public void setRequestedTasks(ArrayList<Task> requestedTasks) {
        this.requestedTasks = requestedTasks;
    }

    public ArrayList<Task> getProvidedTasks() {
        return providedTasks;
    }

    public void setProvidedTasks(ArrayList<Task> providedTasks) {
        this.providedTasks = providedTasks;
    }

    public ArrayList<Bid> getBids() {
        return bids;
    }

    public void setBids(ArrayList<Bid> bids) {
        this.bids = bids;
    }

    public String toDisplayName() {
        String displayName = this.firstName + ' ' + this.lastName.substring(0,1) + ".";
        return displayName;
    }
}
