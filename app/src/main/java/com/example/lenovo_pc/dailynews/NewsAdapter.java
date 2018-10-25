package com.example.lenovo_pc.dailynews;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NewsAdapter extends ArrayAdapter {
    public NewsAdapter(@NonNull Context context, int resource, ArrayList<HashMap<String, String>>list) {
        super(context, resource, list);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        if(itemView == null){
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

        Map<String,String> map = (Map<String, String>) getItem(position);
        TextView title = (TextView) itemView.findViewById(R.id.itemTitle);
        TextView origin = (TextView) itemView.findViewById(R.id.itemOrigin);

        title.setText("Title:" + map.get("ItemTitle"));
        origin.setText("detail:" + map.get("ItemOrigin"));

        return itemView;
    }
}
