package com.example.juan.tcppsp.Utilidades;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Conexion extends SQLiteOpenHelper {

    public Conexion(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Utilidades.CREAR_TABLA_TIME);
        db.execSQL(Utilidades.CREAR_TABLA_DEFECT);
        db.execSQL(Utilidades.CREAR_TABLA_PROYECTO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Utilidades.NOMBRE_TABLA);
        db.execSQL("DROP TABLE IF EXISTS " + Utilidades.NOMBRE_TABLA_PROYECTOS);
        db.execSQL("DROP TABLE IF EXISTS " + Utilidades.NOMBRE_TABLA_DEFECT);

        onCreate(db);
    }
}
