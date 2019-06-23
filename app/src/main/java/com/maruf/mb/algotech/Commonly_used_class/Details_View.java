package com.maruf.mb.algotech.Commonly_used_class;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.maruf.mb.algotech.Home;
import com.maruf.mb.algotech.Login;
import com.maruf.mb.algotech.R;
import com.maruf.mb.algotech.Read_data.DataSet;
import com.maruf.mb.algotech.Read_data.Session;

import java.util.ArrayList;
import java.util.List;

import me.biubiubiu.justifytext.library.JustifyTextView;

public class Details_View extends AppCompatActivity {
    ListView listview;
    //private Adapters adapters;
    Bundle bundle;
    String title,desc,duer,urL,Block_btn;
    Session session;
    Load_img load_img;
    private List<DataSet> list = new ArrayList<DataSet>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details__view);
        // DataSet m=new DataSet();
        session =new Session(this);
        final TextView Title=findViewById(R.id.hedder_Text);
        JustifyTextView Title_Discription=findViewById(R.id.description_ID);
        TextView Title_Duration=findViewById(R.id.courses_durationID);
        Button Bye=findViewById(R.id.bye_btn);

//
//        Title.setText( m.getCourse_name() );
//        Title_Discription.setText( m.getCourse_details() );
        //  duration.setText( m.get_cource_duration() );
        //final String url = "http://192.168.0.113/php/courses_list.php";
        //''''''''''''

        bundle = getIntent().getExtras();
        if (bundle.getString("TITLE")!=null){
            title= bundle.getString("TITLE");
            Title.setText(title);

        }
        if (bundle.getString("description")!=null){
            desc= bundle.getString("description");
            Title_Discription.setText(desc);
        }
        if (bundle.getString("duration")!=null){
            duer= bundle.getString("duration");
            Title_Duration.setText(duer);
        }
        if (bundle.getString("URL")!=null){
            urL= bundle.getString("URL");
            Title_Duration.setText(duer);
        }
        if (bundle.getString("T_block")!=null){
            Block_btn= bundle.getString("T_block");
           if (Block_btn.equals("Block")){
              Bye.setVisibility(View.GONE);
           }
        }


        ImageView imageView= (ImageView)findViewById(R.id.hedder_image);
        String URL= getText(R.string.URL_string).toString() + urL;
        //Load Img from Server
        load_img= new Load_img();
        load_img.Img(Details_View.this,URL,imageView);


        Bye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (session.getemail().isEmpty()){
                    session.setUsers_interest(title);
                    startActivity(new Intent(getApplicationContext(), Login.class));
                }
                else {
                    Intent i=new Intent(getApplicationContext(),Home.class);
                    i.putExtra("Title",title);
                    startActivity(i);

                }

            }
        });
    }
}