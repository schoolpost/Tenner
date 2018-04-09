package cmput301w18t22.com.tenner;

/**
 * Created by Schoolpost on 2018-04-06.
 */

public class ArchiveCode {
}



//TODO - Scale Test
//        else if(requestCode == GET_FROM_GALLERY && resultCode == RESULT_OK) {
//            Uri selectedImage = data.getData();
//            Bitmap bitmap = null;
//
//            try {
//                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
//                mImageView.setImageBitmap(bitmap);
//            } catch (FileNotFoundException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }



//TODO - Fix???
//    private void galleryAddPic() {
//        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
//        File f = new File(mCurrentPhotoPath);
//        Uri contentUri = Uri.fromFile(f);
//        mediaScanIntent.setData(contentUri);
//        this.sendBroadcast(mediaScanIntent);
//    }

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