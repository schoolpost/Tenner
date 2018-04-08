package cmput301w18t22.com.tenner.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

import cmput301w18t22.com.tenner.R;
import cmput301w18t22.com.tenner.classes.Location;
import cmput301w18t22.com.tenner.classes.Task;
import cmput301w18t22.com.tenner.classes.User;
import cmput301w18t22.com.tenner.helpers.LocalDataHelper;
import cmput301w18t22.com.tenner.helpers.PhotoConverterHelper;
import cmput301w18t22.com.tenner.helpers.TaskCheckerHelper;
import cmput301w18t22.com.tenner.server.ElasticServer;

public class EditTaskActivity extends AppCompatActivity {

    private EditText etTitle;
    private EditText etDescription;
    private EditText etLocation;
    private TextView save;
    private Button setLocationButton;
    private FusedLocationProviderClient mFusedLocationClient;
    private LatLng currentloc;
    private LocalDataHelper localDataHelper;
    private Location taskLocation;
    private Task task;
    private Task taskOld;
    private User user;

    //Photo
    String mCurrentPhotoPath;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int GET_FROM_GALLERY = 3;
    int imagepos;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ArrayList<String> b64photos;
    static final int GET_LOCATION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_task);


        localDataHelper = new LocalDataHelper(this);
        task = localDataHelper.getTaskFromFile();
        taskOld = task;
        user = localDataHelper.loadUserFromFile();

        b64photos = new ArrayList<>();
        b64photos = taskOld.getPhotos();

        imageView1 = (ImageView) findViewById(R.id.addTaskImage1);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent(0);
                imagepos = 0;
                Log.i("clicked", "one");
            }
        });
        imageView2 = (ImageView) findViewById(R.id.addTaskImage2);
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent(1);
                imagepos = 1;
                Log.i("clicked", "one");
            }
        });
        imageView3 = (ImageView) findViewById(R.id.addTaskImage3);
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent(2);
                imagepos = 2;
                Log.i("clicked", "one");
            }
        });

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
        taskLocation = task.getLocation();

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
                mapIntent.putExtra("maptype", "setmap");
                mapIntent.setClass(getApplicationContext(), MapActivity.class);
                //TODO: Get result of this activity
                startActivityForResult(mapIntent, GET_LOCATION);
            }
        });


        etTitle.setText(task.getTitle());
        etDescription.setText(task.getDescription());
        etLocation.setText(task.getLocation().getAddress());

        PhotoConverterHelper photoConverterHelper = new PhotoConverterHelper();

        for (int i = 0; i < task.getPhotos().size(); i++) {
            Bitmap bm = photoConverterHelper.convertStringToBM(task.getPhotos().get(i));
            switch (i) {
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


    }


    private void tryPost() {
        String title = String.valueOf(etTitle.getText()).trim();
        String description = String.valueOf(etDescription.getText()).trim();
        String address = String.valueOf(etLocation.getText()).trim();


        if (check(title, description, address)) {
            //TODO: Current User and Elastic Search

            User user = localDataHelper.loadUserFromFile();
            User currentUser = user;
            if (taskLocation == null) {
                taskLocation = new Location(0, 0, "");
            }
            Task task = new Task(title, description, taskLocation, new Date(), currentUser);
            task.setPhotos(b64photos);

            try {

                if (task.getTitle().equals(taskOld.getTitle())) {
                    postTask(task);
                } else {
                    deleteTask();
                    postTask(task);
                }
            } catch (Exception e) {

            }

        }

    }

    private boolean check(String title, String description, String address) {
        TaskCheckerHelper ch = new TaskCheckerHelper();
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

    public void deleteTask() {


        RequestParams params = new RequestParams();

        try {
            params.put("user", user.getEmail());
            params.put("title", taskOld.getTitle());
        } catch (Exception e) {

        }

        ElasticServer.RestClient.post("deleteTask", params, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {

                    if (response.has("Success")) {

                    }

                    if (response.has("Error")) {

                    }

                } catch (Exception e) {

                }
            }
        });
    }


    public void postTask(final Task task) throws JSONException {

        RequestParams params = new RequestParams();
        //https://github.com/google/gson
        Gson gson = new Gson();
        String json = gson.toJson(task);

        try {
            params.put("task", json);
        } catch (Exception e) {

        }

        ElasticServer.RestClient.post("editTask", params, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    if (response.has("Success")) {

                        localDataHelper.saveTaskToFile(task);
                        finish();

                    } else if (response.has("Error")) {

                        etTitle.setError("Task already exists!");
                        Toast toast = Toast.makeText(getApplicationContext(), response.get("Error").toString(), Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 16);
                        toast.show();
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
//                takePictureIntent.putExtra("imagepos", String.valueOf(imagePos));
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

    private void addPicFromGallery() {
        startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);

            PhotoConverterHelper photoConverter = new PhotoConverterHelper();

            int index = imagepos;

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
            taskLocation = new Location(data.getFloatExtra("lat", 0),
                    data.getFloatExtra("lng", 0),
                    data.getStringExtra("location"));
            etLocation.setText(data.getStringExtra("location"));
        }
    }

}


