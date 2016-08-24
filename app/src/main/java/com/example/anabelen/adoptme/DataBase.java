package com.example.anabelen.adoptme;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ana Belen on 20/08/2016.
 */
public class DataBase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Usuarios.db";
    public static final String TABLE_NAME = "tabla_usuarios";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NOMBRE";
    public static final String COL_3 = "APELLIDO";
    public static final String COL_4 = "EDAD";
    public static final String COL_5 = "EMAIL";
    public static final String COL_6 = "USUARIO";
    public static final String COL_7 = "CONTRASENA";
    public static final String COL_8 = "GENERO";


    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("crear tabla " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,NOMBRE TEXT,APELLIDO TEXT,EDAD INTEGER,EMAIL TEXT,USUARIO TEXT,CONTRASENA TEXT,GENERO TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean ingresarDatos(String nombre,String apellido,Integer edad,String email,String usuario,String contrasena,String genero)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,nombre);
        contentValues.put(COL_3,apellido);
        contentValues.put(COL_4,edad);
        contentValues.put(COL_5,email);
        contentValues.put(COL_6,usuario);
        contentValues.put(COL_7,contrasena);
        contentValues.put(COL_8,genero);

        long result = db.insert(TABLE_NAME,null,contentValues);

        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }
}
