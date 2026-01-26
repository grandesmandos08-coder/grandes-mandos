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

import com.example.grandesmandos.notas.CustomAdapterNotas;
import com.example.grandesmandos.notas.DbNotas;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity_Notas extends AppCompatActivity {

    RecyclerView recyclerView_notas;
    FloatingActionButton add_notas;
    DbNotas dbNotas;
    ArrayList<String> nota_id, nota_nombre, nota_fecha, nota_hora, nota_nota;
    CustomAdapterNotas customAdapterNotas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_notas);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.teal_700)));

        recyclerView_notas = findViewById(R.id.recyclerView_notas);
        add_notas = findViewById(R.id.add_notas);



        add_notas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_Notas.this, Agregar_notas.class );
                startActivity(intent);

            }
        });

        dbNotas = new DbNotas(MainActivity_Notas.this);
        nota_id = new ArrayList<>();
        nota_nombre = new ArrayList<>();
        nota_fecha = new ArrayList<>();
        nota_hora = new ArrayList<>();
        nota_nota = new ArrayList<>();

        storeDataInArraysNota();

        customAdapterNotas = new CustomAdapterNotas (MainActivity_Notas.this, this, nota_id, nota_nombre, nota_fecha,
                nota_hora, nota_nota);

        recyclerView_notas.setAdapter(customAdapterNotas);
        recyclerView_notas.setLayoutManager(new LinearLayoutManager(MainActivity_Notas.this));



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


     if (requestCode == 1){

         recreate();


        }
    }

    void storeDataInArraysNota(){
        Cursor cursor = dbNotas.readAllData();

        if(cursor.getCount() == 0 ){
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();

        }else{
            while (cursor.moveToNext()){
                nota_id.add(cursor.getString(0));
                nota_nombre.add(cursor.getString(1));
                nota_fecha.add(cursor.getString(2));
                nota_hora.add(cursor.getString(3));
                nota_nota.add(cursor.getString(4));


            }
        }


    }
}