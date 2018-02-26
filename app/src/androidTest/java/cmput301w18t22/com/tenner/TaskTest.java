package cmput301w18t22.com.tenner;

import android.test.ActivityInstrumentationTestCase2;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Schoolpost on 2018-02-26.
 */

public class TaskTest extends ActivityInstrumentationTestCase2 {

    public TaskTest() {
        super(Task.class);
    }

    public void testGetTaskID() {
        Task task = new Task();
        String id = "123456";
        task.setTaskID(id);
        assertEquals(id, task.getTaskID());
    }

    public void testSetTaskID() {
        Task task = new Task();
        String id = "123456";
        task.setTaskID(id);
        assertEquals(id, task.getTaskID());
    }

    public void testGetStatus() {
        Task task = new Task();
        Statuses testStatus = Statuses.assigned;
        task.setStatus(testStatus);
        assertEquals(testStatus, task.getStatus());

    }

    public void testSetStatus() {
        Task task = new Task();
        Statuses testStatus = Statuses.assigned;
        task.setStatus(testStatus);
        assertEquals(testStatus, task.getStatus());
    }

    public void testGetTitle() {
        Task task = new Task();
        String title = "Some Task Title";
        task.setTitle(title);
        assertEquals(title, task.getTitle());
    }

    public void testSetTitle() {
        Task task = new Task();
        String title = "Some Task Title";
        task.setTitle(title);
        assertTrue(title, task.getTitle().length() < 100);
        assertEquals(title, task.getTitle());
    }

    public void testGetDescription() {
        Task task = new Task();
        String description = "Some description of a task and the cool thigns you can do with this app.";
        task.setDescription(description);
        assertEquals(description, task.getTitle());
    }

    public void testSetDescription() {
        Task task = new Task();
        String description = "Some description of a task and the cool thigns you can do with this app.";
        task.setDescription(description);
        assertTrue(description, task.getDescription().length() < 500);
        assertEquals(description, task.getTitle());
    }

    public void testGetBid() {
        Task task = new Task();
        Bid bid = new Bid();

        Date date = new Date();
        bid.setDate(date);

        ArrayList<Bid> bidlist = task.getBidList();
        bidlist.add(bid);

        Bid returnedBid = bidlist.get(0);
        assertEquals(bid.getDate(), returnedBid.getDate());
        assertEquals(bid.getOwner(), returnedBid.getOwner());
        assertEquals(bid.getValue(), returnedBid.getValue());

    }

    public void testDeleteBid() {

        Task task = new Task();
        Bid bid = new Bid();

        ArrayList<Bid> bidlist = task.getBidList();
        bidlist.add(bid);
        bidlist.remove(bid);

        assertFalse(bidlist.contains(bid));

    }

    public void testAddBid() {

        Task task = new Task();
        Bid bid = new Bid();

        ArrayList<Bid> bidlist = task.getBidList();
        bidlist.add(bid);

        assertTrue(bidlist.contains(bid));


    }

    public void testHasBid() {

        Task task = new Task();
        Bid bid = new Bid();

        ArrayList<Bid> bidlist = task.getBidList();

        assertFalse(bidlist.contains(bid));
        bidlist.add(bid);
        assertTrue(bidlist.contains(bid));


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
