package cmput301w18t22.com.tenner;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Schoolpost on 2018-02-26.
 */

public class Task {

    private String taskID;
    private String title;
    private String description;
    private ArrayList<Bid> bidList;
    private Location locaiton;
    private ArrayList<Photo> photos;
    private Date date;
    private Boolean hasNewBids;


    private Enum status;

    public String getTaskID() {
        return taskID;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }

    public Enum getStatus() {
        return status;
    }

    public void setStatus(Enum status) {
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
        return locaiton;
    }

    public void setLocaiton(Location locaiton) {
        this.locaiton = locaiton;
    }

    public ArrayList<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<Photo> photos) {
        this.photos = photos;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getHasNewBids() {
        return hasNewBids;
    }

    public void setHasNewBids(Boolean hasNewBids) {
        this.hasNewBids = hasNewBids;
    }


}
