package com.example.pm2e13058;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pm2e13058.config.SQLiteConn;
import com.example.pm2e13058.config.transacciones;
import com.example.pm2e13058.tables.Contactos;

import java.util.ArrayList;

public class ActivityGuardados extends AppCompatActivity {

    SQLiteConn conn;
    ListView listaContactos;
    ArrayList<Contactos> lista;
    ArrayList<String> ArregloContactos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guardados);

        Button btnAtras = (Button) findViewById(R.id.btnAtras);

        btnAtras.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), ActivityInsert.class);
            startActivity(intent);
        });


        /* LISTAR CONTACTOS */
        conn = new SQLiteConn(this, transacciones.NameDataBase, null, 1);
        listaContactos = (ListView) findViewById(R.id.listaContactos);

        ObtenerContactos();

        ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ArregloContactos);
        listaContactos.setAdapter(adp);

        listaContactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ActivityActualizare.class);
                intent.putExtra("objeto", lista.get(position));
                startActivity(intent);
            }
        });
    }

    private void ObtenerContactos() {
        SQLiteDatabase db = conn.getReadableDatabase();
        Contactos list_contactos = null;
        lista = new ArrayList<Contactos>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + transacciones.tablePersonas, null);

        while(cursor.moveToNext()) {
            list_contactos = new Contactos();
            list_contactos.setId(cursor.getInt(0));
            list_contactos.setPais(cursor.getString(1));
            list_contactos.setNombre(cursor.getString(2));
            list_contactos.setTelefono(cursor.getString(3));
            list_contactos.setNota(cursor.getString(4));

            lista.add(list_contactos);
        }

        cursor.close();

        filllist();
    }

    private void filllist() {
        ArregloContactos = new ArrayList<String>();

        for (int i = 0; i < lista.size(); i++) {
            ArregloContactos.add(lista.get(i).getNombre() + " | "
                    + lista.get(i).getTelefono());
        }
    }
}