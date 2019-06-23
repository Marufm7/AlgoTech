package com.maruf.mb.algotech;

import android.content.Intent;
import android.os.Bundle;
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
import android.widget.LinearLayout;
import android.widget.ListView;
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
import com.maruf.mb.algotech.Commonly_used_class.Adapters;
import com.maruf.mb.algotech.Commonly_used_class.All_Menu_Class;
import com.maruf.mb.algotech.Commonly_used_class.Controller;
import com.maruf.mb.algotech.Read_data.DataSet;
import com.maruf.mb.algotech.Commonly_used_class.Details_View;
import com.maruf.mb.algotech.Read_data.Session;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static maes.tech.intentanim.CustomIntent.customType;

public class Service_List extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private List<DataSet> list = new ArrayList<DataSet>();
    private ListView listview;
    private Adapters adapters;
    LinearLayout layout;
    Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service__list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        session= new Session(this);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        customType(Service_List.this,"left-to-right");
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
        navigationView.setNavigationItemSelectedListener(this);
        //final String url = "http://192.168.0.113/php/courses_list.php";
        //...........................................................................................

        listview = (ListView) findViewById( R.id.course_listID );
        adapters = new Adapters( Service_List.this,list );
        // list.clear();
        listview.setAdapter( adapters );

       final String URL= getText(R.string.URL_string).toString();

        final String url = URL+"service_list.php";
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONArray jsonArray = null;
                        try {
                            jsonArray = response.getJSONArray("usa_news");

                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                                DataSet dataSet = new DataSet();
                                dataSet.set_course_name(jsonObject.getString("S_name"));
                                dataSet.set_course_details(jsonObject.getString("S_details"));
                                String URL_img= jsonObject.getString("S_url");
                                dataSet.set_course_image(URL+URL_img);

                                //dataSet.setreg_date(jsonObject.getString("reg_date"));

                                list.add(dataSet);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        adapters.notifyDataSetChanged();
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
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {


                String c_Name = String.valueOf(list.get(position).get_course_name());
                String c_dec = String.valueOf(list.get(position).get_course_details());
                String c_url = String.valueOf(list.get(position).get_course_image());
                String T = "Block";

                Intent i = new Intent(Service_List.this, Details_View.class);
                i.putExtra("TITLE", c_Name);
                i.putExtra("description", c_dec);
                i.putExtra("T_block",T);
                i.putExtra("URL", c_url);



                startActivity(i);
            }
        });
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
            Service_List.this.finish();
            return true;
        }
        if (id == R.id.action_log_outID) {
            session.seteamil(null);
            startActivity(new Intent(this, Home_without_Login.class));
            Service_List.this.finish();
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
