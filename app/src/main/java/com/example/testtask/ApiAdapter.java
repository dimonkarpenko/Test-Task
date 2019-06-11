package com.example.testtask;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

class ApiAdapter extends ArrayAdapter<GitData> {

    public ApiAdapter(Activity activity, ArrayList<GitData> gitData) {
        super(activity, 0, gitData);
    }


    public View getView(int position, View convertView, ViewGroup parent) {

        View listView = convertView;

        if (listView == null) {
            listView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        GitData currentData = getItem(position);

        TextView repo_name = (TextView) listView.findViewById(R.id.repo_name);
        repo_name.setText(currentData.getName());

        TextView description_tv = (TextView) listView.findViewById(R.id.description_tv);
        description_tv.setText(currentData.getDesc());

        TextView number_of_forks = (TextView) listView.findViewById(R.id.number_of_forks);
        number_of_forks.setText(currentData.getForks());


        return listView;
    }

}
