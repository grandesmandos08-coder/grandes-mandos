package com.example.grandesmandos.entrevistas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;


public class DbEntrevistas extends SQLiteOpenHelper {

    private  Context context;
    private final static  String DATABASE_NAME = "Entrevistas.db";
    private final static int DATABASE_VERSION =1;

    private static final String TABLE_NAME = "my_entrevista";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_CARPETA = "carpeta";
    private static final String COLUMN_NOMBRE = "nombre";
    private static final String COLUMN_DOMICILIO = "domicilio";
    private static final String COLUMN_TELEFONO = "telefono";
    private static final String COLUMN_ENTREVISTA = "entrevista";




    public DbEntrevistas(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = " CREATE TABLE " + TABLE_NAME +
                "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_CARPETA + " TEXT, " +
                COLUMN_NOMBRE + " TEXT, " +
                COLUMN_DOMICILIO + " TEXT, " +
                COLUMN_TELEFONO + " TEXT, " +
                COLUMN_ENTREVISTA + " TEXT);" ;

        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addEntrevista (String carpeta, String nombre, String domicilio, String telefono,
                       String entrevista) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_CARPETA, carpeta);
        cv.put(COLUMN_NOMBRE, nombre);
        cv.put(COLUMN_DOMICILIO, domicilio);
        cv.put(COLUMN_TELEFONO, telefono);
        cv.put(COLUMN_ENTREVISTA, entrevista);

        long result = db.insert(TABLE_NAME, null, cv);

        if (result == -1){
            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Entrevista creada", Toast.LENGTH_SHORT).show();
        }

    }

    public Cursor readAllData(){
        String query = " SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;

        if (db  != null){
          cursor = db.rawQuery(query, null);

        }

        return cursor;
    }

    public void updateData(String row_id, String carpeta, String nombre,
                           String domicilio, String telefono, String entrevista){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_CARPETA, carpeta);
        cv.put(COLUMN_NOMBRE, nombre);
        cv.put(COLUMN_DOMICILIO, domicilio);
        cv.put(COLUMN_TELEFONO, telefono);
        cv.put(COLUMN_ENTREVISTA, entrevista);

       long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});

       if(result == -1){
           Toast.makeText(context, "No se pudo modificar la entrevista", Toast.LENGTH_SHORT).show();
       }else{
           Toast.makeText(context, "Entrevista modificada correctamente", Toast.LENGTH_SHORT).show();
       }

    }

   public void deleteBoton(String row_id){
        SQLiteDatabase db = getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id} );

        if(result == -1) {
            Toast.makeText(context, "No se pudo borrar la entrevista", Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(context, "Entrevista borrada", Toast.LENGTH_SHORT).show();

        }
    }

}
