package com.example.laptop.interfaz;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by Karen on 08/11/2015.
 */
public class AdaptadorTitulares extends ArrayAdapter  {
    Activity context;
    Titular[] datos;
    private int selectitem;

    public AdaptadorTitulares(Activity context, Titular[] datos ){
        super(context, R.layout.layout,datos);
        this.datos=datos;
        this.context=context;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        LayoutInflater inflater = context.getLayoutInflater();
       final View item= inflater.inflate(R.layout.layout, null);

        TextView titulo = (TextView) item.findViewById(R.id.titulo);
        titulo.setText(datos[position].getTitulo());
        TextView desc=(TextView) item.findViewById(R.id.desc);
        desc.setText(datos[position].getDescripcion());
        ImageView imagen = (ImageView) item.findViewById(R.id.imageView);
        imagen.setImageResource(datos[position].getImg());

        return item;
    }
     public int getItem(){

         return  selectitem;
     }

}
