package com.example.android.a25_hw3;
/*
Thomas Sallurday
C17123785
tsallur@g.clemson.edu
*/
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


public class DetailsActivity extends AppCompatActivity {
    public static final String EXTRA_CITY_ID = "bandID";

    @Override
    /**
     * @pre none
     * @post framnet_initialized = detailsFragment[cityId]
     */
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.list_fragment_container);
        if (fragment == null) {
            int cityId = getIntent().getIntExtra(EXTRA_CITY_ID, 1);
            fragment = DetailsFragment.newInstance(cityId);
            fragmentManager.beginTransaction().add(R.id.list_fragment_container, fragment).commit();
        }
    }
    }