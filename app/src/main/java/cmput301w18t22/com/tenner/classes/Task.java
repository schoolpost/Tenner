package cmput301w18t22.com.tenner.classes;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import cmput301w18t22.com.tenner.helpers.StatusHelper;

/**
 * The Task class represents a task that has been requested. Each task has a taskID, status
 * (one of assigned, bidded, requested, or done), title, description, requestedDate, and requester.
 * Optionally, a task may have a location and one or more photos. <br><br>
 * <p>
 * Once requested, a task can acquire bids, and the requester can select a provider based on the bids.
 *
 * @author Team 22
 * @version 1.2
 * @see Bid
 * @see Location
 */
public class Task {

    /**
     * Represents the four possible statuses: requested (initial status), bidded (once a provider
     * has bid on a task), assigned (once the requester has accepted a bid on that task), and done
     * (once the requester marks the task complete.
     */
    private String taskID; // How are we using task ID?

    private String status;
    private String title;
    private String description;
    private ArrayList<Bid> bidList;
    private Location location;
    private ArrayList<String> photos;
    private Date requestedDate;
    private Boolean hasNewBids;
    private User requester;
    private User provider;

    public Task(String title, String description, Location location, Date date, User requester) {
        this.status = "requested";
        this.title = title;
        this.description = description;
        this.bidList = new ArrayList<Bid>();
        this.location = location;
        this.photos = new ArrayList<String>();
        this.requestedDate = date;
        this.hasNewBids = false;
        this.requester = requester;
    }


    /**
     * Passes the task's ID.
     *
     * @return this task's id
     * @see Task#setTaskID
     */
    public String getTaskID() {
        return taskID;
    }


    /**
     * Set the ID for this task. To be further defined in the future.
     *
     * @param taskID String representing taskID to set
     */
    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }


    /**
     * Passes the task's status.
     *
     * @return this task's current status
     * @see Task #setStatus
     */
    public String getStatus() {
        return this.status;
    }


    /**
     * Set the status of this task to one of requester, bidded, assigned, or done. This is not an
     * attribute users can change directly, but instead changes after certain events.
     *
     * @param status String representing taskID to set
     */
    public void setStatus(String status) {

        StatusHelper statusHelper = new StatusHelper();
        String newStatus = statusHelper.getTaskStatus(status);
        this.status = newStatus;
    }


    /**
     * Passes the task's title.
     *
     * @return this task's title
     * @see Task#setTitle
     */
    public String getTitle() {
        return title;
    }


    /**
     * Set the title of this task. Must eventually enforce a ~30 character limit.
     *
     * @param title String representing title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }


    /**
     * Passes the task's description.
     *
     * @return this task's description
     * @see Task#setDescription
     */
    public String getDescription() {
        return description;
    }


    /**
     * Set the description of this task. Must eventually enforce a ~300 character limit.
     *
     * @param description String representing description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * Passes a list of the bids made on the task. 
     *
     * @return ArrayList of bids on this task
     * @see Task#setBidList
     * @see Task#addBid
     * @see Task#removeBid
     */
    public ArrayList<Bid> getBidList() {
        return bidList;
    }


    /**
     * Set the ArrayList of bids on this task.
     *
     * @param bidList list of bids to set for this task.
     */
    public void setBidList(ArrayList<Bid> bidList) {
        this.bidList = bidList;
    }


    /**
     * Add a new bid to this task. If it is the first bid, set task status to bidded.
     *
     * @param bid bid to add to task
     */
    public void addBid(Bid bid) {
        this.bidList.add(bid);
        setStatus("bidded");

    }


    /**
     * Remove a specific bid from this task. Either because the provider removed it, the requester
     * rejected it, or the requester rejected a different bid.
     *
     * @param bid bid to be removed
     */
    public void removeBid(Bid bid) {

        if (bidList.size() != 0) {
            this.bidList.remove(bid);
        } else {
            setStatus("requested");
        }

    }


    /**
     * Returns the current location of a task, if any.
     *
     * @return this task's location, or null if location does not exist
     * @see Task#setTaskID
     */
    public Location getLocation() {
        if (location != null) return location;
        else return null;
    }


    /**
     * Set the location of this task.
     *
     * @param location Location object belonging to this task.
     */
    public void setLocation(Location location) {
        this.location = location;
    }


    /**
     * Passes all of the photos attached to the task.
     *
     * @return ArrayList of photos for this task
     * @see Task@setPhotos
     * @see Task#addPhoto
     */
    public ArrayList<String> getPhotos() {
        return photos;
    }


    /**
     * Set the photos associated with this task. Each must be less than 65536 bytes.
     * There is a limit of 3 photos per task.
     *
     * @param photos ArrayList of photos to add.
     */
    public void setPhotos(ArrayList<String> photos) {
        this.photos = photos;
    }


    /**
     * Add new photo to task. Must ensure it is less than 65535 bytes in size.
     * Only avaliable if the task has less than three photos already attached to it. 
     *
     * @param photo Photo to be added to task
     */
    public void addPhoto(String photo) {
        this.photos.add(photo);
    }


    /**
     * Passes the date the task was posted.
     *
     * @return this task's requested date
     * @see Task#setRequestedDate
     */
    public Date getRequestedDate() {
        return requestedDate;
    }


    /**
     * Set the requested date of this task.
     *
     * @param date Date object representing this task's requested date
     */
    public void setRequestedDate(Date date) {
        this.requestedDate = date;
    }


    /**
     * Passes the status of the "hasNewBids" flag on the task.
     *
     * @return Boolean representing whether task has new bids
     * @see Task#setHasNewBids
     */
    public Boolean getHasNewBids() {
        return hasNewBids;
    }


    /**
     * Set has new bids boolean. If hasNewBids is equal to 1, the task requester will be notified,
     * and hasNewBids will be restore to 0 once they see the notification.
     *
     * @param hasNewBids Boolean representing new hasNewBids value
     */
    public void setHasNewBids(Boolean hasNewBids) {
        this.hasNewBids = hasNewBids;
    }


    /**
     * Set the requester of this task.
     *
     * @param user User representing requester of this task
     */
    public void setRequester(User user) {
        this.requester = user;
    }


    /**
     * Passes the user that posted the task. 
     *
     * @return this task's requester
     * @see Task#setRequester
     */
    public User getRequester() {
        return this.requester;
    }


    /**
     * Set the provider of this task. Starts as null, and is changed to a user once that user's bid
     * has been accepted by this task's requester.
     *
     * @param user User object whose bid has been accepted to provide this task
     */
    public void setProvider(User user) {
        this.provider = user;
    }


    /**
     * Passes the user selected to perform the task, if one has been chosen.
     *
     * @return this task's provider, if one exists
     * @see Task#setProvider
     */
    public User getProvider() {
        return this.provider;
    }

    /**
     * Finds and returns the lowest bid on the task for display in MyBidLists
     *
     * @return Bid on task with the lowest value.
     */
    public Bid getLowestBid() {
        int lowest = 0;
        for (int i = 1; i < this.bidList.size(); i++) {
            BigDecimal i_value = this.bidList.get(i).getValue();
            if (i_value.compareTo(this.bidList.get(lowest).getValue()) < 0) {
                lowest = i;
            }
        }
        return this.bidList.get(lowest);
    }
}
