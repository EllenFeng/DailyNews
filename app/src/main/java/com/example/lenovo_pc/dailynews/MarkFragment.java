package com.example.lenovo_pc.dailynews;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class MarkFragment extends Fragment{
    private ListView lv;

    public static MarkFragment newInstance(){
        MarkFragment newFragment = new MarkFragment();
        return  newFragment;
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mark_frag, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lv=(ListView) getView().findViewById(R.id.marklist);
        lv.setEmptyView((TextView)getView().findViewById(R.id.nomark));
    }
}
