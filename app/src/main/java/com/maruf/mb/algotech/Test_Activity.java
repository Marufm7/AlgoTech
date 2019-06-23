package com.maruf.mb.algotech;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.maruf.mb.algotech.Commonly_used_class.Controller;
import com.maruf.mb.algotech.Read_data.Session;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class Test_Activity extends AppCompatActivity {
    TextView s_stock_quantity, s_price, s_total_sale, s_customer_commission, s_agent_commission;
Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_);

            session= new Session(this);
            String email=session.getemail();
            setTitle("JSON Object");

            // ------------------------- Text View --------------------------
            s_stock_quantity = (TextView) findViewById(R.id.stock_quantity);
            s_price = (TextView) findViewById(R.id.price);
            s_total_sale = (TextView) findViewById(R.id.total_sale);
            s_customer_commission = (TextView) findViewById(R.id.customer_commission);
            s_agent_commission = (TextView) findViewById(R.id.agent_commission);

        final String url = "http://192.168.20.100/php//get_userl_info.php?EMAILID=" + "musanna324@gmail.com";
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
                               Toast.makeText(getApplicationContext(),fullName,Toast.LENGTH_LONG).show();
                                String MobileName=jsonObject.optString("mobile_number").toString();
                                Toast.makeText(getApplicationContext(),MobileName,Toast.LENGTH_LONG).show();
                                String InstituteName=jsonObject.optString("institute_name").toString();
                                Toast.makeText(getApplicationContext(),InstituteName,Toast.LENGTH_LONG).show();



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



        }
    }