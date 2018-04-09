package cmput301w18t22.com.tenner.classes;

/**
 * Created by Schoolpost on 2018-02-26.
 *
 * The Photo class is used to add pictures to various places in the app. Primarily for tasks and users. 
 *
 * @author Team 22
 * version 1.0
 */

public class Photo {

    private String photo;

    public Photo(String photo){
        this.photo = photo;
    }
    
    /**
     * Passes the photo.
     *
     * @return the photo. 
     */
    public String getPhoto() {
        return photo;
    }

}
