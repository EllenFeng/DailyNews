package com.example.lenovo_pc.dailynews;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class HomeFragment extends Fragment{
    private Button b1;
    private Button b2;
    private Button b3;
    private Button b4;
    private Button b5;
    private Button b6;
    public static HomeFragment newInstance(){
        HomeFragment newFragment = new HomeFragment();
        return  newFragment;
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_frag, container, false);
        return view;
    }
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        b1 = getView().findViewById(R.id.btn_domestic);
        b2 = getView().findViewById(R.id.interational);
        b3 = getView().findViewById(R.id.military);
        b4 = getView().findViewById(R.id.culture);
        b5 = getView().findViewById(R.id.economics);
        b6 = getView().findViewById(R.id.technology);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent domes = new Intent(getActivity(), DomesticNews.class);
                startActivity(domes);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inter = new Intent(getActivity(), InternationalNews.class);
                startActivity(inter);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mil = new Intent(getActivity(), MilitaryNews.class);
                startActivity(mil);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cul = new Intent(getActivity(), CulturalNews.class);
                startActivity(cul);
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent eco = new Intent(getActivity(), EconomicNews.class);
                startActivity(eco);
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tech = new Intent(getActivity(), TechNews.class);
                startActivity(tech);
            }
        });

    }


}
