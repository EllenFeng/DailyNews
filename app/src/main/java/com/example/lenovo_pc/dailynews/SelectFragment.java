package com.example.lenovo_pc.dailynews;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SelectFragment extends Fragment{
    public static SelectFragment newInstance(){
        SelectFragment newFragment = new SelectFragment();
        return  newFragment;
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sel_frag, container, false);
        return view;
    }
}
