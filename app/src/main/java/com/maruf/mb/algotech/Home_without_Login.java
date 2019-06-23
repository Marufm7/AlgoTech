package com.maruf.mb.algotech;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.maruf.mb.algotech.Commonly_used_class.All_Menu_Class;
import com.maruf.mb.algotech.Read_data.Session;

import static maes.tech.intentanim.CustomIntent.customType;

public class Home_without_Login extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Button Login, Create_Account;
Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main2 );
        setTitle(" ");
        AppBarLayout appBarLayout=(AppBarLayout)findViewById(R.id.app_bar_home);

        YoYo.with(Techniques.BounceInDown)
                .duration(3000)
                .repeat(0)
                .playOn(appBarLayout);
        customType(Home_without_Login.this,"fadein-to-fadeout");
        /**left-to-right
         *right-to-left
         *bottom-to-up
         *up-to-bottom
         *fadein-to-fadeout
         *rotateout-to-rotatein
         * */
    session= new Session(Home_without_Login.this);
        Login = (Button) findViewById( R.id.home_page_logID );
        Login.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent( Home_without_Login.this,com.maruf.mb.algotech.Login.class ) );

            }
        } );

        Create_Account = (Button) findViewById( R.id.create_accountID );
        Create_Account.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent( Home_without_Login.this,Register.class ) );

            }
        } );


        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );
        getSupportActionBar().setTitle( "" );

        /*FloatingActionButton fab = (FloatingActionButton) findViewById( R.id.fab );
        fab.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make( view,"Replace with your own action",Snackbar.LENGTH_LONG )
                        .setAction( "Action",null ).show();
            }
        } );*/

        DrawerLayout drawer = (DrawerLayout) findViewById( R.id.drawer_layout );
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close );
        drawer.addDrawerListener( toggle );
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById( R.id.nav_view );
        navigationView.setNavigationItemSelectedListener( this );
    }




    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById( R.id.drawer_layout );
        if (drawer.isDrawerOpen( GravityCompat.START )) {
            drawer.closeDrawer( GravityCompat.START );
        } else {
            super.onBackPressed();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate( R.menu.home_menue,menu );
//
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_log_inID) {
            session.seteamil(null);
            startActivity(new Intent(this,Login.class));
            return true;
        }
        return super.onOptionsItemSelected( item );
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        return All_Menu_Class.HandleMenu(this,item.getItemId());

    }
}
