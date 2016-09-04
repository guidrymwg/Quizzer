package com.lightcone.quizzer;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        AdapterView.OnItemSelectedListener{

    long quizSelected = 1;
    Spinner spinner;
    boolean quizStarted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Spinner to choose quiz
        spinner = (Spinner) findViewById(R.id.quiz_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout.
        // The array of choices is the array quiz_array defined in res/values/arrays.xml
        spinner.setDropDownWidth(600);
        // Add the listener for the Spinner options.  The methods onItemSelected
        // and onNothingSelected must be overriden to respond to the clicks on the spinner
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.quiz_array, android.R.layout.simple_spinner_item);
        // Specify the layout for the dropdown choice menu
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Attach the data adapter to the spinner
        spinner.setAdapter(adapter);


        // Create a floating action button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        // Add a click listener to the floating action button that launches a snackbar
        // and sets a listener on it to deal with clicks on the action button of the
        // snackbar.

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, R.string.snackText, Snackbar.LENGTH_INDEFINITE)
                        .setAction(R.string.snackButtonText, new View.OnClickListener() {
                            // Handle clicks on snackbar button
                            @Override
                            public void onClick(View v) {
                                Log.i("SNACK", "Snackbar button was clicked");
                            }
                        }).show();
            }
        });

        // Set up the navigation drawer

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.astro_quiz) {

           /* AstroQA.subjectIndex = 0;
            Intent i = new Intent(this, AstroQA.class);
            startActivity(i);*/

/*            // Handle the camera action
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            startActivity(intent);*/

        } else if (id == R.id.nav_call) {

            /*AstroQA.subjectIndex = 1;
            Intent i = new Intent(this, AstroQA.class);
            startActivity(i);*/

            /*String number = "202-456-1111";   // White House phone number
            String numberToDial = "tel:" + number;
            startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(numberToDial)));*/

        } else if (id == R.id.nav_slideshow) {

            // Will open to your google slides account if you have one, assuming
            // that you are logged into your Google account on the device.

            Intent markerIntent = new Intent(Intent.ACTION_VIEW);
            markerIntent.setData(Uri.parse("https://docs.google.com/presentation/u/0/"));
            startActivity(markerIntent);

            // This will only work if dropbox app is installed
            /*Intent intent = getPackageManager().getLaunchIntentForPackage(
                    "com.dropbox.android");
            startActivity(intent);*/

            /*Intent markerIntent=new Intent(Intent.ACTION_VIEW);
            markerIntent.setData(Uri.parse("https://www.dropbox.com/login"));
            startActivity(markerIntent);*/

        } else if (id == R.id.nav_drive) {

            // Will open to your google drive account if you have one
            Intent markerIntent = new Intent(Intent.ACTION_VIEW);
            markerIntent.setData(Uri.parse("https://drive.google.com/drive"));
            startActivity(markerIntent);

        } else if (id == R.id.nav_share) {

            // Will open to my Google+ homepage.  Change gid to your G+ id
            // to access your G+ page.  To get your id, open Google+ with a
            // browser and click Profile.  The long number displayed as part
            // of the URL in the address bar is your G+ id.

            String gid = "101802627488828432585";
            String add = "https://plus.google.com/" + gid + "/posts";
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(add)));

        } else if (id == R.id.nav_play) {

            // Open the Play Store

            Intent markerIntent = new Intent(Intent.ACTION_VIEW);
            markerIntent.setData(Uri.parse("https://play.google.com/store"));
            startActivity(markerIntent);
        }

        // Close the navigation drawer and return true indicating that the event
        // has been handled.

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // Callback method to be invoked when an item in this view has been selected. This callback
    // is invoked only when the newly selected position is different from the previously selected
    // position or if there was no selected item. Call getItemAtPosition(position) to access the
    // data associated with the selected item.

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        quizSelected = parent.getItemIdAtPosition(position);
        Log.i("QZ","Selected="+quizSelected);
        //if(quizStarted) {
            AstroQA.subjectIndex = (int) quizSelected;
            startActivity(new Intent(this, AstroQA.class));
        //}
        quizStarted = true;
    }

    // Callback method to be invoked when the selection disappears from this view. The selection
    // can disappear for instance when touch is activated or when the adapter becomes empty.

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Log.i("QZ","No selection");
    }

    @Override
    protected void onPause() {
        super.onPause();
        //finish();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    // Note that this one is not a lifecycle method
    @Override
    protected void onSaveInstanceState (Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    // Note that this one is not a lifecycle method
    @Override
    protected void onRestoreInstanceState (Bundle inState) {
        super.onRestoreInstanceState(inState);
    }

}
