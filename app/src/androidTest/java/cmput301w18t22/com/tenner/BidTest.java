package cmput301w18t22.com.tenner;

import android.test.ActivityInstrumentationTestCase2;

import java.util.Date;

import cmput301w18t22.com.tenner.classes.Bid;
import cmput301w18t22.com.tenner.classes.Location;
import cmput301w18t22.com.tenner.classes.Task;
import cmput301w18t22.com.tenner.classes.User;

import static cmput301w18t22.com.tenner.classes.Status.bidStatus.assigned;

public class BidTest extends ActivityInstrumentationTestCase2 {

    public BidTest() {
        super(Bid.class);

    }
/*
    public void testSetOwner() {

        Bid bid = new Bid();
        User user = new User();
        bid.setOwner(user);
        assertEquals(bid.getOwner(), user);

    }

    public void testSetValue() {
        Bid bid = new Bid();
        Double val = 2.0;

        BigDecimal Bg = new BigDecimal(val);
        bid.setValue(Bg);

        assertEquals(Bg, bid.getValue());
    }


    public void testGetOwner() {
        Bid bid = new Bid();
        User user = new User();
        bid.setOwner(user);
        assertEquals(bid.getOwner(), user);
    }

    public void testGetValue() {
        Bid bid = new Bid();
        Double val = 2.0;

        BigDecimal Bg = new BigDecimal(val);
        bid.setValue(Bg);

        assertEquals(Bg, bid.getValue());
    }
*/

    public void testToString() {
        Bid bid = new Bid(createTestUser(), "2.25", new Date(), createTestTask(), assigned);

        assertEquals(bid.toString(), "$ 2.25");
    }

    private User createTestUser(){
        User testUser = new User("email@example.com", "First", "Last", "780-123-4567");
        return testUser;
    }

    private Task createTestTask() {
        Location testLocation = new Location(0.0f, 0.0f, "123 Main St");
        Task testTask = new Task("Task Title", "Task Description", testLocation, new Date(), createTestUser());
        return testTask;
    }
}