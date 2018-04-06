package cmput301w18t22.com.tenner.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cmput301w18t22.com.tenner.Helpers.PhotoConverterHelper;
import cmput301w18t22.com.tenner.R;
import cmput301w18t22.com.tenner.classes.User;
import cmput301w18t22.com.tenner.server.ElasticServer;
import cmput301w18t22.com.tenner.utils.Authenticator;
import cmput301w18t22.com.tenner.utils.LocalDataHandler;

public class EditProfileActivity extends AppCompatActivity {

    private TextView tvEmail;
    private EditText etFirst;
    private EditText etLast;
    private EditText etPhone;
    private TextView save;

    private User user;
    private LocalDataHandler localDataHandler;

    //Photo
    ImageView mImageView;
    String mCurrentPhotoPath;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int GET_FROM_GALLERY = 3;
    String b64;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        localDataHandler = new LocalDataHandler(this);

        // Change ActionBar
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setElevation(3);
        getSupportActionBar().setCustomView(R.layout.toolbar_edit_profile);
        save = getSupportActionBar().getCustomView().findViewById(R.id.toolbar_edit_save);

        tvEmail = (TextView) findViewById(R.id.edit_email);
        etFirst = (EditText) findViewById(R.id.edit_firstname);
        etLast = (EditText) findViewById(R.id.edit_lastname);
        etPhone = (EditText) findViewById(R.id.edit_phone);

        //Photo
        mImageView = (ImageView) findViewById(R.id.editProfileImage);

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePic();
            }
        });

        loadData();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                applyChanges();
            }
        });


    }

    private void loadData() {
        user = localDataHandler.loadUserFromFile();

        etFirst.setText(user.getFirstName());
        etLast.setText(user.getLastName());
        tvEmail.setText(user.getEmail());
        etPhone.setText(user.getPhoneNum());

        //check if user has a profile picture
        if (user.getPhoto() == null) {
            mImageView.setImageResource(R.drawable.user_pic);
        } else {
            mImageView.setImageBitmap(new PhotoConverterHelper().convertStringToBM(user.getPhoto()));
        }

    }

    boolean check(String first, String last, String phone) {
        Authenticator a = new Authenticator();

        if (!a.checkName(first)) {
            etFirst.setError("invalid first name");
            return false;
        }
        if (!a.checkName(last)) {
            etLast.setError("invalid last name");
            return false;
        }
        if (!a.checkPhoneNum(phone)) {
            etPhone.setError("invalid phone number");
            return false;
        }
        return true;
    }

    public void applyChanges() {

        String firstName = String.valueOf(etFirst.getText()).trim();
        String lastName = String.valueOf(etLast.getText()).trim();
        String phone = String.valueOf(etPhone.getText()).trim();
        String photo = b64;

        if (check(firstName, lastName, phone)) {
            if (TextUtils.isEmpty(firstName)) {
                firstName = user.getFirstName();
            }
            if (TextUtils.isEmpty(lastName)) {
                lastName = user.getLastName();
            }
            if (TextUtils.isEmpty(firstName)) {
                phone = user.toDisplayPhone();
            }

            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPhoneNum(phone);
            user.setPhoto(photo);

            // post the edited user
            try {
                postEditUser(user);
            } catch (Exception e) {

            }

        }

    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                Log.i("Error", "File Image Creation Error!");
            }
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    public void takePic() {
        dispatchTakePictureIntent();
    }

    public void addPic() {
        addPicFromGallery();
    }

    private void addPicFromGallery() {
        startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);

            PhotoConverterHelper photoConverter = new PhotoConverterHelper();
            b64 = photoConverter.convertBMToString(bitmap);

            Bitmap bm = photoConverter.convertStringToBM(b64);

            mImageView.setImageBitmap(bm);
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
    }

    //TODO - Fix???
//    private void galleryAddPic() {
//        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
//        File f = new File(mCurrentPhotoPath);
//        Uri contentUri = Uri.fromFile(f);
//        mediaScanIntent.setData(contentUri);
//        this.sendBroadcast(mediaScanIntent);
//    }

    public void postEditUser(final User postUser) throws JSONException {

        RequestParams params = new RequestParams();
        Gson gson = new Gson();
        String json = gson.toJson(postUser);

        try {
            params.put("user", json);
        } catch (Exception e) {

        }

        ElasticServer.RestClient.post("editUser", params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    localDataHandler.saveUserInFile(postUser);
                    finish();
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
    }
}