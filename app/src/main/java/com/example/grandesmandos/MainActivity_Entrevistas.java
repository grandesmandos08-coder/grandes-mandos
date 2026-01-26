package com.example.grandesmandos;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.grandesmandos.entrevistas.CustomAdapterEntrev;
import com.example.grandesmandos.entrevistas.DbEntrevistas;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity_Entrevistas extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton add_entrevista;

    DbEntrevistas myDb;
    ArrayList<String> entrev_id, entrev_carpeta, entrev_nombre, entrev_domicilio, entrev_telefono, entrev_entrevista;
    CustomAdapterEntrev customAdapterEntrev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_entrevistas);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.teal_700)));

        recyclerView = findViewById(R.id.recyclerView);
        add_entrevista = findViewById(R.id.add_entrevista);

        add_entrevista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_Entrevistas.this, Add_Entrevistas.class);
                startActivity(intent);

            }
        });


        myDb = new DbEntrevistas(MainActivity_Entrevistas.this);
       entrev_id = new ArrayList<>();
       entrev_carpeta = new ArrayList<>();
       entrev_nombre = new ArrayList<>();
       entrev_domicilio = new ArrayList<>();
       entrev_telefono = new ArrayList<>();
       entrev_entrevista = new ArrayList<>();

       storeDataInArrays();

       customAdapterEntrev = new CustomAdapterEntrev(MainActivity_Entrevistas.this, this, entrev_id, entrev_carpeta,entrev_nombre, entrev_domicilio,entrev_telefono,
               entrev_entrevista);

       recyclerView.setAdapter(customAdapterEntrev);
       recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity_Entrevistas.this));



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1){
            recreate();

        }
    }

    void storeDataInArrays(){
        Cursor cursor = myDb.readAllData();

        if(cursor.getCount() == 0 ){
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();

        }else{
            while (cursor.moveToNext()){
                entrev_id.add(cursor.getString(0));
                entrev_carpeta.add(cursor.getString(1));
                entrev_nombre.add(cursor.getString(2));
                entrev_domicilio.add(cursor.getString(3));
                entrev_telefono.add(cursor.getString(4));
                entrev_entrevista.add(cursor.getString(5));

            }
        }


    }
}