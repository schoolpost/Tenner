package com.example.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by moc on 3/24/18.
 */

public class Task {
    private String taskID; // How are we using task ID -> Date for now?
    private Status.bidStatus status;
    private String title;
    private String description;
    private ArrayList<Bid> bidList;
    private Location location;
    private ArrayList<Photo> photos;
    private Date requestedDate;
    private Boolean hasNewBids;
    private User requester;
    private User provider;

    public Task(String title, String description, Location location, Date date, User requester){
        this.taskID = String.valueOf(date);
        this.status = Status.bidStatus.requested;
        this.title = title;
        this.description = description;
        this.bidList = new ArrayList<Bid>();
        this.location = location;
        //TODO implement photo
        //this.photos = new ArrayList<Photo>();
        this.requestedDate = date;
        this.hasNewBids = false;
        this.requester = requester;
    }

    /**
     * @return this Task's ID
     * @see Task # setTaskID
     */
    public String getTaskID() {
        return taskID;
    }

    /**
     * Set the taskID of this Task
     * @param taskID, new String value for this Task
     */
    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }

    /**
     * @return this Task's status
     * @see Task # setStatus
     */
    public Status.bidStatus getStatus() {
        return this.status;
    }

    /**
     * Set the status of this Task
     * @param status, new Status.bidStatus value for this Task
     */
    public void setStatus(Status.bidStatus status) {
        this.status = status;
    }

    /**
     * @return this Task's title
     * @see Task # setTitle
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the title of this Task
     * @param title, new String value for this Task
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return this Task's description
     * @see Task # setDescription
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the description of this Task
     * @param description, new String value for this Task
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return this Task's bidlist
     * @see Task # setBidList
     */
    public ArrayList<Bid> getBidList() {
        return bidList;
    }

    /**
     * Set the bidList of this Task
     * @param bidList, new ArrayList<Bid> value for this Task
     */
    public void setBidList(ArrayList<Bid> bidList) {
        this.bidList = bidList;
    }

    /**
     * @return this Task's location
     * @see Task # setLocation
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Set the location of this Task
     * @param location, new Location value for this Task
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * @return this Task's photos
     * @see Task # setPhotos
     */
    public ArrayList<Photo> getPhotos() {
        return photos;
    }

    /**
     * Set the photos of this Task
     * @param photos, new ArrayList<Photo> value for this Task
     */
    public void setPhotos(ArrayList<Photo> photos) {
        this.photos = photos;
    }

    /**
     * @return this Task's requestedDate
     * @see Task # setRequestedDate
     */
    public Date getRequestedDate() {
        return requestedDate;
    }

    /**
     * Set the requestedDate of this Task
     * @param date, new Date value for this Task
     */
    public void setRequestedDate(Date date) {
        this.requestedDate = date;
    }

    /**
     * @return whether this task has new bids or not
     * @see Task # setHasNewBids
     */
    public Boolean getHasNewBids() {
        return hasNewBids;
    }

    /**
     * Set the hasNewBids of this Task
     * @param hasNewBids, new Boolean value for this Task
     */
    public void setHasNewBids(Boolean hasNewBids) {
        this.hasNewBids = hasNewBids;
    }

    /**
     * @return this Task's requester
     * @see Task # setRequester
     */
    public User getRequester() {
        return this.requester;
    }

    /**
     * Set the requester of this Task
     * @param user, new User value for this Task
     */
    public void setRequester(User user) {
        this.requester = user;
    }

    /**
     * @return this Task's provider
     * @see Task # setProvider
     */
    public User getProvider() {
        return this.provider;
    }

    /**
     * Set the provider of this Task
     * @param user, new User value for this Task
     */
    public void setProvider(User user) {
        this.provider = user;
    }

    /**
     * @return this Tasks's lowest bid
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
