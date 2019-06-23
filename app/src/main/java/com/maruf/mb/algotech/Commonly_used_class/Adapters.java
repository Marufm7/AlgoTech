package com.maruf.mb.algotech.Commonly_used_class;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.maruf.mb.algotech.R;
import com.maruf.mb.algotech.Read_data.DataSet;

import java.util.List;

/**
 * Created by sm on 11/07/2016.
 */

public class Adapters extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<DataSet> DataList;
    ImageLoader imageLoader = Controller.getPermission().getImageLoader();

    public Adapters(Activity activity, List<DataSet> billionairesItems) {
        this.activity = activity;
        this.DataList = billionairesItems;

    }

    @Override
    public int getCount() {
        return DataList.size();
    }

    @Override
    public Object getItem(int location) {
        return DataList.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_network_hospitality, null);

        if (imageLoader == null)
            imageLoader = Controller.getPermission().getImageLoader();




        TextView name = (TextView) convertView.findViewById( R.id.courses_nameID);
        TextView address = (TextView) convertView.findViewById(R.id.courses_detailsID);
        TextView duration = (TextView) convertView.findViewById(R.id.courses_durationID);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.item_image);



        DataSet m = DataList.get(position);


      Load_img  load_img= new Load_img();
        load_img.Img(activity,m.get_course_image(),imageView);


        name.setText(m.getfirstname());
        address.setText(m.getlastname());

        name.setText( m.getCourse_name() );
        address.setText( m.getCourse_details() );
        duration.setText( m.get_cource_duration() );


        return convertView;
    }


    public void filter(String text) {
    }
}
