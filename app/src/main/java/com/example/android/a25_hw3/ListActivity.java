package com.example.android.a25_hw3;
//activity is inspired by Zybooks 5.2 the bandapp
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
/*
Thomas Sallurday
C17123785
tsallur@g.clemson.edu
*/
public class ListActivity extends AppCompatActivity implements ListFragment.OnCitySelectedListener { //activity hosts the

    @Override
    /**@pre desired geographic location was selected
     * @post fragment has been created
     */
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.list_fragment_container);

        if (fragment == null) {
            fragment = new ListFragment();
            fragmentManager.beginTransaction().add(R.id.list_fragment_container, fragment).commit();
        }
    }

    @Override
    /**
     * @pre none
     * @post EXTRA_CITY_ID >= 1 and EXTRA_CITY_ID <= 10
     */
    public void onCitySelected(int cityId) {
        // Send the city ID of the clicked button to DetailsActivity
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra(DetailsActivity.EXTRA_CITY_ID, cityId);
        startActivity(intent);
    }

}