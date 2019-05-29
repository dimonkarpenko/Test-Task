package com.example.testtask;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class GitAdapter extends ArrayAdapter<GitApi> {

    public GitAdapter(Context context, ArrayList<GitApi> gitApiArrayList) {
        super(context, 0, gitApiArrayList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listView = convertView;

        if (listView == null) {
            listView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        GitApi currentPosition = getItem(position);



        return listView;
    }
}
