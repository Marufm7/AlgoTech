package com.maruf.mb.algotech;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
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
import android.widget.Spinner;
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

public class Register extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private EditText Names, Emails, Password, Confirm_Pass, Mobiles, Institute_name;
    public Button Register;
    private String Users_course_name,url;
    public Spinner spinner;
    Session session;
    String reqModelString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_register );

session=new Session(this);
        //Device information_data (reqModelString)
         reqModelString = Build.MANUFACTURER
                + " " + Build.MODEL + " " + Build.VERSION.RELEASE
                + " " + Build.VERSION_CODES.class.getFields()[Build.VERSION.SDK_INT].getName();




        customType(Register.this,"left-to-right");
        /**left-to-right
         *right-to-left
         *bottom-to-up
         *up-to-bottom
         *fadein-to-fadeout
         *rotateout-to-rotatein
         * */




        DrawerLayout drawer = (DrawerLayout) findViewById( R.id.drawer_layout );
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,drawer,R.string.navigation_drawer_open,R.string.navigation_drawer_close );
        drawer.addDrawerListener( toggle );
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById( R.id.nav_view );
        navigationView.setNavigationItemSelectedListener( this );




        //API connection
       String URL= getText(R.string.URL_string).toString();
       url = URL+"register.php";

        Names = (EditText) findViewById(R.id.reg_full_nameID);
        Emails = (EditText) findViewById(R.id.reg_emailID);
        Password = (EditText) findViewById(R.id.reg_passID);
        Confirm_Pass = (EditText) findViewById(R.id.reg_confirm_passID);
        Mobiles = (EditText)findViewById(R.id.reg_mobileID);
        Institute_name = (EditText)findViewById(R.id.reg_instituteID);



        Register = (Button)findViewById(R.id.registerID);
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CheakTextFilds();      //send in server}

            }
        });
    }

    private void CheakTextFilds() {
        String name = Names.getText().toString().trim();
        String email = Emails.getText().toString().trim();
        String password = Password.getText().toString().trim();
        String confirm_password = Confirm_Pass.getText().toString().trim();
        String mobile = Mobiles.getText().toString().trim();
        String institute_name = Institute_name.getText().toString().trim();

        if (name.isEmpty()) {
            Names.setError("Please enter your name");
            return;
        }
        if (session.getUsers_interest().isEmpty()==false){
            Users_course_name=session.getUsers_interest();
        }
        if (email.isEmpty()) {
            Emails.setError("Please enter your valid email");
            return;
        }
        if (password.isEmpty()) {
            Password.setError("Please enter your password");
            return;
        }
        if (confirm_password.isEmpty()) {
            Confirm_Pass.setError("Please enter your Confirm password");
            return;
        }
        if ( mobile.isEmpty() || (mobile.length() < 11) ) {
            Mobiles.setError("Please enter your valid mobile number");
            return;
        }
        if (institute_name.isEmpty()) {
            Password.setError("Please enter your institute name");
            return;
        }
        if (password.equals(confirm_password)==false) {
            Password.setError("Password Not Matching");
            Confirm_Pass.setError("Confirm Password Not Matching");
            return;
        }
        else {
            register( name, email, password , mobile, institute_name, Users_course_name, reqModelString);
        }
    }


    private void register(String names, String emails, String password, String mobiles, String institute_name, String bought_item, String s_reqModelString) {

        class LoginAsync extends AsyncTask<String, Void, String> {

            private Dialog loadingDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loadingDialog = ProgressDialog.show(Register.this, "Please wait", "Try to submit...");
            }

            @Override
            protected String doInBackground(String... sm) {
                String sm_name = sm[0];
                String sm_email = sm[1];
                String sm_password = sm[2];
                String sm_mobile = sm[3];
                String sm_institute_name = sm[4];
                String sm_course_name = sm[5];
                String sm_reqModelString = sm[6];

                InputStream is = null;
                //ArrayList<String> arrayList = new ArrayList<>();
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                nameValuePairs.add(new BasicNameValuePair("v_Full_Name", sm_name));
                nameValuePairs.add(new BasicNameValuePair("v_Email", sm_email));
                nameValuePairs.add(new BasicNameValuePair("v_Password", sm_password));
                nameValuePairs.add(new BasicNameValuePair("v_Mobiles", sm_mobile));
                nameValuePairs.add(new BasicNameValuePair("v_Institute_Name", sm_institute_name));
                nameValuePairs.add(new BasicNameValuePair("v_Course_Name", sm_course_name));
                nameValuePairs.add(new BasicNameValuePair("v_reqModelString", sm_reqModelString));
                String result = null;

                try{
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(url);
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    HttpResponse response = httpClient.execute(httpPost);
                    HttpEntity entity = response.getEntity();
                    is = entity.getContent();
                    /*UrlEncodedFormEntity*/
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
                    if(s.equalsIgnoreCase("success")){
                        Toast.makeText(Register.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                        startActivity( new Intent( com.maruf.mb.algotech.Register.this, Login.class ) );
                        finish();

                    }else{
                        Toast.makeText(Register.this, "Not Successful", Toast.LENGTH_LONG).show();
                        loadingDialog.dismiss();
                    }
                }catch (Exception e){
                    Toast.makeText(Register.this, "Please connect internet and try again", Toast.LENGTH_LONG).show();
                    loadingDialog.dismiss();
                }
            }
        }

        LoginAsync la = new LoginAsync();
        la.execute(names, emails, password, mobiles, institute_name, bought_item, s_reqModelString);
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

}
