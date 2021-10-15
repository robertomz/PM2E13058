package com.example.pm2e13058;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pm2e13058.config.SQLiteConn;
import com.example.pm2e13058.config.transacciones;
import com.example.pm2e13058.tables.Contactos;

import java.net.URL;

public class ActivityActualizare extends AppCompatActivity {

    SQLiteConn conn;
    private Contactos item;
    EditText nombreA, telefonoA, notaA;
    Integer id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizare);

        conn = new SQLiteConn(this, transacciones.NameDataBase, null, 1);

        item = (Contactos) getIntent().getSerializableExtra("objeto");

        nombreA = (EditText) findViewById(R.id.txtNombreA);
        telefonoA = (EditText) findViewById(R.id.txtTelefonoA);
        notaA = (EditText) findViewById(R.id.txtNotaA);

        id = item.getId();
        nombreA.setText(item.getNombre());
        telefonoA.setText(item.getTelefono());
        notaA.setText(item.getNota());

        Button btnAtras2 = (Button) findViewById(R.id.btnAtras2);
        Button btnLlamar = (Button) findViewById(R.id.btnLlamar);
        Button btnActualizar = (Button) findViewById(R.id.btnActualizar);
        Button btnEliminar = (Button) findViewById(R.id.btnEliminar);

        btnAtras2.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), ActivityGuardados.class);
            startActivity(intent);
        });

        btnLlamar.setOnClickListener(view -> {
            int permissionCheck = ContextCompat.checkSelfPermission(
                    this, Manifest.permission.CALL_PHONE);
            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "No se tiene permiso para realizar llamadas telefónicas!", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 225);
            } else {Intent callIntent = new Intent(Intent.ACTION_CALL);

                callIntent.setData(Uri.parse("tel:+"+ item.getTelefono()));
                startActivity(callIntent);
            }
        });

        btnActualizar.setOnClickListener(view -> {
            Actualizar();
        });

        btnEliminar.setOnClickListener(view -> {
            Eliminar();
        });
    }

    private void Actualizar() {
        SQLiteDatabase db = conn.getWritableDatabase();

        String [] params = {
                id.toString()
        };

        ContentValues valores = new ContentValues();
        valores.put(transacciones.nombre, nombreA.getText().toString());
        valores.put(transacciones.telefono, telefonoA.getText().toString());
        valores.put(transacciones.nota, notaA.getText().toString());

        db.update(transacciones.tablePersonas, valores, transacciones.id + "=?", params);

        Toast.makeText(getApplicationContext(), "Contacto Actualizado", Toast.LENGTH_LONG).show();

        Intent intent = new Intent(getApplicationContext(), ActivityGuardados.class);
        startActivity(intent);
    }

    private void Eliminar() {
        SQLiteDatabase db = conn.getWritableDatabase();

        String [] params = {
                id.toString()
        };

        String wherecond = transacciones.id + "=?";

        db.delete(transacciones.tablePersonas, wherecond, params);

        Toast.makeText(getApplicationContext(), "Contacto Eliminado", Toast.LENGTH_LONG).show();

        Intent intent = new Intent(getApplicationContext(), ActivityGuardados.class);
        startActivity(intent);
    }
}