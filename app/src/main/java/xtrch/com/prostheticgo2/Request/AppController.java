package xtrch.com.prostheticgo2.Request;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class AppController extends Application {

    private static final String TAG = AppController.class.getSimpleName();
    public static AppController instance;
    RequestQueue requestQueue;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
    }

    public static AppController getInstance()
    {
        return instance;
    }

    public RequestQueue getRequestQueue()
    {
        if(requestQueue == null)
        {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag)
    {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req)
    {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelAllRequest(Object req)
    {
        if(requestQueue != null)
        {
            requestQueue.cancelAll(req);
        }
    }
}