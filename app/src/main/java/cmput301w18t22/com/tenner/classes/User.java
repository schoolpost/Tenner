package cmput301w18t22.com.tenner.classes;

import java.util.ArrayList;

/**
 * The User class represents a Tenner user, who could be either a requester, or provider, or both.
 * It contains their first and last name, contact info (phone and email), and keeps track of
 * the user's requested tasks, provided tasks, and bids.
 *
 * @author Team 22
 * @version 1.1
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

    public User(String email, String firstName, String lastName, String phoneNum) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNum = phoneNum;
        //this.photo = photo;
        this.requestedTasks = new ArrayList<Task>();
        this.providedTasks = new ArrayList<Task>();
        this.bids = new ArrayList<Bid>();
    }

    public User(String email, String firstName, String lastName, String phoneNum, ArrayList<Task> requestedTasks, ArrayList<Task> providedTasks, ArrayList<Bid> bids) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNum = phoneNum;
        //this.photo = photo;
        this.requestedTasks = requestedTasks;
        this.providedTasks = providedTasks;
        this.bids = bids;
    }

    /**
     * @return this user's email
     * @see User#setEmail
     */
    public String getEmail() {
        return email;
    }


    /**
     * Set the email for this user. Doubles as their username. Might check if proper format
     * (ie. contains '@', .') at later date.
     *
     * @param email String representing user's email
     */
    public void setEmail(String email) {
        this.email = email;
    }


    /**
     * @return this user's first name
     * @see User#setFirstName
     */
    public String getFirstName() {
        return firstName;
    }


    /**
     * Set the first name for this user. Will ensure it is no longer than 30 characters.
     *
     * @param firstName String representing user's first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    /**
     * @return this user's last name
     * @see User#setLastName
     */
    public String getLastName() {
        return lastName;
    }


    /**
     * Set the last name for this user. Will ensure it is no longer than 30 characters.
     *
     * @param lastName String representing user's last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    /**
     * @return this user's phone number
     * @see User#setPhoneNum
     */
    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }


    /**
     * @return this user's photo
     * @see User#setPhoto
     */
    public Photo getPhoto() {
        return photo;
    }


    /**
     * Set the photo for this user. Must be less than 65536 bytes.
     *
     * @param photo Photo object to be set for this user
     */
    public void setPhoto(Photo photo) {
        this.photo = photo;
    }


    /**
     * @return ArrayList of tasks where this user is the requester
     * @see User#setRequestedTasks
     * @see User#addRequestedTask
     */
    public ArrayList<Task> getRequestedTasks() {
        return requestedTasks;
    }


    /**
     * Set the ArrayList of requested tasks for this user. That is, tasks for which this user is
     * the requester.
     *
     * @param requestedTasks ArrayList of tasks for which this user is the requester
     */
    public void setRequestedTasks(ArrayList<Task> requestedTasks) {
        this.requestedTasks = requestedTasks;
    }


    /**
     * Add a new task for which this user is the requester to this user's requested task list.
     *
     * @param newTask Task to be added to requested tasks list
     */
    public void addRequestedTask(Task newTask) {
        this.requestedTasks.add(newTask);
    }


    /**
     * @return ArrayList of tasks where this user is the provider
     * @see User#setProvidedTasks
     * @see User#addProvidedTask
     */
    public ArrayList<Task> getProvidedTasks() {
        return providedTasks;
    }


    /**
     * Set the ArrayList of provided tasks for this user. That is, tasks for which this user is
     * the provider.
     *
     * @param providedTasks ArrayList of tasks for which this user is the provider
     */
    public void setProvidedTasks(ArrayList<Task> providedTasks) {
        this.providedTasks = providedTasks;
    }


    /**
     * Add a new task for which this user is the provider to this user's provided task list.
     *
     * @param newTask Task to be added to provided tasks list
     */
    public void addProvidedTask(Task newTask) {
        this.providedTasks.add(newTask);
    }


    /**
     * @return ArrayList of bids this user has made on all tasks
     * @see User#setBids
     * @see User#addBid
     */
    public ArrayList<Bid> getBids() {
        return bids;
    }


    /**
     * Set the ArrayList of bids that this user has made on all tasks.
     *
     * @param bids ArrayList of bids this user has made
     */
    public void setBids(ArrayList<Bid> bids) {
        this.bids = bids;
    }


    /**
     * Add a new bid this user has made to their bids list
     *
     * @param newBid Bid to be added to bids list
     */
    public void addBid(Bid newBid) {
        this.bids.add(newBid);
    }

    /**
     * Sets up and returns the user in display format: FirstName LastInitial. <br>
     * (ex. Daniel C.)
     *
     * @return The user's name in display format (FirstName LastInitial.)
     */
    public String toDisplayName() {
        String displayName = this.firstName + ' ' + this.lastName.substring(0, 1) + ".";
        return displayName;
    }
}
