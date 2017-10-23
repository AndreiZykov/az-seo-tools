package com.zykov.andrii.azseotools.utils;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by andrii on 10/21/17.
 */

public class NetworkUtils {


    public interface GetHTMLInfoListener {
        void onSuccess(String s);
        void onError(String e);
    }

    public static void getHTMLInfo(Context context, String url, final GetHTMLInfoListener listener) {
        RequestQueue queue = Volley.newRequestQueue(context);
            // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        listener.onSuccess(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                        listener.onError(error.getMessage());
            }
        });
            // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
