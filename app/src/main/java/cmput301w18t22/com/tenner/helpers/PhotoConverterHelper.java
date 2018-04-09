package cmput301w18t22.com.tenner.helpers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class PhotoConverterHelper {

    private static final PhotoConverterHelper ourInstance = new PhotoConverterHelper();

    static PhotoConverterHelper getInstance() {
        return ourInstance;
    }

    public PhotoConverterHelper() {
    }

    //https://developer.android.com/training/camera/photobasics.html#TaskScalePhoto
    //https://stackoverflow.com/questions/10513976/how-to-convert-image-into-byte-array-and-byte-array-to-base64-string-in-android
    //https://stackoverflow.com/questions/4837110/how-to-convert-a-base64-string-into-a-bitmap-image-to-show-it-in-a-imageview
    //https://stackoverflow.com/questions/16804404/create-a-bitmap-drawable-from-file-path

    public String convertBMToString(Bitmap bitmap) {
        //TODO
//        bitmap = Bitmap.createScaledBitmap(bitmap, (int) (bitmap.getWidth() * 0.25), (int) (bitmap.getHeight() * 0.25), true);
        bitmap = ThumbnailUtils.extractThumbnail(bitmap, 512, 512);


        int MAX_IMAGE_SIZE = 65 * 1024;
        int streamLength = MAX_IMAGE_SIZE;
        int compressQuality = 95;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        while (streamLength >= MAX_IMAGE_SIZE && compressQuality > 5) {
            try {
                stream.flush();
                stream.reset();
            } catch (IOException e) {
                e.printStackTrace();
            }
            compressQuality -= 5;
            bitmap.compress(Bitmap.CompressFormat.JPEG, compressQuality, stream);
            streamLength = stream.toByteArray().length;
            Log.i("compressQuality", String.valueOf(compressQuality));
            Log.i("Filezie", String.valueOf(streamLength));
        }
        String imgString = Base64.encodeToString(stream.toByteArray(),
                Base64.DEFAULT);

        return imgString;
    }

    public Bitmap convertStringToBM(String b64) {
        byte[] decodedString = Base64.decode(b64, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        return decodedByte;
    }
}