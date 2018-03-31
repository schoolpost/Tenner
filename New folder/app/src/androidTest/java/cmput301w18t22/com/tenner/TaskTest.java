package cmput301w18t22.com.tenner;

import android.test.ActivityInstrumentationTestCase2;

import java.util.ArrayList;
import java.util.Date;

import cmput301w18t22.com.tenner.classes.Bid;
import cmput301w18t22.com.tenner.classes.Location;
import cmput301w18t22.com.tenner.classes.Photo;
import cmput301w18t22.com.tenner.classes.Task;

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


    public void testGetLoction() {
        Task task = new Task();
        Location loc = new Location();
        loc.setLatitude(12.2f);
        task.setLocation(loc);

        assertEquals(loc, task.getLocation());

    }

    public void testSetLocation() {
        Task task = new Task();
        Location loc = new Location();
        loc.setLatitude(12.2f);
        task.setLocation(loc);

        assertEquals(loc, task.getLocation());
    }

    public void testGetPhoto() {
        Task task = new Task();
        Photo photo = new Photo();


        ArrayList<Photo> photolist = task.getPhotos();
        photolist.add(photo);

        Photo returnedphoto = photolist.get(0);
        assertEquals(photo.getImageFile(), returnedphoto.getImageFile());

    }

    public void testDeletePhoto() {

        Task task = new Task();
        Photo photo = new Photo();

        ArrayList<Photo> photolist = task.getPhotos();
        photolist.add(photo);
        photolist.remove(photo);

        assertFalse(photolist.contains(photo));

    }

    public void testAddPhoto() {

        Task task = new Task();
        Photo photo = new Photo();

        ArrayList<Photo> photolist = task.getPhotos();
        photolist.add(photo);

        assertTrue(photolist.contains(photo));


    }

    public void testHasPhoto() {

        Task task = new Task();
        Photo photo = new Photo();

        ArrayList<Photo> photolist = task.getPhotos();

        assertFalse(photolist.contains(photo));
        photolist.add(photo);
        assertTrue(photolist.contains(photo));


    }

    public void testGetDate() {
        Task task = new Task();
        Date date = new Date();
        task.setDate(date);
        assertEquals(date, task.getDate());
    }

    public void testSetDate() {
        Task task = new Task();
        Date date = new Date();
        task.setDate(date);
        assertEquals(date, task.getDate());
    }

    public void testgetHasNewBids() {
        Task task = new Task();
        Boolean hasNew = false;
        task.setHasNewBids(hasNew);
        assertTrue(task.getHasNewBids() == hasNew);
    }

    public void testHasNewBids(Boolean hasNewBids) {
        Task task = new Task();
        Boolean hasNew = false;
        task.setHasNewBids(hasNew);
        assertTrue(task.getHasNewBids() == hasNew);
    }

}
