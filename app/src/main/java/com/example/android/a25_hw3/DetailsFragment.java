package com.example.android.a25_hw3;
/*
Thomas Sallurday
C17123785
tsallur@g.clemson.edu
*/
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static java.lang.Integer.valueOf;
import static java.lang.Math.round;

/**
 * Class based on Details Fragment from Zybooks5.5 and volley info from Zybooks 6.9
 */

public class DetailsFragment extends Fragment {

    private City city;
    private int CityId;
    private TextView temptextView;
    private TextView feelsLikeView;
    private TextView descriptionView;

    /**
     * @pre none
     * @post fragment is initiated with the correct city details
     */
    public static Fragment newInstance(int cityId) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putInt("bandId", cityId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    /**
     * @pre none
     * @post cityDataBase = #CityDataBase
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the band ID from the intent that started DetailsActivity
        CityId= 1;
        if (getArguments() != null) {
            CityId = getArguments().getInt("bandId");
        }
        city = CityDataBase.getInstance(getContext()).getCity(CityId);

    }

    @Override
    /**
     * @pre none
     * @post cityId = #CityId
     * view contains info based on city selected
     * temptextView = temperature
     * feelsLikeView = feels_like
     * descriptionView = description
     */
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        TextView nameTextView = view.findViewById(R.id.cityName);
        nameTextView.setText(city.getName());
        temptextView = view.findViewById(R.id.temperature);
        feelsLikeView = view.findViewById(R.id.feels_like);
        descriptionView = view.findViewById(R.id.description);
        getWeatherData(CityId);
        return view;
    }

    /**
     * @pre * temptextView = temperature
     *      * feelsLikeView = feels_like
     *      * descriptionView = description
     * @post
     *
     * temptextView.text = response.temp
     * feelsLikeView.text = response.feels_like
     * descriptionView.text = response.description
     */
    //function is heavily based off of the way Zybooks 6.9 made their basic volley calls
    public void getWeatherData(int cityId){
        //declares all longitude and latitude info for their corresponsing location number.
        double location1_long = -82.8374, location3_long = -79.9959, location5_long = 2.3522;
        double location1_lat = 34.6834, location3_lat = 40.4406, location5_lat = 48.8566;
        double location2_long = 3.7038, location4_long = -82.3940, location6_long = -122.3321;
        double location2_lat = 40.4168, location4_lat = 34.8526,location6_lat = 47.6080;
        double location7_long = -81.0884, location7_lat = 32.0762, location8_long = -84.3863;
        double location8_lat = 33.7537, location9_long = -75.1652, location9_lat =  39.9526;
        double location10_long = -118.2437, location10_lat = 34.0522,selected_long = 0, selected_lat = 0;
        //switch statement that sets correct longitude and latitude
        switch(cityId){
            case(1):
                selected_long = location1_long;
                selected_lat = location1_lat;
                break;
            case(2):
                selected_long = location2_long;
                selected_lat = location2_lat;
                break;
            case(3):
                selected_long = location3_long;
                selected_lat = location3_lat;
                break;
            case(4):
                selected_long = location4_long;
                selected_lat = location4_lat;
                break;
            case(5):
                selected_long = location5_long;
                selected_lat = location5_lat;
                break;
            case(6):
                selected_long = location6_long;
                selected_lat = location6_lat;
                break;
            case(7):
                selected_long = location7_long;
                selected_lat = location7_lat;
                break;
            case(8):
                selected_long = location8_long;
                selected_lat = location8_lat;
                break;
            case(9):
                selected_long = location9_long;
                selected_lat = location9_lat;
                break;
            case(10):
                selected_long = location10_long;
                selected_lat = location10_lat;
                break;
        }

        //Create a new RequestQueue
        String JSON_data;
        String url = "https://api.openweathermap.org/data/2.5/onecall?lat=" + selected_lat + "&lon="+ selected_long + "&units=imperial&exclude=daily&appid=b760bdcc3169215434411cfd17bf32d4";
        String TAG = "JSON_RETURN";
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
// Request a string response from the provided URL.
        JsonObjectRequest requestObj = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Display the first 500 characters of the response string.
                        Log.d(TAG, "JSON response: " + response);
                      //  String string = response.substring(0,10);
                       // text.setText(string);
                        try {
                                JSONArray jsonArray = response.getJSONArray("hourly"); //get array hourly
                                JSONObject jo = jsonArray.getJSONObject(0); //get array in the form of an object
                                String temp = jo.getString("temp"); //gets temp field
                                double tempDouble = Double.parseDouble(temp);
                                tempDouble= round(tempDouble);
                                int tempInt = (int) tempDouble; //gets rid of decimal place
                                String string = "" + tempInt + "\u00B0" +" F";
                                temptextView.setText(string);
                                String feels = jo.getString("feels_like");
                                int feels_int = (int) Double.parseDouble(feels);
                                string = "Feels Like:\n" + feels_int + "\u00B0" +" F";
                                feelsLikeView.setText(string);
                                jo = jsonArray.getJSONObject(0);
                                String weather = "weather";
                                JSONArray j2 = jo.getJSONArray(weather); //gets weather array
                                JSONObject j3 = j2.getJSONObject(0); //puts it into form of JSON object
                                String description = j3.getString("description"); // gets description
                                descriptionView.setText(description);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            /**
             * @pre none
             * @post none
             */
            public void onErrorResponse(VolleyError error) {
            }
        });

// Add the request to the RequestQueue.
        queue.add(requestObj); // adds request to the queue
    }
}