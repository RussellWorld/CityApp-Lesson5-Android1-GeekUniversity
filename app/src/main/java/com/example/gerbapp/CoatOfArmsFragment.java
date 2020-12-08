package com.example.gerbapp;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CoatOfArmsFragment extends Fragment {

    public static CoatOfArmsFragment create(City city) {
        CoatOfArmsFragment fragment = new CoatOfArmsFragment();

        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.CITY_KEY, city);
        fragment.setArguments(bundle);
        return fragment;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_coast_of_arms, container, false);
        ImageView coatArms = layout.findViewById(R.id.imageView);
        TextView cityNameView = layout.findViewById(R.id.textView);

        TypedArray imgs = getResources().obtainTypedArray(R.array.coatutofarms_imgs);

        Bundle args = getArguments();

        City city = getCurrentCity();
        if (city != null) {
            coatArms.setImageResource(imgs.getResourceId(city.getImageIndex(), -1));
            cityNameView.setText(city.getCityName());
        }
        return layout;

    }

    public City getCurrentCity() {
        Bundle args = getArguments();
        City city = null;
        if (args != null) {
            city = getArguments().getParcelable(Constants.CITY_KEY);
        }
        return city;

    }
}
