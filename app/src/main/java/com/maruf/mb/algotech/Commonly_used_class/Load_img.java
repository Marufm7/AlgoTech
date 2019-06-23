package com.maruf.mb.algotech.Commonly_used_class;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.maruf.mb.algotech.R;

public class Load_img{


    public void Img(Context context,String url, ImageView imageView){
        Glide.with(context)
                .load(url)
                .override(800, 700)
                .fitCenter()
                .error(R.drawable.apple)
                // .placeholder(R.anim.animation)
                //.apply(new RequestOptions().override(600, 200))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);

    }
}
