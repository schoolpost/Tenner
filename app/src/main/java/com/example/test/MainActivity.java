package com.example.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ElasticSearchRestClient elasticSearchRestClient = ElasticSearchRestClient.getInstance();

        User user = new User("n1@gmail.com", "L", "m", "4938884444");

        try{
//            elasticSearchRestClient.getAllUsers();
            Log.i("Hello", "World");
            elasticSearchRestClient.postUser(user);
        } catch (Exception e){

        }
    }
}
