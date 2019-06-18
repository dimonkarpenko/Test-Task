package com.example.testtask;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


class ApiAdapter extends ArrayAdapter<GitData> {


    public ApiAdapter(MainActivity activity, ArrayList<GitData> gitData) {
        super(activity, 0, gitData);
    }


    public View getView(int position, View convertView, ViewGroup parent) {

        View listView = convertView;

        if (listView == null) {
            listView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        GitData currentData = getItem(position);

        Picasso.get().load(currentData.getThumbnail()).into((ImageView) listView.findViewById(R.id.avatar_image));

        TextView repo_name = (TextView) listView.findViewById(R.id.repo_name);
        repo_name.setText(currentData.getName());

        TextView description_tv = (TextView) listView.findViewById(R.id.description_tv);
        description_tv.setText(currentData.getDesc());

        TextView number_of_forks = (TextView) listView.findViewById(R.id.number_of_forks);
        number_of_forks.setText(currentData.getForks());

        return listView;
    }
}
