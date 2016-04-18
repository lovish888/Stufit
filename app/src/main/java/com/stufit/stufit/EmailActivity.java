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
                int text = getEmailText(i);
                email.putExtra(Intent.EXTRA_EMAIL, to);
                email.putExtra(Intent.EXTRA_TEXT,text);
            }
        });
    }

    public String getStrings(int i){
        String[] emailLinks = {"sdangi724@gmail.com","shubhamv1204@gmail.com","niraj.lnmiit@gmail.com","vipul.viki@gmail.com","lostandfound.lnmiit@gmail.com" +
                "lostandfound.lnmiit@gmail.com","sdgupta.gupta9@gmail.com","hostel@lnmiit.ac.in"};
        return emailLinks[i];
    }
    public int getEmailText(int i){
        int[] emailText = {R.string.president_email,R.string.gsec_sports,R.string.gsec_cultural,R.string.gsec_scienceandtech,R.string.lostandfound,R.string.rfid_lost,R.string.vice_president,R.string.hostelaffairs};
        return emailText[i];
    }
}
