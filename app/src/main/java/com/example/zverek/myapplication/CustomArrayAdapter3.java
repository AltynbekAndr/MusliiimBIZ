package com.example.zverek.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;


public class CustomArrayAdapter3 extends BaseAdapter {
    private LayoutInflater inflater;
    private List list;

    public CustomArrayAdapter3(Context context, List list) {
        this.list = list;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        View v = convertView;
        if ( v == null){
            holder = new ViewHolder();
            v = inflater.inflate(R.layout.custom_list3, parent, false);
            holder.txt1 = (TextView) v.findViewById(R.id.txt1);
            v.setTag(holder);
        }
        holder = (ViewHolder) v.getTag();
        holder.txt1.setText(""+list.get(position));

        return v;

    }
    private static class ViewHolder {
        private TextView txt1;
    }
    @Override
    public boolean isEnabled(int position)
    {
        return true;
    }
}