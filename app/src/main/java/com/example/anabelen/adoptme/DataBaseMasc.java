package com.example.anabelen.adoptme;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ana Belen on 23/08/2016.
 */
public class DataBaseMasc extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Mascotas.db";
    public static final String TABLE_NAME = "tabla_mascotas";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "FOTO";
    public static final String COL_3 = "TIPO";
    public static final String COL_4 = "EDAD";
    public static final String COL_5 = "RAZA";
    public static final String COL_6 = "ESTADO";
    public static final String COL_7 = "GENERO";


    public DataBaseMasc(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("crear tabla " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,FOTO BLOB,TIPO TEXT,EDAD TEXT,RAZA TEXT,ESTADO TEXT,GENERO TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean ingresarDatosMasc(String tipo,String edad,String raza,String estado,String genero)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_3,tipo);
        contentValues.put(COL_4,edad);
        contentValues.put(COL_5,raza);
        contentValues.put(COL_6,estado);
        contentValues.put(COL_7,genero);

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
