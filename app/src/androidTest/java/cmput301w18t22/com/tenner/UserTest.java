package cmput301w18t22.com.tenner;

/**
 * Created by Dinesh on 2/26/2018.
 */

import android.media.Image;
import android.test.ActivityInstrumentationTestCase2;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;

public class UserTest extends ActivityInstrumentationTestCase2 {

    public UserTest(){
        super(User.class);
    }

    public void testGetEmail() {
        User user = new User("john@email.com", "John", "Doe", "16956950000");
        String email = "cmput301@gmail.com";
        user.setEmail(email);

        assertTrue(user.getEmail().contains("@"));
        assertTrue(user.getEmail().length() <= 8);
        assertEquals(user.getEmail(), email);
    }

    public void setEmail() {
        User user = new User("john@email.com", "John", "Doe", "16956950000");
        String email = "cmput301@gmail.com";
        user.setEmail(email);

        assertTrue(user.getEmail().contains("@"));
        assertTrue(user.getEmail().length() <= 8);
        assertEquals(user.getEmail(), email);
    }

    public void getFirstName() {
        User user = new User("john@email.com", "John", "Doe", "16956950000");
        String name = "cmput301";
        user.setFirstName(name);

        assertTrue(user.getFirstName().length() <= 30);
        assertEquals(user.getFirstName(), name);
    }

    public void setFirstName() {
        User user = new User("john@email.com", "John", "Doe", "16956950000");
        String name = "cmput301";
        user.setFirstName(name);

        assertTrue(user.getFirstName().length() <= 30);
        assertEquals(user.getFirstName(), name);
    }

    public void getLastName() {
        User user = new User("john@email.com", "John", "Doe", "16956950000");
        String lname = "cmput302";
        user.setLastName(lname);

        assertTrue(user.getLastName().length() <= 30);
        assertEquals(user.getLastName(), lname);
    }

    public void setLastName() {
        User user = new User("john@email.com", "John", "Doe", "16956950000");
        String lname = "cmput302";
        user.setLastName(lname);

        assertTrue(user.getLastName().length() <= 30);
        assertEquals(user.getLastName(), lname);
    }

    public void getPhoneNum() {
        User user = new User("john@email.com", "John", "Doe", "16956950000");
        String pnum = "17804830394";
        user.setPhoneNum(pnum);

        assertTrue(user.getPhoneNum().length() == 10);
        assertEquals(user.getPhoneNum(), pnum);
    }

    public void setPhoneNum() {
        User user = new User("john@email.com", "John", "Doe", "16956950000");
        String pnum = "17804830394";
        user.setPhoneNum(pnum);

        assertTrue(user.getPhoneNum().length() == 10);
        assertEquals(user.getPhoneNum(), pnum);
    }

    public void getPhoto() {
        User user = new User("john@email.com", "John", "Doe", "16956950000");
        Photo photo = new Photo();
        user.setPhoto(photo);

        assertEquals(user.getPhoto(), photo);
    }

    public void setPhoto() {
        User user = new User("john@email.com", "John", "Doe", "16956950000");
        Photo photo = new Photo();
        user.setPhoto(photo);

        assertEquals(user.getPhoto(), photo);
    }
}