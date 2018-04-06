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