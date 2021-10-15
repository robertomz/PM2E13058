package com.example.pm2e13058.config;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteConn extends SQLiteOpenHelper {

    public SQLiteConn(Context context,  String dbname, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, dbname, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Creacion de la primera tabla de la base de datos
        db.execSQL(transacciones.CreateTableContacto);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Eliminacion de las tablas al momento de eliminar la info de la bd / BD limpia
        db.execSQL(transacciones.DROPTableContacto);
        onCreate(db);
    }
}
