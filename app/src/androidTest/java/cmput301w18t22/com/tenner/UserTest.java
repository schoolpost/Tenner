package cmput301w18t22.com.tenner;

/**
 * Created by Dinesh on 2/26/2018.
 */

import android.media.Image;
import android.test.ActivityInstrumentationTestCase2;

import org.junit.Test;

import java.io.IOException;

public class UserTest extends ActivityInstrumentationTestCase2 {

    /* - Email : str min(8)
    - First Name : str max(30)
    - Last Name : str max(30)
    - Phone : int max(10)
    - Photo : Photo (optional)
    */

    String emailtest = "ualberta@hotmail.com";
    String f_nametest = "First";
    String l_nametest = "Last";
    int phone_numtest =  780-123-4567;
    Image phototest = null;


    public UserTest() {
        super(UserActivity.class);

    }

    public void testGetEmail() {
        UserActivity user = new UserActivity(emailtest,f_nametest,l_nametest,phone_numtest,phototest);
        String email = "ualberta@hotmail.com";
        user.setEmail(email);
        assertEquals(email, user.getEmail());
    }

    public void testSetEmail() {
        UserActivity user = new UserActivity(emailtest,f_nametest,l_nametest,phone_numtest,phototest);
        String email = "ualberta@hotmail.com";
        user.setEmail(email);
        assertTrue(email, user.getEmail().length() > 8);
        assertEquals(email, user.getEmail());
    }

    public void testGetFname() {
        UserActivity user = new UserActivity(emailtest,f_nametest,l_nametest,phone_numtest,phototest);
        String fname = "first";
        user.setFname(fname);
        assertEquals(fname, user.getFname());
    }

    public void testSetFname() {
        UserActivity user = new UserActivity(emailtest,f_nametest,l_nametest,phone_numtest,phototest);
        String fname = "first";
        user.setFname(fname);
        assertTrue(fname, user.getFname().length() < 30);
        assertEquals(fname, user.getFname());
    }

    public void testGetLname() {
        UserActivity user = new UserActivity(emailtest,f_nametest,l_nametest,phone_numtest,phototest);
        String lname = "last";
        user.setLname(lname);
        assertEquals(lname, user.getLname());
    }

    public void testSetLname() {
        UserActivity user = new UserActivity(emailtest,f_nametest,l_nametest,phone_numtest,phototest);
        String lname = "last";
        user.setLname(lname);
        assertTrue(lname, user.getLname().length() < 30);
        assertEquals(lname, user.getLname());
    }

    public void testGetPhone() {
        UserActivity user = new UserActivity(emailtest,f_nametest,l_nametest,phone_numtest,phototest);
        int phone = 123-456-7890;
        user.setPhoneNum(phone);
        assertEquals(phone, user.getPhoneNum());
    }

    public void testSetPhone() {
        UserActivity user = new UserActivity(emailtest,f_nametest,l_nametest,phone_numtest,phototest);
        int phone = 1234567890;
        user.setPhoneNum(phone);
        assertEquals(phone, user.getPhoneNum());
    }


}
