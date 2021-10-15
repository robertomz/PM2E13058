package com.example.pm2e13058;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pm2e13058.config.SQLiteConn;
import com.example.pm2e13058.config.transacciones;

public class ActivityInsert extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner pais;
    EditText nombre, telefono, nota;

    String codigoPais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        Button btnSalvados = (Button) findViewById(R.id.btnSalvados);

        btnSalvados.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), ActivityGuardados.class);
            startActivity(intent);
        });

        /* INICIO COGIDO SPINNER */
        Spinner txtPais = findViewById(R.id.txtPais);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.paises, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        txtPais.setAdapter(adapter);
        txtPais.setOnItemSelectedListener(this);
        /* FIN COGIDO SPINNER */


        /* OBTENER Y GUARDAR DATOS */
        pais = (Spinner) findViewById(R.id.txtPais);
        nombre = (EditText) findViewById(R.id.txtNombre);
        telefono = (EditText) findViewById(R.id.txtTelefono);
        nota = (EditText) findViewById(R.id.txtNota);

        Button btnGuardar = (Button) findViewById(R.id.btnGuardar);

        btnGuardar.setOnClickListener(v -> GuardarContacto());
    }

    private void GuardarContacto() {
        SQLiteConn conn = new SQLiteConn(this, transacciones.NameDataBase, null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues valores = new ContentValues();

        valores.put(transacciones.pais, codigoPais);
        valores.put(transacciones.nombre, nombre.getText().toString());
        valores.put(transacciones.telefono, codigoPais+telefono.getText().toString());
        valores.put(transacciones.nota, nota.getText().toString());

        Long result = db.insert(transacciones.tablePersonas, transacciones.id, valores);

        Toast.makeText(getApplicationContext(), "Registro Ingresado: " + result.toString(), Toast.LENGTH_LONG).show();

        db.close();

        Clean();
    }

    private void Clean() {
        nombre.setText("");
        telefono.setText("");
        nota.setText("");
    }

    /* FUNCIONES DEL SPINNER */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();

        if (pais.getSelectedItem().equals("Honduras (504)")) {
            codigoPais = "504";
        }
        else if (pais.getSelectedItem().equals("Costa Rica (506)")) {
            codigoPais = "506";
        }
        else if (pais.getSelectedItem().equals("Guatemala (502)")) {
            codigoPais = "502";
        }
        else if (pais.getSelectedItem().equals("El Salvador (503)")) {
            codigoPais = "503";
        }

        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}
}