package cmput301w18t22.com.tenner.Helpers;

public class PhotoConverter {

    private static final PhotoConverter ourInstance = new PhotoConverter();

    static PhotoConverter getInstance() {
        return ourInstance;
    }

    public PhotoConverter() {
    }

    //https://developer.android.com/training/camera/photobasics.html#TaskScalePhoto
    //https://stackoverflow.com/questions/10513976/how-to-convert-image-into-byte-array-and-byte-array-to-base64-string-in-android
    //https://stackoverflow.com/questions/9107900/how-to-upload-image-from-gallery-in-android
    //https://stackoverflow.com/questions/4837110/how-to-convert-a-base64-string-into-a-bitmap-image-to-show-it-in-a-imageview


//    private void setPic() {
//        // Get the dimensions of the View
//        int targetW = mImageView.getWidth();
//        int targetH = mImageView.getHeight();
//
//        // Get the dimensions of the bitmap
//        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
//        bmOptions.inJustDecodeBounds = true;
//        BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
//        int photoW = bmOptions.outWidth;
//        int photoH = bmOptions.outHeight;
//
//        // Determine how much to scale down the image
//        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);
//
//        // Decode the image file into a Bitmap sized to fill the View
//        bmOptions.inJustDecodeBounds = false;
//        bmOptions.inSampleSize = scaleFactor;
//        bmOptions.inPurgeable = true;
//
//        Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
//        mImageView.setImageBitmap(bitmap);
//    }

//    private void getLogoImage(){
//        ImageView mImageView = findViewById(R.id.imageView);
//        File f = new File(mCurrentPhotoPath);
//        Uri contentUri = Uri.fromFile(f);
//        try {
//            URL imageUrl = new URL(mCurrentPhotoPath);
//            URLConnection ucon = imageUrl.openConnection();
//
//            InputStream is = ucon.getInputStream();
//            BufferedInputStream bis = new BufferedInputStream(is);
//
//            ByteArrayOutputStream fos = new ByteArrayOutputStream(4000);
//            int current = 0;
//            while ((current = bis.read()) != -1) {
//                fos.write(current);
//            }
//            byte[] byteArr = fos.toByteArray();
//            Bitmap bitmap = BitmapFactory.decodeByteArray(byteArr, 0, byteArr.length);
//            mImageView.setImageBitmap(bitmap);
//        } catch (Exception e) {
//            Log.d("ImageManager", "Error: " + e.toString());
//        }
//    }
}