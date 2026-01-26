package com.example.grandesmandos;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.grandesmandos.entrevistas.DbEntrevistas;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Update_Activity_Entrevista extends AppCompatActivity {

    EditText carpeta_input, nombre_input,domicilio_input, telefono_input, entrevista_input;
    FloatingActionButton boton_editar;
    String  id, carpeta, nombre, domicilio, telefono, entrevista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_entrevista);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.teal_700)));

        carpeta_input = findViewById(R.id.carpeta_input_update);
        nombre_input = findViewById(R.id.nombre_input_update);
        domicilio_input = findViewById(R.id.domicilio_input_update);
        telefono_input = findViewById(R.id.telefono_input_update);
        entrevista_input = findViewById(R.id.entrevista_input_update);
        boton_editar = findViewById(R.id.boton_editar_entrev);


        //Primera llamada
        getAndSetIntentData();

        //Enviar titulo de la barra de getAndSet
        ActionBar ab = getSupportActionBar();
        if(ab != null){

        }
        ab.setTitle(nombre);

        boton_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbEntrevistas myDb = new DbEntrevistas(Update_Activity_Entrevista.this);
                carpeta = carpeta_input.getText().toString().trim();
                nombre = nombre_input.getText().toString().trim();
                domicilio = domicilio_input.getText().toString().trim();
                telefono = telefono_input.getText().toString().trim();
                entrevista = entrevista_input.getText().toString().trim();

                myDb.updateData(id,carpeta,nombre,domicilio,telefono,entrevista);
                finish();

            }
        });





    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.menu_eliminar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.eliminar_entrev:
                confirmDialog();

                break;
            default:
                throw new IllegalStateException("Unexpected value: " + item.getItemId());
        }


        return super.onOptionsItemSelected(item);

    }



    void getAndSetIntentData(){
        if (getIntent().hasExtra("id") && getIntent().hasExtra("carpeta") &&
                getIntent().hasExtra("nombre") && getIntent().hasExtra("domicilio")
        && getIntent().hasExtra("telefono") && getIntent().hasExtra("entrevista")){

            //Obtener base desde el Intent
            id = getIntent().getStringExtra("id");
            carpeta = getIntent().getStringExtra("carpeta");
            nombre = getIntent().getStringExtra("nombre");
            domicilio = getIntent().getStringExtra("domicilio");
            telefono = getIntent().getStringExtra("telefono");
            entrevista = getIntent().getStringExtra("entrevista");

            //Enviar base

            carpeta_input.setText(carpeta);
            nombre_input.setText(nombre);
            domicilio_input.setText(domicilio);
            telefono_input.setText(telefono);
            entrevista_input.setText(entrevista);


        }else{
            Toast.makeText(this, "No existe registro", Toast.LENGTH_SHORT).show();

        }
    }

    void confirmDialog (){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("¿Borrar entrevista  " + nombre + " ?");
        builder.setMessage("¿Quieres borrar la entrevista de " + nombre + " ?");
        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DbEntrevistas db = new DbEntrevistas(Update_Activity_Entrevista.this);
                db.deleteBoton(id);
                finish();


            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.create().show();

    }
}