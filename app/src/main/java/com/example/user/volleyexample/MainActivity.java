package com.example.user.volleyexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        JSONobjectRequest();
        JSONArrayRequest();
        StringRequest();
        Adding_post_parameters_request();
        Adding_request_headers();
        Making_Image_request_in_NetworkImageView();
        Loading_image_in_ImageView();
        image_and_error_image();
        Loading_request_from_cache();
        Invalidate_cache();
        Turning_off_cache();
        Deleting_cache_for_particular_URL();
        Deleting_all_the_cache();
        Cancelling_requests();
        Request_prioritization();

    }

    private void JSONobjectRequest() {

        String tag_json_obj = "json_obj_req";
        String url = "https://raw.githubusercontent.com/ianbar20/JSON-Volley-Tutorial/master/Example-JSON-Files/Example-Object.JSON";

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG,"object response = "+ response.toString());
                        //pDialog.hide();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "Error: " + error.getMessage());
                // hide the progress dialog
                //pDialog.hide();
            }
        });

        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }

    private void JSONArrayRequest() {
        // Tag used to cancel the request
        String tag_json_arry = "json_array_req";

        String url = "https://api.androidhive.info/volley/person_array.json";

        JsonArrayRequest req = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG,"array response = "+response.toString());
                        //pDialog.hide();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                //pDialog.hide();
            }
        });

// Adding request to request queue
        AppController.getInstance().addToRequestQueue(req, tag_json_arry);
    }

    private void StringRequest() {

        String  tag_string_req = "string_req";

        String url = "https://api.androidhive.info/volley/string_response.html";

        StringRequest strReq = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                //pDialog.hide();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                //pDialog.hide();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);

    }

    private void Adding_post_parameters_request() {
        // Tag used to cancel the request
        String tag_json_obj = "json_obj_req";

        String url = "https://api.androidhive.info/volley/person_object.json";

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", "Androidhive");
                params.put("email", "abc@androidhive.info");
                params.put("password", "password123");

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }

    private void Adding_request_headers() {
        // Tag used to cancel the request
        String tag_json_obj = "json_obj_req";

        String url = "https://api.androidhive.info/volley/person_object.json";

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        }) {

            /**
             * Passing some request headers
             * */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                headers.put("apiKey", "xxxxxxxxxxxxxxx");
                return headers;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }

    private void Making_Image_request_in_NetworkImageView() {
        //ImageLoader imageLoader = AppController.getInstance().getImageLoader();
        // If you are using NetworkImageView
        //imgNetWorkView.setImageUrl(Const.URL_IMAGE, imageLoader);
    }

    private void Loading_image_in_ImageView() {
        ImageLoader imageLoader = AppController.getInstance().getImageLoader();

        // If you are using normal ImageView
        imageLoader.get("sample image url here", new ImageLoader.ImageListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Image Load Error: " + error.getMessage());
            }

            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean arg1) {
                if (response.getBitmap() != null) {
                    // load image into imageview
                    //imageView.setImageBitmap(response.getBitmap());
                }
            }
        });
    }

    private void image_and_error_image() {
        // Loading image with placeholder and error image
        //imageLoader.get(Const.URL_IMAGE, ImageLoader.getImageListener(imageView, R.drawable.ico_loading, R.drawable.ico_error));
    }

    private void Loading_request_from_cache() {
        Cache cache = AppController.getInstance().getRequestQueue().getCache();
        Cache.Entry entry = cache.get("request url");
        if(entry != null){
            try {
                String data = new String(entry.data, "UTF-8");
                // handle data, like converting it to xml, json, bitmap etc.,
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }else
            {
             //Cached response doesn't exists. Make network call here
        }
    }

    private void Invalidate_cache() {
        // Invalidate means we are invalidating the cached data instead of deleting it.
        // Volley will still uses the cached object until the new data received from server.
        // Once it receives the response from the server it will override the older cached response.

        AppController.getInstance().getRequestQueue().getCache().invalidate("url", true);

    }

    private void Turning_off_cache() {
        // String request
        //StringRequest stringReq = new StringRequest("....");

        // disable cache
        //stringReq.setShouldCache(false);
    }

    private void Deleting_cache_for_particular_URL() {
        AppController.getInstance().getRequestQueue().getCache().remove("url");
    }

    private void Deleting_all_the_cache() {
            //Followoing will delete the cache for all the URLs.
            //AppController.getInstance().getRequestQueue().getCache().clear("url");

    }

    private void Cancelling_requests() {

        //If you notice addToRequestQueue(request, tag) method, it accepts two parameters. One is request object and other is request tag. This tag will be used to identify the request while cancelling it. If the tag is same for multiple requests, all the requests will be cancelled. cancellAll() method is used to cancel any request.

        //8.1 Cancel single request
        //Following will cancel all the request with the tag named “feed_request”
        String tag_json_arry = "json_req";
        AppController.getInstance().getRequestQueue().cancelAll("feed_request");


        //8.2 Cancel all requests
        //If you don’t pass any tag to cancelAll() method, it will cancel the request in request queue.
        //AppController.getInstance().getRequestQueue().cancelAll();

    }

    private void Request_prioritization() {
        //If you are making multiple request at the same time, you can prioritize the requests those you want be executed first. The priory can be Normal, Low, Immediate and High.

        final Request.Priority priority = Request.Priority.HIGH;

        StringRequest strReq = new StringRequest(Request.Method.GET,
                "Const.URL_STRING_REQ", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                //msgResponse.setText(response.toString());
                //hideProgressDialog();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                //hideProgressDialog();
            }
        }) {
            @Override
            public Priority getPriority() {
                return priority;
            }
        };
    }
}