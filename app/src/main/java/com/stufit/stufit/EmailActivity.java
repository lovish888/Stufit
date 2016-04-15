package com.stufit.stufit;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class EmailActivity extends AppCompatActivity {
    ListView emailList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        createList();
        handleListItemClick(emailList);
    }

    public void createList(){
        String[] email_list = getResources().getStringArray(R.array.Email_to);
        emailList = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,email_list);
        emailList.setAdapter(adapter);
    }

    public void handleListItemClick(ListView emailList) {
        emailList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent email = new Intent(Intent.ACTION_SEND);
                email.setData(Uri.parse("mailto:"));
                String to = getStrings(i);
                email.putExtra(Intent.EXTRA_EMAIL, to);
            }
        });
    }

    public String getStrings(int i){
        String[] emailLinks = {"lovish79214@gmail.com"};
        return emailLinks[i];
    }
}
