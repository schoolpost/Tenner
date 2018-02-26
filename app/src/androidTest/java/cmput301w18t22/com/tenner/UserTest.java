package cmput301w18t22.com.tenner;

/**
 * Created by Dinesh on 2/26/2018.
 */

import android.media.Image;
import android.test.ActivityInstrumentationTestCase2;
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



}
