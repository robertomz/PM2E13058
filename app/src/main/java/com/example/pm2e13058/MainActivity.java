package com.example.pm2e13058;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnIngresar = (Button) findViewById(R.id.btnIngresar);

        btnIngresar.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), ActivityInsert.class);
            startActivity(intent);
        });
    }
}