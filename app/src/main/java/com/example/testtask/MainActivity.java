package com.example.testtask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.net.URL;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    EditText search_et;
    Button search_btn;
    ImageView avatar_image;
    TextView repo_name;
    TextView desc;
    TextView number_of_forks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create a fake list of earthquake locations.
        ArrayList<GitApi> gitApiArrayList = new ArrayList<>();

        // Find a reference to the {@link ListView} in the layout
        ListView gitListView = (ListView) findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes
        GitAdapter adapter = new GitAdapter(this, gitApiArrayList);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        gitListView.setAdapter(adapter);


    }


}
