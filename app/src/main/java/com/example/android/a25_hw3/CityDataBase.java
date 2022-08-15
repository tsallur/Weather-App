package com.example.android.a25_hw3;
/*
Thomas Sallurday
C17123785
tsallur@g.clemson.edu
*/
import android.content.Context;
import android.content.res.Resources;
import java.util.List;
import java.util.ArrayList;

public class CityDataBase { //basically an array of City's that will make it convienet to pull the name of cities
    /**
     * Citation: Class based on BandDataBase from Zybooks 5.2
     */
    private static CityDataBase myCityDatabase;
    private List<City> CitiesList;

    /**
     * @pre none
     * @post none
     */
    public static CityDataBase getInstance(Context context) {
        if (myCityDatabase == null) {
            myCityDatabase = new CityDataBase(context);
        }
        return myCityDatabase;
    }

    /**
     * @pre non
     * @ost CitiesList = R.array.cities
     */
    private CityDataBase(Context context) {
        CitiesList = new ArrayList<>();
        Resources res = context.getResources();
        String[] cities = res.getStringArray(R.array.cities);
        for (int i = 0; i < cities.length; i++) {
            CitiesList.add(new City(i+1, cities[i]));
        }
    }

    /**
     * @pre none
     * @post CitiesList = #CitiesList
     */
    public List<City> getCities() {
        return CitiesList;
    }

    /**
     * @pre city.id != null
     * @post CitiesList = #CitiesList
     */

    public City getCity(int cityId) {
        for (City city : CitiesList) {
            if (city.getId() == cityId) {
                return city;
            }
        }
        return null;
    }
}
