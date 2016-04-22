package com.stufit.stufit;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Classtt extends AppCompatActivity {

    ListView timeTableList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classtt);

        //create(time_list, subjects_list);
    }

    void create(String[] time_list ,String[] subjects_list) {
        timeTableList = (ListView) findViewById(R.id.classtt_list);
        myAdapter adapter = new myAdapter(this, time_list, subjects_list);
        timeTableList.setAdapter(adapter);
    }

    class myAdapter extends ArrayAdapter<String> {
        Context context;
        String[] time;
        String[] subjectsName;

        myAdapter(Context context, String[] time, String[] subjectsName) {
            super(context, R.layout.item_layout, R.id.time, time);
            this.context = context;
            this.time = time;
            this.subjectsName = subjectsName;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.item_layout, parent, false);

            TextView tv1 = (TextView) row.findViewById(R.id.time);
            TextView tv2 = (TextView) row.findViewById(R.id.subjects);
            tv1.setText(time[position]);
            tv2.setText(subjectsName[position]);
            return row;
        }
    }
}
