package cmput301w18t22.com.tenner;

import android.test.ActivityInstrumentationTestCase2;

import java.util.Date;

import cmput301w18t22.com.tenner.classes.Bid;
import cmput301w18t22.com.tenner.classes.Location;
import cmput301w18t22.com.tenner.classes.Task;
import cmput301w18t22.com.tenner.classes.User;

import static cmput301w18t22.com.tenner.Helpers.Status.bidStatus.assigned;

/**
 * Created by Dinesh on 2/26/2018.
 */


public class UserTest extends ActivityInstrumentationTestCase2 {

    public UserTest(){
        super(User.class);
    }
/*
    - Email : str min(8)
    - First Name : str max(30)
    - Last Name : str max(30)
    - Phone : int max(10)
    - Photo : Photo (optional)


        public void testGetEmail() {
            User user = new User();
            String email = "cmput301@gmail.com";
            user.setEmail(email);

            assertTrue(user.getEmail().contains("@"));
            assertTrue(user.getEmail().length() <= 8);
            assertEquals(user.getEmail(), email);
        }

        public void setEmail() {
            User user = new User();
            String email = "cmput301@gmail.com";
            user.setEmail(email);

            assertTrue(user.getEmail().contains("@"));
            assertTrue(user.getEmail().length() <= 8);
            assertEquals(user.getEmail(), email);
        }

        public void getFirstName() {
            User user = new User();
            String name = "cmput301";
            user.setFirstName(name);

            assertTrue(user.getFirstName().length() <= 30);
            assertEquals(user.getFirstName(), name);
        }

        public void setFirstName() {
            User user = new User();
            String name = "cmput301";
            user.setFirstName(name);

            assertTrue(user.getFirstName().length() <= 30);
            assertEquals(user.getFirstName(), name);
        }

        public void getLastName() {
            User user = new User();
            String lname = "cmput302";
            user.setLastName(lname);

            assertTrue(user.getLastName().length() <= 30);
            assertEquals(user.getLastName(), lname);
        }

        public void setLastName() {
            User user = new User();
            String lname = "cmput302";
            user.setLastName(lname);

            assertTrue(user.getLastName().length() <= 30);
            assertEquals(user.getLastName(), lname);
        }

        public void getPhoneNum() {
            User user = new User();
            String pnum = "17804830394";
            user.setPhoneNum(pnum);

            assertTrue(user.getPhoneNum().length() == 10);
            assertEquals(user.getPhoneNum(), pnum);
        }

        public void setPhoneNum() {
            User user = new User();
            String pnum = "17804830394";
            user.setPhoneNum(pnum);

            assertTrue(user.getPhoneNum().length() == 10);
            assertEquals(user.getPhoneNum(), pnum);
        }

        public void getPhoto() {
            User user = new User();
            Photo photo = new Photo();
            user.setPhoto(photo);

            assertEquals(user.getPhoto(), photo);
        }

        public void setPhoto() {
            User user = new User();
            Photo photo = new Photo();
            user.setPhoto(photo);

            assertEquals(user.getPhoto(), photo);
        }
*/

    public void testAddRandPTask() {
        User testUser = createTestUser();

        Task task1 = createTestTask();
        task1.setTitle("Task 1");
        Task task2 = createTestTask();
        task2.setTitle("Task 2");

        testUser.addRequestedTask(task1);
        assertEquals(testUser.getRequestedTasks().get(0), task1);

        testUser.addProvidedTask(task2);
        assertEquals(testUser.getProvidedTasks().get(0), task2);

    }

    public void testAddBid() {
        User testUser = createTestUser();

        Bid bid = createTestBid();
        int i;
        for (i = 0; i < 10; i++) {
            testUser.addBid(bid);
        }

        assertEquals(testUser.getBids().size(), 10);

    }

    public void testToDisplayName() {
        User testUser = createTestUser();

        testUser.setFirstName("Bruce");
        testUser.setLastName("Wayne");

        assertEquals(testUser.toDisplayName(), "Bruce W.");
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

    private Bid createTestBid() {
        Bid bid = new Bid(createTestUser(), "1.00", new Date(), createTestTask(), assigned);
        return bid;
    }
}
