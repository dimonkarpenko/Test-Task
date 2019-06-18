package com.example.testtask;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {

    EditText search_et;
    Button search_btn;
    TextView display;
    ListView gitListView;
    URL generateURL;
    JSONArray items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search_btn = (Button) findViewById(R.id.search_btn);
        search_et = (EditText) findViewById(R.id.search_et);
        display = (TextView) findViewById(R.id.display_url);


        gitListView = (ListView) findViewById(R.id.list);



        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getText = search_et.getText().toString();
                generateURL = Network.buildUrl(getText);

                display.setText(generateURL.toString());

                new gitAsync().execute(generateURL);


            }
        };

        search_btn.setOnClickListener(onClickListener);


    }

    class gitAsync extends AsyncTask<URL, Void, Void> {

        @Override
        protected Void doInBackground(URL... urls) {
            URL searchUrl = urls[0];
            String gitSearchUrl = null;

            try {
                gitSearchUrl = Network.getResponseFromHttpUrl(searchUrl);

                JSONObject baseJsonResponse = (JSONObject) new JSONTokener(gitSearchUrl).nextValue();
                items = baseJsonResponse.getJSONArray("items");

            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            final ArrayList<GitData> gitData = new ArrayList<>();

            if(items.length() == 0){
                return;
            }

            try {
                for (int i = 0; i < items.length(); i++) {
                    JSONObject currentGitData = items.getJSONObject(i);

                    String repoName = currentGitData.getString("name");
                    String descript = currentGitData.getString("description");
                    String forks = currentGitData.getString("forks");
                    String link = currentGitData.getString("html_url");

                    JSONObject owner = currentGitData.getJSONObject("owner");
                    String thumbnail = owner.getString("avatar_url");

                    GitData git = new GitData(repoName, descript, forks, thumbnail, link);

                    gitData.add(git);

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            ApiAdapter adapter = new ApiAdapter(MainActivity.this, gitData);

            gitListView.setAdapter(adapter);

            gitListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    GitData current = gitData.get(position);
                    Uri gitUri = Uri.parse(current.getLink());
                    Intent websiteIntent = new Intent(Intent.ACTION_VIEW, gitUri);
                    startActivity(websiteIntent);
                }
            });
        }
    }

}
