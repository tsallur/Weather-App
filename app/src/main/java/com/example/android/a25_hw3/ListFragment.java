package com.example.android.a25_hw3;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
/* ListFragment is inspired by the code found in ZyBooks 5.2
Thomas Sallurday
C17123785
tsallur@g.clemson.edu
*/

public class ListFragment extends Fragment {

    // For the activity to implement
    public interface OnCitySelectedListener { //interface for onCitySelected function
        void onCitySelected(int CityId);
    }

    // Reference to the activity
    private OnCitySelectedListener mListener;


    @Override
    /**
     * @pre onCitySelectedListener exists and is listening
     * @post mListener = oncitySelectedListener
     */
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnCitySelectedListener) {
            mListener = (OnCitySelectedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnCitySelectedListener");
        }
    }

    @Override
    /**
     * @pre none
     * @post mListener = null
     */
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        /**
         * @pre none
         * @post DetailsActivity is sent the cityId
         */
        public void onClick(View view) {
            // Send the band ID of the clicked button to DetailsActivity
            Intent intent = new Intent(getActivity(), DetailsActivity.class);
            String bandId = (String) view.getTag();
            intent.putExtra(DetailsActivity.EXTRA_CITY_ID, Integer.parseInt(bandId));
            startActivity(intent);
        }
    };





    @Override
    /**
     * @pre none
     * @post view = correct view that lists all city options
     */
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.band_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Send bands to recycler view
        CityAdapter adapter = new CityAdapter(CityDataBase.getInstance(getContext()).getCities());
        recyclerView.setAdapter(adapter);

        return view;
    }


    private class CityHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private City mCity;
        private TextView textView;

        /**
         * @pre none
         * @post textviews = cityNames
         */
        public CityHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_city, parent, false));
            itemView.setOnClickListener(this);
            textView = itemView.findViewById(R.id.cityName);
        }

        /**
         * @pre none
         * @post mCity = city && textView.text = mCity.name
         */
        public void bind(City city) {
            mCity = city;
            textView.setText(mCity.getName());
        }

        @Override
        /**
         * @pre non2
         * @post mListener = #mListener
         */
        public void onClick(View view) {
            //Tell ListActivity what band was clicked
            mListener.onCitySelected(mCity.getId());
        }
    }

    private class CityAdapter extends RecyclerView.Adapter<CityHolder> {

        private List<City> mCitys;

        /**
         @pre none
         * @postmCitys = cities
         */
        public CityAdapter(List<City> cities) {
            mCitys = cities;
        }

        @Override
        /**
         * @pre none
         * @post layout is made
         */
        public CityHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new CityHolder(layoutInflater, parent);
        }


        @Override
        /**
         * @pre none
         * @post mCitys = #mCItys
         */
        public void onBindViewHolder(CityHolder holder, int position) {
            City city = mCitys.get(position);
            holder.bind(city);
        }

        @Override
        /**
         * @pre none
         * @post mCitys = #mCitys
         */
        public int getItemCount() {
            return mCitys.size();
        }
    }
}