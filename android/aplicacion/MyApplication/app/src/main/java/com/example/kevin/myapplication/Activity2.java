package com.example.kevin.myapplication;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class Activity2 extends ActionBarActivity  {

    private MediaPlayer mp;
    private Button botonSonido, btnstopSonido, btnAbrir;
    private static final int ABRIRFICHERO_RESULT_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity2);
       //Creamos el reproductor y cargamos audio
        StarReproductor();
        //Declaracion Stop
        StopReproductor();
        btnAbrir = (Button) findViewById(R.id.btnAbrir);
        btnAbrir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("file/*.mp3");
                    startActivityForResult(intent, ABRIRFICHERO_RESULT_CODE);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Upps!! - No hay archivo a reproducir", Toast.LENGTH_SHORT).show();
                };
            }
        });


    }


    public void StarReproductor(){
        botonSonido = (Button) findViewById(R.id.btnSonido);
        botonSonido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (mp==null) {
                        mp = MediaPlayer.create(Activity2.this, R.raw.keshatog);
                    }
                    if (mp.isPlaying()){
                        mp.pause();
                    }else {
                        mp.start();
                    }
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Upps!! - No hay archivo a reproducir", Toast.LENGTH_SHORT).show();
                };
            }
        });
    }
    public void StopReproductor(){
        btnstopSonido= (Button) findViewById(R.id.btnDetenerAudio);
        btnstopSonido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mp.stop();
                    mp = null;
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Upps!!", Toast.LENGTH_SHORT).show();
                };
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Toast.makeText(getApplicationContext(), "id:!! " + id, Toast.LENGTH_SHORT).show();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case ABRIRFICHERO_RESULT_CODE:
                if (resultCode == RESULT_OK) {

                    // Mostramos por pantalla la ruta del archivo seleccionado.
                    String ruta = data.getData().getPath();
                    //txtInfo.setText(ruta);
                    Toast.makeText(getApplicationContext(), "ruta!!" + ruta, Toast.LENGTH_SHORT).show();
                }
        }
    }


}
