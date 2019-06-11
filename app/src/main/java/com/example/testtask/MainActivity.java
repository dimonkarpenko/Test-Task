package com.example.testtask;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    EditText search_et;
    Button search_btn;
    TextView display;
    ListView gitListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search_btn = (Button) findViewById(R.id.search_btn);
        search_et = (EditText) findViewById(R.id.search_et);
        display = (TextView) findViewById(R.id.display_url);


        gitListView = (ListView) findViewById(R.id.list_item);





        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getText = search_et.getText().toString();
                URL generateURL = Network.buildUrl(getText);

                display.setText(generateURL.toString());

                gitAsync gitTask = new gitAsync();

                gitTask.execute();


            }
        };

        search_btn.setOnClickListener(onClickListener);
    }

    class gitAsync extends AsyncTask<URL, Void, ArrayList<GitData>> {

        @Override
        protected ArrayList<GitData> doInBackground(URL... urls) {
            URL searchUrl = urls[0];
            String jsonResponse = "";

            ArrayList<GitData> gitData = new ArrayList<>();

            try {
                jsonResponse = Network.getResponseFromHttpUrl(searchUrl);

                JSONObject baseJsonResponse = new JSONObject(jsonResponse);
                JSONArray featureArray = baseJsonResponse.getJSONArray("items");

                for (int i = 0; i < featureArray.length(); i++) {
                    JSONObject currentGitData = featureArray.getJSONObject(i);

                    JSONObject properties = currentGitData.getJSONObject(String.valueOf(i));

                    String repoName = currentGitData.getString("name");
                    String descript = currentGitData.getString("description");
                    String forks = currentGitData.getString("forks");

                    GitData git = new GitData(repoName, descript, forks);

                    gitData.add(git);

                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


            return gitData;
        }

        @Override
        protected void onPostExecute(ArrayList<GitData> gitData) {

            ApiAdapter adapter = new ApiAdapter(MainActivity.this, gitData);
            gitListView.setAdapter(adapter);
        }
    }


}
