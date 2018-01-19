package com.example.zverek.myapplication;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomArrayAdapter_un_kuran extends BaseAdapter {
    private LayoutInflater inflater;
    private List  kg;
    private List  ru;
    private List  arab;

    public CustomArrayAdapter_un_kuran(Context context, List list, List  list1, List  list2) {
        kg = list;
        ru = list1;
        arab = list2;

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return kg.size();
    }

    @Override
    public Object getItem(int position) {
        return kg.get(position);
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
            v = inflater.inflate(R.layout.custom_list_un_kuran, parent, false);

            holder.txt = (TextView) v.findViewById(R.id.textView30);
            holder.txt1 = (TextView) v.findViewById(R.id.textView33);
            holder.txt2 = (TextView) v.findViewById(R.id.textView34);
            holder.txt3 = (TextView) v.findViewById(R.id.textView35);


            v.setTag(holder);
        }
        int i = position+1;
        holder = (ViewHolder) v.getTag();
        holder.txt.setText(""+i);
        holder.txt1.setText(""+arab.get(position));
        holder.txt2.setText(""+kg.get(position));
        holder.txt3.setText(""+ru.get(position));

        return v;

    }
    private static class ViewHolder {
        private TextView txt;
        private TextView txt1;
        private TextView txt2;
        private TextView txt3;
    }
    @Override
    public boolean isEnabled(int position)
    {
        return true;
    }
}