package com.stufit.stufit;

import android.app.LocalActivityManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.Toast;

public class second extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private TabHost tabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        createDrawerLayout(toolbar);
        LocalActivityManager mLocalActivityManager = manageNavigation(savedInstanceState);
        manageTabhost(mLocalActivityManager);
    }

    private void manageTabhost(LocalActivityManager mLocalActivityManager) {
        tabHost = (TabHost)findViewById(R.id.tabhost);
        tabHost.setup(mLocalActivityManager);

        TabHost.TabSpec tab1 = tabHost.newTabSpec("First Tab");
        TabHost.TabSpec tab2 = tabHost.newTabSpec("Second Tab");
        TabHost.TabSpec tab3 = tabHost.newTabSpec("Third Tab");

        tab3.setIndicator("Class tt");//Name of the Tab.
        tab3.setContent(new Intent(this, Classtt.class));
        tab2.setIndicator("Email");//Name of the Tab.
        tab2.setContent(new Intent(this, EmailActivity.class));
        tab1.setIndicator("Daily Summary");
        tab1.setContent(new Intent(this, Summary.class));

        tabHost.addTab(tab1);
        tabHost.addTab(tab2);
        tabHost.addTab(tab3);
    }

    @NonNull
    private LocalActivityManager manageNavigation(Bundle savedInstanceState) {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        LocalActivityManager mLocalActivityManager = new LocalActivityManager(this, false);
        mLocalActivityManager.dispatchCreate(savedInstanceState); // state will be bundle your activity state which you get in onCreate
        return mLocalActivityManager;
    }

    private void createDrawerLayout(Toolbar toolbar) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }

    //Exit the Activity on pressing back button twice.

    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce=false;
                }
            }, 1000);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.second, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.favorites) {
            Toast.makeText(second.this, "Feature Coming Soon", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.rate_us) {
            Intent market = new Intent(Intent.ACTION_VIEW);
            market.setData(Uri.parse("market://details?id=xyz.wheretopark.tabtest&hl=en"));
            startActivity(Intent.createChooser(market,"Launch Market"));

        }

        else if (id == R.id.share) {
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String shareBody = "I am using this awesome android app.";
            sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody +"\nDownload: https://play.google.com/store/apps/details?id=xyz.wheretopark.tabtest&hl=en");
            startActivity(Intent.createChooser(sharingIntent, "Share via"));


        } else if (id == R.id.contact_us) {
            Intent contact = new Intent(Intent.ACTION_SEND);
            contact.setData(Uri.parse("mailto:"));
            String[] to = {"hello@wheretopark.xyz"};
            contact.putExtra(Intent.EXTRA_EMAIL, to);

            // Name of specification for email.
            contact.setType("message/rfc822"); //To handle the putExtra methods. MIME type of the email. App will crash otherwise.
            startActivity(Intent.createChooser(contact, "Send Email"));

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
