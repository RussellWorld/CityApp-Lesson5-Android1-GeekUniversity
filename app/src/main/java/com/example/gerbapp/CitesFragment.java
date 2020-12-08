package com.example.gerbapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class CitesFragment extends Fragment {


    private boolean isExitCoatOfArms;
    private City currentCity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initList(view);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        isExitCoatOfArms = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;

        if (savedInstanceState != null) {
            currentCity = savedInstanceState.getParcelable(Constants.CITY_KEY);
        } else {
            currentCity = new City(0, getResources().getStringArray(R.array.cities)[0]);

        }
        if (isExitCoatOfArms) showCoatOfArms(currentCity);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable(Constants.CITY_KEY, currentCity);

    }

    private void initList(View view) {
        LinearLayout linearView = (LinearLayout) view;

        String[] cities = getResources().getStringArray(R.array.cities);

        for (int i = 0; i < cities.length; i++) {
            String city = cities[i];
            TextView tv = new TextView(getContext());
            tv.setText(city);
            tv.setTextSize(30);

            linearView.addView(tv);
            final int f1 = i;
            tv.setOnClickListener((View View) -> {
                currentCity = new City(f1, getResources().getStringArray(R.array.cities)[f1]);
                showCoatOfArms(currentCity);
            });
        }
    }

    private void showCoatOfArms(City currentCity) {
        if (isExitCoatOfArms) {
            CoatOfArmsFragment detailFragment = (CoatOfArmsFragment) getFragmentManager().findFragmentById(R.id.coat_of_arms);


            if (detailFragment == null || currentCity.getImageIndex() != detailFragment.getCurrentCity().getImageIndex()) {
               detailFragment = CoatOfArmsFragment.create(currentCity);
            }

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.coat_of_arms, detailFragment);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        } else {
            Intent intent = new Intent();
            intent.setClass(getActivity(), CoatOfArmsActivity.class);

            intent.putExtra(Constants.CITY_KEY, currentCity);
            startActivity(intent);
        }

    }
}
