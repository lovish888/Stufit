package com.stufit.stufit;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class Summary extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextView summary;
    mongodatabase mdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        summary = (TextView) findViewById(R.id.summary);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);

         mdb = new mongodatabase();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.subjects_summary, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0) {
            summary.setText("COA");
        } else if (position == 1) {
            summary.setText("OOPS");
        } else if (position == 2) {
            summary.setText("POC");
        } else if (position == 3) {
            summary.setText("SSC");
        } else if (position == 4) {
            summary.setText("IPSY");
        } else if (position == 5) {
            summary.setText("DBMS");
        } else {
            summary.setText("DAA");
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
