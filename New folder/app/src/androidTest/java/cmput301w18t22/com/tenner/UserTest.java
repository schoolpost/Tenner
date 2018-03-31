package cmput301w18t22.com.tenner;

/**
 * Created by Dinesh on 2/26/2018.
 */

import android.test.ActivityInstrumentationTestCase2;

import cmput301w18t22.com.tenner.classes.Photo;
import cmput301w18t22.com.tenner.classes.User;

public class UserTest extends ActivityInstrumentationTestCase2 {

    public UserTest(){
        super(User.class);
    }

    /* - Email : str min(8)
    - First Name : str max(30)
    - Last Name : str max(30)
    - Phone : int max(10)
    - Photo : Photo (optional)
    */

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
}
