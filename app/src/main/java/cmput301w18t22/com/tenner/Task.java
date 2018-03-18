package cmput301w18t22.com.tenner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import static cmput301w18t22.com.tenner.Task.Status.requested;

/**
 * Created by Schoolpost on 2018-02-26.
 */

public class Task {

    public enum Status {
        assigned, bidded, requested, done
    }

    private String taskID; // How are we using task ID?

    private Status status;
    private String title;
    private String description;
    private ArrayList<Bid> bidList;
    private Location location;
    private ArrayList<Photo> photos;
    private Date requestedDate;
    private Boolean hasNewBids;
    private User requester;
    private User provider;

    public void Task(String title, String description, Location location, Date date, User requester){
        this.status = requested;
        this.title = title;
        this.description = description;
        this.bidList = new ArrayList<Bid>();
        this.location = location;
        this.photos = new ArrayList<Photo>();
        this.requestedDate = date;
        this.hasNewBids = false;
        this.requester = requester;
    }

    public String getTaskID() {
        return taskID;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Bid> getBidList() {
        return bidList;
    }

    public void setBidList(ArrayList<Bid> bidList) {
        this.bidList = bidList;
    }

    public Location getLocaiton() {
        return location;
    }

    public void setLocaiton(Location locaiton) {
        this.location = locaiton;
    }

    public ArrayList<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<Photo> photos) {
        this.photos = photos;
    }

    public Date getRequestedDate() {
        return requestedDate;
    }

    public void setRequestedDate(Date date) {
        this.requestedDate = date;
    }

    public Boolean getHasNewBids() {
        return hasNewBids;
    }

    public void setHasNewBids(Boolean hasNewBids) {
        this.hasNewBids = hasNewBids;
    }

    public void setRequester(User user) {this.requester = user;}

    public User getRequester() { return this.requester; }

    public void setProvider(User user) {this.provider = user;}

    public User getProvider() {return this.provider;}

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
