package com.example.pm2e13058.config;

public class transacciones {

    //Nombre de la base de datos
    public static final String NameDataBase = "PM1E13058";

    //Tablas de la base de datos
    public static final String tablePersonas = "Contactos";

    //Campos de la tabla
    public static final String id = "id";
    public static final String pais = "pais";
    public static final String nombre = "nombre";
    public static final String telefono = "telefono";
    public static final String nota = "nota";

    //Transacciones DDL (Data Definition Language) tabla personas
    public static final String CreateTableContacto = "CREATE TABLE Contactos (id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "pais TEXT, nombre TEXT, telefono TEXT, nota TEXT)";

    public static final String DROPTableContacto = "DROP TABLE IF EXISTS Contactos";
}
