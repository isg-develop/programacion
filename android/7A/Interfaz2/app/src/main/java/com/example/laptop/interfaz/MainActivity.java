package com.example.laptop.interfaz;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.AdapterView;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.content.Context;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

  ListView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


   lista =(ListView) findViewById(R.id.lista);
        Titular[] args = new Titular[]{
                new Titular("50 Sombras de Grey" , "Descripcion...", R.drawable.de),

                new Titular("Driver" , "Descripcion...", R.drawable.des),
                new Titular("Titulo 1" , "Hola breve descripcion", R.drawable.h7),
                new Titular("Titulo 1" , "Hola breve descripcion", R.drawable.insi),
                new Titular("Titulo 1" , "Hola breve descripcion", R.drawable.insur),
                new Titular("Titulo 1" , "Hola breve descripcion", R.drawable.minions),
                new Titular("Titulo 1" , "Hola breve descripcion", R.drawable.sinsajo),
                new Titular("Titulo 1" , "Hola breve descripcion", R.drawable.toy),
                new Titular("Titulo 1" , "Hola breve descripcion", R.drawable.vengadores)
        };
        AdaptadorTitulares adap = new AdaptadorTitulares(this ,args);
        lista.setAdapter(adap);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
//aquí vendría las acciones que tengo que realizar
            finish();

            return true;

        }

        return super.onKeyDown(keyCode, event);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;

        } return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_cartelera) {


        } else if (id == R.id.nav_estrenos) {
            Intent intent1 = new Intent(this, estrenos.class);
            this.startActivity(intent1);
            return true;
        } else if (id == R.id.nav_promo) {
            Intent intent1 = new Intent(this, promociones.class);
            this.startActivity(intent1);
            return true;
        } else if (id == R.id.nav_ubicacion) {
            Intent intent1 = new Intent(this, Maps.class);
            this.startActivity(intent1);
            return true;
        } else if (id == R.id.nav_quejas) {
            Intent intent1 = new Intent(this, quejas.class);
            this.startActivity(intent1);
            return true;

        }
        else if (id == R.id.nav_config) {
                   }
     else if (id == R.id.nav_share) {

    }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
