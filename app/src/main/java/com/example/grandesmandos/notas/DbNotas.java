package com.example.grandesmandos.notas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DbNotas extends SQLiteOpenHelper {
    private  Context context;
    private final static  String DATABASE_NAME = "Notas.db";
    private final static int DATABASE_VERSION =1;

    private static final String TABLE_NAME = "my_notas";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NOMBRE = "nombre";
    private static final String COLUMN_FECHA = "fecha";
    private static final String COLUMN_HORA = "hora";
    private static final String COLUMN_NOTA = "nota";

    public DbNotas(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = " CREATE TABLE " + TABLE_NAME +
                "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NOMBRE + " TEXT, " +
                COLUMN_FECHA + " TEXT, " +
                COLUMN_HORA + " TEXT, " +
                COLUMN_NOTA + " TEXT);";

        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public void agregarNota (String nombre, String fecha, String hora,
                               String nota) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();


        cv.put(COLUMN_NOMBRE, nombre);
        cv.put(COLUMN_FECHA, fecha);
        cv.put(COLUMN_HORA, hora);
        cv.put(COLUMN_NOTA, nota);

        long result = db.insert(TABLE_NAME, null, cv);

        if (result == -1){
            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "EVENTO CREADO!!!", Toast.LENGTH_SHORT).show();
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

    public void updateDataNotas(String row_id, String nombre,
                           String fecha, String hora, String nota) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NOMBRE, nombre);
        cv.put(COLUMN_FECHA, fecha);
        cv.put(COLUMN_HORA, hora);
        cv.put(COLUMN_NOTA, nota);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});

        if(result == -1){
            Toast.makeText(context, "No se pudo modificar el evento", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Evento modificado correctamente", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteBoton (String row_id){
        SQLiteDatabase db = getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id} );

        if(result == -1) {
            Toast.makeText(context, "No se pudo borrar el evento", Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(context, "Evento borrado", Toast.LENGTH_SHORT).show();

        }
    }
}
