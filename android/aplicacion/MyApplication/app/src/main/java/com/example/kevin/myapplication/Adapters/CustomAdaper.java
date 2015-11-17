package com.example.kevin.myapplication.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.kevin.myapplication.R;

import java.util.List;

/**
 * Created by User on 08/11/2015.
 */
public class CustomAdaper extends BaseAdapter {
    private int layoutMolde;
    private List<String> lista;
    private Activity activity;

     public CustomAdaper(Activity activity, List<String> lista, int layout){
         this.activity = activity;
         this.lista = lista;
         this.layoutMolde = layout;
     }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parentViewGroup) {
        LayoutInflater inflater;
        inflater = activity.getLayoutInflater();
        convertView =inflater.inflate(layoutMolde,null);
        TextView t1= (TextView)convertView.findViewById(R.id.textView);
        t1.setText(lista.get(position));


        return convertView;
    }
}
