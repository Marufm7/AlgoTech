package com.maruf.mb.algotech;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.maruf.mb.algotech.Commonly_used_class.All_Menu_Class;
import com.maruf.mb.algotech.Commonly_used_class.Controller;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;
import static maes.tech.intentanim.CustomIntent.customType;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
Bundle bundle;
    TextView user_email,user_cource,UserName,usermobileNumber,UserInstitute;
    Session session;
    String Course_name,User_Email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_home );
//.........Animatyion.................
        customType(Home.this,"left-to-right");
        /**left-to-right
         *right-to-left
         *bottom-to-up
         *up-to-bottom
         *fadein-to-fadeout
         *rotateout-to-rotatein
         * */
        bundle = getIntent().getExtras();

         session = new Session( Home.this );


        UserInstitute = findViewById( R.id.user_instituteID );
        usermobileNumber = findViewById( R.id.user_mobileID );
        UserName = findViewById( R.id.user_nameID );
        user_email = findViewById( R.id.user_emailID );
        user_cource = findViewById( R.id.user_cource );

        user_email.setText( session.getemail() );

        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );

        User_Email=session.getemail();
        if (bundle.getString("Title")!=null){
           Course_name=bundle.getString("Title");
//            Toast.makeText(this,Course_name,Toast.LENGTH_SHORT).show();
            user_cource.setText(Course_name);
        }
        getSupportActionBar().setTitle("    Courses");
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimaryDark));
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(R.drawable.signin_action_bar_icon);
        actionBar.setDisplayShowHomeEnabled(true);
        FloatingActionButton fab = (FloatingActionButton) findViewById( R.id.fab );
        fab.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register(User_Email ,Course_name  );
            }
        } );

        DrawerLayout drawer = (DrawerLayout) findViewById( R.id.drawer_layout );
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close );
        drawer.addDrawerListener( toggle );
        toggle.syncState();

        //navigation Header name
        NavigationView navigationView = (NavigationView) findViewById( R.id.nav_view );
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.header_user_emailID);
        navUsername.setText(session.getemail());
        navigationView.setNavigationItemSelectedListener( this );
//.................................................
        String URL= getText(R.string.URL_string).toString();
        final String url = URL+"get_userl_info.php?EMAILID="+ User_Email;
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONArray jsonArray = null;
                        try {
                            jsonArray = response.getJSONArray("status");

                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject jsonObject = (JSONObject) jsonArray.get(i);

                                String fullName=jsonObject.optString("full_name").toString();

                                String MobileName=jsonObject.optString("mobile_number").toString();

                                String InstituteName=jsonObject.optString("institute_name").toString();
                            //set data
                                UserName.setText(fullName);
                                usermobileNumber.setText(MobileName);
                                UserInstitute.setText(InstituteName);


                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    Toast.makeText(getApplication(),"Your Network Connection Problem", Toast.LENGTH_LONG).show();
                } else if (error instanceof AuthFailureError) {
                    Toast.makeText(getApplication(),"Failure to Connection Server", Toast.LENGTH_LONG).show();
                } else if (error instanceof ServerError) {
                    Toast.makeText(getApplication(),"Server Problem", Toast.LENGTH_LONG).show();
                } else if (error instanceof NetworkError) {
                    Toast.makeText(getApplication(),"Network Problem", Toast.LENGTH_LONG).show();
                } else if (error instanceof ParseError) {
                    Toast.makeText(getApplication(),"Parse Problem", Toast.LENGTH_LONG).show();
                }
            }

        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                //  params.put("Authorization", "Bearer "+access_token);
                return params;
            }


        };

        req.setRetryPolicy(
                new DefaultRetryPolicy(
                        500000,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                )
        );

        Controller.getPermission().addToRequestQueue(req);
        //set data s


    }
//..............................
private void register(String emails, String cource_name) {

    class LoginAsync extends AsyncTask<String, Void, String> {

        private Dialog loadingDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loadingDialog = ProgressDialog.show(Home.this, "Please wait", "Try to submit...");
        }

        @Override
        protected String doInBackground(String... sm) {
            String sm_email = sm[0];
            String sm_C_name = sm[1];

            InputStream is = null;
            //ArrayList<String> arrayList = new ArrayList<>();
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

            nameValuePairs.add(new BasicNameValuePair("v_Email", sm_email));
            nameValuePairs.add(new BasicNameValuePair("v_reqModelString", sm_C_name));
            String result = null;

            try{
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(URL);
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



                    Toast.makeText(Home.this, "Purcessed Successful", Toast.LENGTH_SHORT).show();
                       /* startActivity( new Intent( getApplicationContext(),Register.class ) );
                        finish();*/

                }else{
                    Toast.makeText(Home.this, "Successful", Toast.LENGTH_LONG).show();
                    loadingDialog.dismiss();
                    startActivity(new Intent(Home.this,Courses_list.class));
                    Home.this.finish();
                }
            }catch (Exception e){
                Toast.makeText(Home.this, "Please connect internet and try again", Toast.LENGTH_LONG).show();
                loadingDialog.dismiss();
            }
        }
    }

    LoginAsync la = new LoginAsync();
    la.execute( emails, cource_name);
}
    //.....................
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
            session.seteamil(null);
            startActivity(new Intent(this, Home_without_Login.class));
            Home.this.finish();
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
