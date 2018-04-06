package cmput301w18t22.com.tenner.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import cmput301w18t22.com.tenner.Helpers.PhotoConverterHelper;
import cmput301w18t22.com.tenner.R;
import cmput301w18t22.com.tenner.classes.Location;
import cmput301w18t22.com.tenner.classes.User;
import cmput301w18t22.com.tenner.classes.Task;
import cmput301w18t22.com.tenner.server.ElasticServer;
import cmput301w18t22.com.tenner.utils.LocalDataHandler;
import cmput301w18t22.com.tenner.utils.TaskChecker;

public class PostTaskActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etTitle;
    private EditText etDescription;
    private EditText etLocation;
    private TextView save;
    private Button setLocationButton;
    private FusedLocationProviderClient mFusedLocationClient;
    private LatLng currentloc;
    private LocalDataHandler localDataHandler;

    //Photo
    ImageView mImageView;
    String mCurrentPhotoPath;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int GET_FROM_GALLERY = 3;
    String b64;
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private ArrayList<String> b64photos;


    static final int GET_LOCATION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        localDataHandler = new LocalDataHandler(this);
        b64photos = new ArrayList<>();
        imageView1 = (ImageView) findViewById(R.id.addTaskImage1);
        imageView2 = (ImageView) findViewById(R.id.addTaskImage2);
        imageView3 = (ImageView) findViewById(R.id.addTaskImage3);


        setContentView(R.layout.activity_post_task);

        setLocationButton = (Button) findViewById(R.id.location_button);
        setLocationButton.setEnabled(false);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        getcurrloc();

        // Change ActionBar
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setElevation(3);
        getSupportActionBar().setCustomView(R.layout.toolbar_post_task);
        save = getSupportActionBar().getCustomView().findViewById(R.id.toolbar_edit_save);

        etTitle = (EditText) findViewById(R.id.editTitle);
        etDescription = (EditText) findViewById(R.id.editDescription);
        etLocation = (EditText) findViewById(R.id.editLocation);
        etLocation.setEnabled(false);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tryPost();
            }
        });

        setLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mapIntent = new Intent();
                mapIntent.putExtra("lat", currentloc.latitude);
                mapIntent.putExtra("lng", currentloc.longitude);
                mapIntent.setClass(PostTaskActivity.this, SetLocationActivity.class);
                //TODO: Get result of this activity
                startActivityForResult(mapIntent, GET_LOCATION);
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.addTaskImage1) {
            dispatchTakePictureIntent(0);
        }
        if (v.getId() == R.id.addTaskImage3) {
            dispatchTakePictureIntent(1);
        }
        if (v.getId() == R.id.addTaskImage3) {
            dispatchTakePictureIntent(2);
        }
    }


    private void tryPost() {
        String title = String.valueOf(etTitle.getText()).trim();
        String description = String.valueOf(etDescription.getText()).trim();
        String address = String.valueOf(etLocation.getText()).trim();

        if (check(title, description, address)) {
            //TODO: Current User and Elastic Search

            User user = localDataHandler.loadUserFromFile();
            User currentUser = user;
            Location newLocation = new Location(0.0f, 0.0f, address);
            Task task = new Task(title, description, newLocation, new Date(), currentUser);

            try {
                postTask(task);
            } catch (Exception e) {

            }

        }

    }

    private boolean check(String title, String description, String address) {
        TaskChecker ch = new TaskChecker();
        if (TextUtils.isEmpty(title)) {
            etTitle.setError("title is empty");
            return false;
        }

        if (!ch.checkTitle(title)) {
            etTitle.setError("invalid title");
            return false;
        }

        if (!ch.checkDuplicateTitle(title)) {
            etTitle.setError("duplicate title");
            return false;
        }

        if (TextUtils.isEmpty(description)) {
            etDescription.setError("description is empty");
            return false;
        }

        if (!ch.checkDescription(description)) {
            etDescription.setError("invalid description");
            return false;
        }

        if (!ch.checkAddress(address)) {
            etLocation.setError("invalid address");
            return false;
        }
        return true;
    }

    public void postTask(Task task) throws JSONException {

        RequestParams params = new RequestParams();
        //https://github.com/google/gson
        Gson gson = new Gson();
        String json = gson.toJson(task);

        try {
            params.put("task", json);
        } catch (Exception e) {

        }

        ElasticServer.RestClient.post("addTask", params, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    if (response.has("Success")) {

                        Intent intent = new Intent();
                        intent.setClass(PostTaskActivity.this, TaskDetailActivity.class);
                        startActivity(intent);
                        finish();


                    } else if (response.has("Error")) {

                    }
                } catch (Exception e) {

                }
            }
        });
    }

    public void getcurrloc() throws SecurityException {
        mFusedLocationClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<android.location.Location>() {
            @Override
            public void onSuccess(android.location.Location location) {

                if (location != null) {

                    currentloc = new LatLng(location.getLatitude(), location.getLongitude());
                    setLocationButton.setEnabled(true);

                }
            }
        });
    }

    private void dispatchTakePictureIntent(int imagePos) {
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
                takePictureIntent.putExtra("imagepos", String.valueOf(imagePos));
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

//    public void takePic() {
//        dispatchTakePictureIntent();
//    }

//    public void addPic() {
//        addPicFromGallery();
//    }

    private void addPicFromGallery() {
        startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);

            PhotoConverterHelper photoConverter = new PhotoConverterHelper();

            int index = data.getIntExtra("imagepos", -1);
            b64photos.add(index, photoConverter.convertBMToString(bitmap));

            Bitmap bm = photoConverter.convertStringToBM(b64photos.get(index));

            switch (index) {
                case 0:
                    imageView1.setImageBitmap(bm);
                    break;
                case 1:
                    imageView2.setImageBitmap(bm);
                    break;
                case 2:
                    imageView3.setImageBitmap(bm);
                    break;
            }
        }
        if (resultCode == 20) {
            etLocation.setText(data.getStringExtra("location"));
        }
    }

}
