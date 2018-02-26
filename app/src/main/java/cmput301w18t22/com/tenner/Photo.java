package cmput301w18t22.com.tenner;

import android.media.Image;

import java.io.File;

/**
 * Created by Schoolpost on 2018-02-26.
 */

public class Photo {

    private File imageFile;
    private int width;
    private int height;

    public File getImageFile() {
        return imageFile;
    }

    public void setImageFile(File imageFile) {
        this.imageFile = imageFile;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
