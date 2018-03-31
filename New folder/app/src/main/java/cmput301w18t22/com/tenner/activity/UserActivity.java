package cmput301w18t22.com.tenner.activity;

import android.media.Image;

/**
 * Created by Dinesh on 2/26/2018.
 */

public class UserActivity {

    /* - Email : str min(8)
    - First Name : str max(30)
    - Last Name : str max(30)
    - Phone : int max(10)
    - Photo : Photo (optional)
    */

    private String email;
    private String f_name;
    private String l_name;
    private int p_num;
    private Image photo;

    public UserActivity(String email, String f_name, String l_name, int p_num, Image photo){
        this.email = email;
        this.f_name = f_name;
        this.l_name = l_name;
        this.p_num = p_num;
        this.photo = photo;
    }

    public UserActivity UserLoginTask(String email, String optionalpassword){
        return null;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getFname()
    {
        return f_name;
    }

    public void setFname(String f_name){
        this.f_name = f_name;
    }

    public String getLname()
    {
        return l_name;
    }

    public void setLname(String l_name){
        this.l_name = l_name;
    }

    public int getPhoneNum()
    {
        return p_num;
    }

    public void setPhoneNum(int p_num){
        this.p_num = p_num;
    }

    public void setImage(Image photo){
        this.photo = photo;
    }

    public  Image getImage(){
        return photo;
    }

}
