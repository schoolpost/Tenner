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
        User user = new User("john@email.com", "John", "Doe", "16956950000");
        Location loc = new Location(14.2f, 15.2f, "new address");
        Task task = new Task("","", loc, new Date(), user);

        String id = "123456";
        task.setTaskID(id);

        assertEquals(id, task.getTaskID());
    }

    public void testSetTaskID() {
        User user = new User("john@email.com", "John", "Doe", "16956950000");
        Location loc = new Location(14.2f, 15.2f, "new address");
        Task task = new Task("","", loc, new Date(), user);

        String id = "123456";
        task.setTaskID(id);

        assertEquals(id, task.getTaskID());
    }

    public void testGetStatus() {
        User user = new User("john@email.com", "John", "Doe", "16956950000");
        Location loc = new Location(14.2f, 15.2f, "new address");
        Task task = new Task("","", loc, new Date(), user);

        Status.bidStatus newStatus = Status.bidStatus.assigned;
        task.setStatus(newStatus);

        assertEquals(newStatus, task.getStatus());
    }

    public void testSetStatus() {
        User user = new User("john@email.com", "John", "Doe", "16956950000");
        Location loc = new Location(14.2f, 15.2f, "new address");
        Task task = new Task("","", loc, new Date(), user);

        Status.bidStatus newStatus = Status.bidStatus.assigned;
        task.setStatus(newStatus);
        assertEquals(newStatus, task.getStatus());
    }

    public void testGetTitle() {
        User user = new User("john@email.com", "John", "Doe", "16956950000");
        Location loc = new Location(14.2f, 15.2f, "new address");
        Task task = new Task("","", loc, new Date(), user);

        String title = "Some Task Title";
        task.setTitle(title);

        assertEquals(title, task.getTitle());
    }

    public void testSetTitle() {
        User user = new User("john@email.com", "John", "Doe", "16956950000");
        Location loc = new Location(14.2f, 15.2f, "new address");
        Task task = new Task("","", loc, new Date(), user);

        String title = "Some Task Title";
        task.setTitle(title);
        assertTrue(title, task.getTitle().length() < 100);

        assertEquals(title, task.getTitle());
    }

    public void testGetDescription() {
        User user = new User("john@email.com", "John", "Doe", "16956950000");
        Location loc = new Location(14.2f, 15.2f, "new address");
        Task task = new Task("","", loc, new Date(), user);

        String description = "Some description of a task and the cool thigns you can do with this app.";
        task.setDescription(description);

        assertEquals(description, task.getTitle());
    }

    public void testSetDescription() {
        User user = new User("john@email.com", "John", "Doe", "16956950000");
        Location loc = new Location(14.2f, 15.2f, "new address");
        Task task = new Task("","", loc, new Date(), user);

        String description = "Some description of a task and the cool thigns you can do with this app.";
        task.setDescription(description);

        assertTrue(description, task.getDescription().length() < 500);
        assertEquals(description, task.getTitle());
    }

    public void testGetBid() {
        User user = new User("john@email.com", "John", "Doe", "16956950000");
        Location loc = new Location(14.2f, 15.2f, "new address");
        Task task = new Task("","", loc, new Date(), user);

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
        User user = new User("john@email.com", "John", "Doe", "16956950000");
        Location loc = new Location(14.2f, 15.2f, "new address");
        Task task = new Task("","", loc, new Date(), user);

        Bid bid = new Bid();

        ArrayList<Bid> bidlist = task.getBidList();
        bidlist.add(bid);
        bidlist.remove(bid);

        assertFalse(bidlist.contains(bid));
    }

    public void testAddBid() {
        User user = new User("john@email.com", "John", "Doe", "16956950000");
        Location loc = new Location(14.2f, 15.2f, "new address");
        Task task = new Task("","", loc, new Date(), user);

        Bid bid = new Bid();

        ArrayList<Bid> bidlist = task.getBidList();
        bidlist.add(bid);

        assertTrue(bidlist.contains(bid));
    }

    public void testHasBid() {
        User user = new User("john@email.com", "John", "Doe", "16956950000");
        Location loc = new Location(14.2f, 15.2f, "new address");
        Task task = new Task("","", loc, new Date(), user);

        Bid bid = new Bid();

        ArrayList<Bid> bidlist = task.getBidList();

        assertFalse(bidlist.contains(bid));
        bidlist.add(bid);

        assertTrue(bidlist.contains(bid));
    }


    public void testGetLoction() {
        User user = new User("john@email.com", "John", "Doe", "16956950000");
        Location loc = new Location(14.2f, 15.2f, "new address");
        Task task = new Task("","", loc, new Date(), user);

        loc.setLatitude(12.2f);
        task.setLocation(loc);

        assertEquals(loc, task.getLocation());
    }

    public void testSetLocation() {
        User user = new User("john@email.com", "John", "Doe", "16956950000");
        Location loc = new Location(14.2f, 15.2f, "new address");
        Task task = new Task("","", loc, new Date(), user);

        loc.setLatitude(12.2f);
        task.setLocation(loc);

        assertEquals(loc, task.getLocation());
    }

    public void testGetPhoto() {
        User user = new User("john@email.com", "John", "Doe", "16956950000");
        Location loc = new Location(14.2f, 15.2f, "new address");
        Task task = new Task("","", loc, new Date(), user);

        Photo photo = new Photo();
        ArrayList<Photo> photolist = task.getPhotos();
        photolist.add(photo);

        Photo returnedphoto = photolist.get(0);
        assertEquals(photo.getImageFile(), returnedphoto.getImageFile());

    }

    public void testDeletePhoto() {
        User user = new User("john@email.com", "John", "Doe", "16956950000");
        Location loc = new Location(14.2f, 15.2f, "new address");
        Task task = new Task("","", loc, new Date(), user);

        Photo photo = new Photo();

        ArrayList<Photo> photolist = task.getPhotos();
        photolist.add(photo);
        photolist.remove(photo);

        assertFalse(photolist.contains(photo));
    }

    public void testAddPhoto() {
        User user = new User("john@email.com", "John", "Doe", "16956950000");
        Location loc = new Location(14.2f, 15.2f, "new address");
        Task task = new Task("","", loc, new Date(), user);

        Photo photo = new Photo();

        ArrayList<Photo> photolist = task.getPhotos();
        photolist.add(photo);

        assertTrue(photolist.contains(photo));
    }

    public void testHasPhoto() {
        User user = new User("john@email.com", "John", "Doe", "16956950000");
        Location loc = new Location(14.2f, 15.2f, "new address");
        Task task = new Task("","", loc, new Date(), user);

        Photo photo = new Photo();

        ArrayList<Photo> photolist = task.getPhotos();

        assertFalse(photolist.contains(photo));
        photolist.add(photo);

        assertTrue(photolist.contains(photo));
    }

    public void testGetDate() {
        User user = new User("john@email.com", "John", "Doe", "16956950000");
        Location loc = new Location(14.2f, 15.2f, "new address");
        Task task = new Task("","", loc, new Date(), user);

        Date date = new Date();
        task.setRequestedDate(date);

        assertEquals(date, task.getRequestedDate());
    }

    public void testSetDate() {
        User user = new User("john@email.com", "John", "Doe", "16956950000");
        Location loc = new Location(14.2f, 15.2f, "new address");
        Task task = new Task("","", loc, new Date(), user);

        Date date = new Date();
        task.setRequestedDate(date);
        assertEquals(date, task.getRequestedDate());
    }

    public void testgetHasNewBids() {
        User user = new User("john@email.com", "John", "Doe", "16956950000");
        Location loc = new Location(14.2f, 15.2f, "new address");
        Task task = new Task("","", loc, new Date(), user);

        Boolean hasNew = false;
        task.setHasNewBids(hasNew);

        assertTrue(task.getHasNewBids() == hasNew);
    }

    public void testHasNewBids(Boolean hasNewBids) {
        User user = new User("john@email.com", "John", "Doe", "16956950000");
        Location loc = new Location(14.2f, 15.2f, "new address");
        Task task = new Task("","", loc, new Date(), user);

        Boolean hasNew = false;
        task.setHasNewBids(hasNew);

        assertTrue(task.getHasNewBids() == hasNew);
    }

}
