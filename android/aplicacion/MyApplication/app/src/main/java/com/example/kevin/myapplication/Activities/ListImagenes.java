package com.example.kevin.myapplication.Activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.kevin.myapplication.Adapters.CustomAdaper;
import com.example.kevin.myapplication.R;

import java.util.LinkedList;
import java.util.List;

public class ListImagenes extends ActionBarActivity {

    private ListView listView;
    private CustomAdaper adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_imagenes);

        listView = (ListView) findViewById(R.id.listView);
        List<String> lista = new LinkedList<String>();
        lista.add("Fernando");
        lista.add("Pedro");
        adapter = new CustomAdaper(this,lista,R.layout.molde_list_view);
        listView.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_imagenes, menu);
        return true;
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
        }

        return super.onOptionsItemSelected(item);
    }
}
