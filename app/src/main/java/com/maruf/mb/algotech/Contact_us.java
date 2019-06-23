package com.maruf.mb.algotech;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.maruf.mb.algotech.Commonly_used_class.All_Menu_Class;
import com.maruf.mb.algotech.Read_data.Session;
import com.maruf.mb.algotech.Commonly_used_class.ExampleDialog;

import static maes.tech.intentanim.CustomIntent.customType;

public class Contact_us extends AppCompatActivity

        implements NavigationView.OnNavigationItemSelectedListener {
    private RelativeLayout callLayout, mailLayout, facebookLayout, twitterLayout, youtubeLayout, linkinLayout;
    //private TextView mTextViewMail,mTextViewCall,mTextViewFacebook,mTextViewTwitter,mTextViewYoutube,mTextViewLinkIn;
    private static final int REQUEST_CALL = 1;
    Context context;
    Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        session=new Session(this);

        customType(Contact_us.this,"left-to-right");
        /**left-to-right
         *right-to-left
         *bottom-to-up
         *up-to-bottom
         *fadein-to-fadeout
         *rotateout-to-rotatein
         * */

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.header_user_emailID);
        navUsername.setVisibility(View.GONE);
        navigationView.setNavigationItemSelectedListener(this);


        callLayout = (RelativeLayout) findViewById(R.id.contact_call_Layout);
        mailLayout = (RelativeLayout) findViewById(R.id.contact_email_Layout);
        facebookLayout = (RelativeLayout) findViewById(R.id.contact_fb_Layout);
        twitterLayout = (RelativeLayout) findViewById(R.id.contact_twitter_Layout);
        youtubeLayout = (RelativeLayout) findViewById(R.id.contact_youtube_Layout);
        linkinLayout = (RelativeLayout) findViewById(R.id.contact_link_in_Layout);


        // On click view listener  for call
        callLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall();
            }
        });

        // On click view listener  for mail
        mailLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openDialog();

//                sendMail();
            }
        });


        // On click view listener  for Facebook
        facebookLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent facebookIntent = openFacebook(Contact_us.this);
                startActivity(facebookIntent);
            }
        });

        //On click view listener for youtube
        youtubeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent youtubeIntent = openYoutube(Contact_us.this);
                startActivity(youtubeIntent);
            }
        });

        //On click view listener for twitter
        twitterLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent twitterIntent = openTwitter(Contact_us.this);
                startActivity(twitterIntent);
            }
        });
        //On click view listener for Link in
        linkinLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent linkInIntent = openLinkIn(Contact_us.this);
                startActivity(linkInIntent);
            }
        });


    }


    //Calling Start
    private void makePhoneCall() {
        String number = "01611-609372";
        if (number.trim().length() > 0) {
            if (ContextCompat.checkSelfPermission(Contact_us.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(Contact_us.this,
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);

            } else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall();
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    } //Calling End


    //Open Dialog box for mail send

    public void openDialog() {

        ExampleDialog exampleDialog = new ExampleDialog();
        exampleDialog.show(getSupportFragmentManager(), "example dialog");
    }

    //Open facebook
    public static Intent openFacebook(Context context) {

        try {
            context.getPackageManager()
                    .getPackageInfo("com.facebook.katana", 0);
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.facebook.com/xubisoft"));
        } catch (Exception e) {

            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.facebook.com/xubisoft"));
        }


    }

    //Open Youtube
    public static Intent openYoutube(Context context) {
        try {
            context.getPackageManager()
                    .getPackageInfo("com.google.android.youtube.", 0);
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.youtube.com/channel/UCx1l705n1sCGeDEn0cn9LBQ"));
        } catch (Exception e) {

            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.youtube.com/channel/UCx1l705n1sCGeDEn0cn9LBQ"));
        }

    }

    //Open Twitter
    public static Intent openTwitter(Context context) {
        try {
            context.getPackageManager()
                    .getPackageInfo("com.twitter.android", 0);
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://twitter.com/xubisoft"));
        } catch (Exception e) {

            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://twitter.com/xubisoft"));
        }
    }

    //Open LInk In
    public static Intent openLinkIn(Context context) {
        try {
            context.getPackageManager()
                    .getPackageInfo("com.example.package", 0);
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.linkedin.com/company/xubisoft"));
        } catch (Exception e) {

            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.linkedin.com/company/xubisoft"));
        }
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
        if ( session.getemail().isEmpty()==false)
        {getMenuInflater().inflate(R.menu.main2, menu);
            return true;}
        if ( session.getemail().isEmpty()==true){
            getMenuInflater().inflate(R.menu.home_menue, menu);
            return true;}

        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_log_inID) {
            startActivity(new Intent(this,Login.class));
            Contact_us.this.finish();
            return true;
        }
        if (id == R.id.action_log_outID) {
            session.seteamil(null);
            startActivity(new Intent(this, Home_without_Login.class));
            Contact_us.this.finish();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        return All_Menu_Class.HandleMenu(this,item.getItemId());

    }
}
