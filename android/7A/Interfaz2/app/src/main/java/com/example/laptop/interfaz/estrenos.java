package com.example.laptop.interfaz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class estrenos extends AppCompatActivity {

    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estrenos);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        lista =(ListView) findViewById(R.id.lista);
        Titular[] args = new Titular[]{
                new Titular("Titulo 1" , "Hola breve descripcion", R.drawable.h7),
                new Titular("Titulo 1" , "Hola breve descripcion", R.drawable.insi),
                new Titular("Titulo 1" , "Hola breve descripcion", R.drawable.insur),
                new Titular("Titulo 1" , "Hola breve descripcion", R.drawable.minions),
                new Titular("Titulo 1" , "Hola breve descripcion", R.drawable.sinsajo),
                new Titular("Titulo 1" , "Hola breve descripcion", R.drawable.toy)
        };
        AdaptadorTitulares adap = new AdaptadorTitulares(this ,args);
        lista.setAdapter(adap);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_estrenos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         finish();
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }
}
