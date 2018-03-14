package cmput301w18t22.com.tenner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MyTasksActivity extends AppCompatActivity {

    private TaskAdapter myadapter;
    private enum currentView {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tasks);
    }

    public void deleteTask(){
    }

    private void viewRequestedTasks(){
    }

    private void viewProvidedTasks(){
    }

    private void moveToTaskDetailsActivity(){
    }
}
