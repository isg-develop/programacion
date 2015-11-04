package mx.edu.uth.testsqlite;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
/*
* Creado y  Actualizado por uth.tics.tareas
*/

public class Principal extends ActionBarActivity {

    //Código de envío
    public final static int ADD_REQUEST_CODE = 1;
    //Atributos para datos
    private QuotesDataSource dataSource;
    private SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal_layout);
        Button b1 = (Button) findViewById(R.id.btnValidar);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarConexion();
            }
        });
    }

    public void validarConexion(){
        TextView t1 = (TextView) findViewById(R.id.textView3);
        t1.setText("Iniciando...");
        try {

        //Crear nuevo objeto QuotesDataSource
        dataSource = new QuotesDataSource(this);
        //Iniciando el nuevo Adaptador
        adapter = new SimpleCursorAdapter(
                this,
                android.R.layout.two_line_list_item,
                dataSource.getAllQuotes(),
                new String[]{QuotesDataSource.ColumnQuotes.BODY_QUOTES, QuotesDataSource.ColumnQuotes.AUTHOR_QUOTES},
                new int[]{android.R.id.text1, android.R.id.text2},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER

        );

        ListView list = (ListView) findViewById(R.id.list1);
        list.setAdapter(adapter);
        // Se agrego al listado
        t1.setText("Agregado...");

        }catch (Exception e){
            t1.setText("Falla by..." + e.getMessage() + " -----  AND ------" + e.getLocalizedMessage() + "----" + e.toString());
        }
        return;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
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
