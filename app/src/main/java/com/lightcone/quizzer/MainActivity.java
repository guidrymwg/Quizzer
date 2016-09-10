package com.lightcone.quizzer;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //long quizSelected = -1;
    Context context;
    static final String TAG="QUIZZER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = getBaseContext();

        // Create a floating action button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        // Add a click listener to the floating action button

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processMenu();
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

        if (id == R.id.astronomy) {

            Log.i(TAG,"Astronomy nav drawer");

        }

        else if (id == R.id.history) {

            Log.i(TAG,"History nav drawer");

        }

        else if (id == R.id.mathematics) {

            Log.i(TAG,"Math nav drawer");

        }

        else if (id == R.id.geography) {

            Log.i(TAG,"Geography nav drawer");

        }

        else if (id == R.id.biology) {

            Log.i(TAG,"Biology nav drawer");

        }

        else if (id == R.id.nav_share) {

            // Will open to my Google+ homepage.  Change gid to your G+ id
            // to access your G+ page.  To get your id, open Google+ with a
            // browser and click Profile.  The long number displayed as part
            // of the URL in the address bar is your G+ id.

            String gid = "101802627488828432585";
            String add = "https://plus.google.com/" + gid + "/posts";
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(add)));

        }

        else if (id == R.id.nav_play) {

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

    // Open AlertDialog holding quiz subject options menu and process with anonymous inner class
    private void processMenu(){
        new AlertDialog.Builder(this).setTitle(R.string.choose_subject)
                .setItems(R.array.quiz_array,
                        new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialoginterface, int i){
                                AstroQA.subjectIndex = i;
                                startActivity(new Intent(context, AstroQA.class));
                                //doMenu(i);
                            }
                        }).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("QZ", "Pausing");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("QZ", "onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("QZ", "Restarting");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("QZ", "Stopping");
    }

    // Note that this one is not a lifecycle method
    @Override
    protected void onSaveInstanceState (Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i("QZ", "Saving instance state");
    }

    // Note that this one is not a lifecycle method
    @Override
    protected void onRestoreInstanceState (Bundle inState) {
        super.onRestoreInstanceState(inState);
        Log.i("QZ", "Restoring instance state");
    }

}
