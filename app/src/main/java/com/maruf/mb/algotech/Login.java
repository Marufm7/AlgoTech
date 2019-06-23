package com.maruf.mb.algotech;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.maruf.mb.algotech.Commonly_used_class.All_Menu_Class;
import com.maruf.mb.algotech.Read_data.Session;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static maes.tech.intentanim.CustomIntent.customType;

public class Login extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    EditText Email, Password;
    String URL;
    Button Login;
    String s;
    TextView Forget_Pass, Not_register;
    public Session session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );

        URL= getText(R.string.URL_string).toString()+"validation.php";

        customType(Login.this,"left-to-right");
        /**left-to-right
         *right-to-left
         *bottom-to-up
         *up-to-bottom
         *fadein-to-fadeout
         *rotateout-to-rotatein
         * */
        session = new Session( Login.this );
        Not_register=findViewById(R.id.notregisterId);
        Not_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,Register.class));
                Login.this.finishAffinity();
            }
        });


        Forget_Pass = (TextView) findViewById( R.id.forget_passID );
        Forget_Pass.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText( getApplicationContext(), "Click forget password", Toast.LENGTH_SHORT ).show();
                /*startActivity( new Intent( com.example.xubisoftapp.Login.this, ForgetPassword.class ) );
                finish();*/
            }
        } );





        //action bar
  /*      Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );


        getSupportActionBar().setTitle("Sign In");
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimaryDark));
        setSupportActionBar(toolbar);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(R.drawable.signin_action_bar_icon);
        actionBar.setDisplayShowHomeEnabled(true);
*/
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
                this,drawer,R.string.navigation_drawer_open,R.string.navigation_drawer_close );
        drawer.addDrawerListener( toggle );
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById( R.id.nav_view );
        navigationView.setNavigationItemSelectedListener( this );








        Email = (EditText) findViewById( R.id.emailID );
        Password = (EditText) findViewById( R.id.passID );
        Login = (Button) findViewById( R.id.loginID );

        Login.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = Email.getText().toString().trim();
                String pass = Password.getText().toString().trim();
                //String pass = Password.getText().toString().trim();

                /*if(session.getusename().isEmpty() && session.getusepass().isEmpty()){
                    Toast.makeText(getApplicationContext(),"empty",Toast.LENGTH_LONG).show();
                }*/
                if (session.getemail().isEmpty()){
                    //Toast.makeText( getApplicationContext(), "empty", Toast.LENGTH_SHORT ).show();
                }

                if (email.isEmpty()) {
                    Email.setError( "Please enter your Email" );
                    return;
                }

                if (pass.isEmpty()) {
                    Password.setError("Please enter your password");
                    return;
                }
                else {
                    login( email, pass );
                } //send in server}

            }
        } );

    }


    private void login(final String emails,String passes) {

        class LoginAsync extends AsyncTask<String, Void, String> {

            private Dialog loadingDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loadingDialog = ProgressDialog.show(Login.this, "Please wait", "Try to submit...");
            }

            @Override
            protected String doInBackground(String... sm) {
                String sm_emails = sm[0];
                String sm_passes = sm[1];
               // String sm_con_passes = sm[1];


                InputStream is = null;
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("v_emailID", sm_emails));
                nameValuePairs.add(new BasicNameValuePair("v_passdID", sm_passes));
                //nameValuePairs.add(new BasicNameValuePair("v_confirm_pass", sm_con_passes));
                String result = null;

                try{
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(URL);
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    HttpResponse response = httpClient.execute(httpPost);
                    HttpEntity entity = response.getEntity();
                    is = entity.getContent();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
                    StringBuilder sb = new StringBuilder();
                    String line = null;
                    while ((line = reader.readLine()) != null)
                    {
                        sb.append(line + "\n");
                    }
                    result = sb.toString();
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return result;
            }

            @Override protected void onPostExecute(String result){


                try{
                    String s = result.trim();
                    loadingDialog.dismiss();
                    if(s.equalsIgnoreCase("Login")){

                        session.seteamil( emails );
                        startActivity( new Intent( Login.this, Courses_list.class ) );
//                        Login.this.finish();


                        //Toast.makeText( getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT ).show();
                    }

                    if(s.equalsIgnoreCase("NotLogin")) {
                       /* Email.setError( "Incorrect Email" );
                        Password.setError( "Incorrect password" );*/
                        Toast.makeText(Login.this, "Incorrect username/password", Toast.LENGTH_SHORT).show();

                    } /*else{
                        Toast.makeText(Login.this, "Please Try again", Toast.LENGTH_LONG).show();
                        loadingDialog.dismiss();
                    }*/
                }catch (Exception e){
                    Toast.makeText(Login.this, "Please connect internet and try again", Toast.LENGTH_LONG).show();
                    loadingDialog.dismiss();
                }
            }
        }

        LoginAsync la = new LoginAsync();
        la.execute(emails, passes);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate( R.menu.main2,menu );

        // change color for icon 0
        for(int i = 0; i < menu.size(); i++){
            Drawable drawable = menu.getItem(i).getIcon();
            if(drawable != null) {
                drawable.mutate();
                drawable.setColorFilter(getResources().getColor(R.color.colorPrimaryDark), PorterDuff.Mode.SRC_ATOP);
            }
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_log_outID) {

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

    /*public void hide_keyboard(View view) {



    }*/
}
