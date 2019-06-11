package com.example.testtask;

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
    private URL generateURL;

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
                generateURL = Network.buildUrl(getText);

                display.setText(generateURL.toString());

                gitAsync gitTask = new gitAsync();

                gitTask.execute();


            }
        };

        search_btn.setOnClickListener(onClickListener);
    }

    class gitAsync extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {
            URL searchUrl = urls[0];
            String gitSearchUrl = null;

            try {
                gitSearchUrl = Network.getResponseFromHttpUrl(searchUrl);

            } catch (IOException e1) {
                e1.printStackTrace();
            }

            return gitSearchUrl;
        }

        @Override
        protected void onPostExecute(String s) {

            ArrayList<GitData> gitData = new ArrayList<>();

            try {
                JSONObject baseJsonResponse = new JSONObject(String.valueOf(generateURL));
                JSONArray featureArray = baseJsonResponse.getJSONArray("items");

                for (int i = 0; i < featureArray.length(); i++) {
                    JSONObject currentGitData = featureArray.getJSONObject(i);

                    String repoName = currentGitData.getString("name");
                    String descript = currentGitData.getString("description");
                    String forks = currentGitData.getString("forks");

                    GitData git = new GitData(repoName, descript, forks);

                    gitData.add(git);

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            ApiAdapter adapter = new ApiAdapter(MainActivity.this, gitData);
            gitListView.setAdapter(adapter);
        }
    }
}
