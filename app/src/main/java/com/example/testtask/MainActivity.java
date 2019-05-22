package com.example.testtask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private EditText search_et;
    private Button search_btn;
    private ImageView avatar_image;
    private TextView repo_name;
    private TextView desc;
    private TextView number_of_forks;
    TextView url_display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search_et = (EditText) findViewById(R.id.search_et);
        search_btn = (Button) findViewById(R.id.search_btn);
        avatar_image = (ImageView) findViewById(R.id.avatar_image);
        repo_name = (TextView) findViewById(R.id.repo_name);
        desc = (TextView) findViewById(R.id.description_tv);
        number_of_forks = (TextView) findViewById(R.id.number_of_forks);
        url_display = (TextView) findViewById(R.id.url_display);
    }

    private void makeGithubSearchQuery() {
        String githubQuery = search_et.getText().toString();
        URL githubSearchUrl = Network.buildUrl(githubQuery);
        url_display.setText(githubSearchUrl.toString());
        String githubResults = null;
        new githubQueryTask().execute(githubSearchUrl);
    }

    public class githubQueryTask extends AsyncTask<URL, Void, String> {


        @Override
        protected String doInBackground(URL... urls) {
            URL searchUrl = urls[0];
            String githubSearchResults = null;
            try {
                githubSearchResults = Network.getResponseFromHttpUrl(searchUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return githubSearchResults;
        }

        protected void onPostExecute(String s) {
            if (s != null && !s.equals("")) {
                url_display.setText(s);
            }
        }
    }
}
