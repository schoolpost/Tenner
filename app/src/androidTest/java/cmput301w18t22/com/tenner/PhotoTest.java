package cmput301w18t22.com.tenner;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.test.ActivityInstrumentationTestCase2;

import java.util.ArrayList;
import java.util.Date;

import cmput301w18t22.com.tenner.classes.Photo;
import cmput301w18t22.com.tenner.classes.Task;

/**
 * Created by Dinesh on 4/8/2018.
 */

public class PhotoTest extends ActivityInstrumentationTestCase2 {

    public PhotoTest() {
        super(Photo.class);
    }

    public void getPhotoTest(){

        String photoString = "photo";
        Photo photo = new Photo(photoString);
        assertTrue(photo.getPhoto().equals(photoString));

    }

    public void setPhotoTest(){
        String photoS1 = "photo 1";
        Photo photo1 = new Photo(photoS1);
        String photoS2 = "photo 2";
        Photo photo2 = new Photo(photoS2);
        photo1.setPhoto(photoS2);
        assertTrue(photo1.getPhoto().equals(photoS2));
    }

}

