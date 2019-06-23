package com.maruf.mb.algotech.Commonly_used_class;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;


/**
 * Created by sm on 01/01/2019.
 */

public class Controller extends Application {

    public static final String TAG = Controller.class.getSimpleName();

    private RequestQueue queue;
    private com.android.volley.toolbox.ImageLoader ImageLoader;

    private static Controller controller;

    @Override
    public void onCreate() {
        super.onCreate();
        controller = this;
    }

    public static synchronized Controller getPermission() {
        return controller;
    }

    public RequestQueue getRequestQueue() {
        if (queue == null) {
            queue = Volley.newRequestQueue(getApplicationContext());
        }

        return queue;
    }

    public com.android.volley.toolbox.ImageLoader getImageLoader() {
        getRequestQueue();
        if (ImageLoader == null) {
            ImageLoader = new ImageLoader(this.queue,
                    new BitmapCache());
        }
            return this.ImageLoader;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        req.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        req.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (queue != null) {
            queue.cancelAll(tag);
        }
    }

}
