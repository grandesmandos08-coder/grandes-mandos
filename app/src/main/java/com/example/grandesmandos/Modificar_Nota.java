package com.example.grandesmandos;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.grandesmandos.notas.DbNotas;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Modificar_Nota extends AppCompatActivity {
    EditText nombre_colocar, fecha_colocar, hora_colocar, nota_colocar;

    FloatingActionButton boton_editar;
    String id, nombre, fecha, hora, nota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_nota);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.teal_700)));

        nombre_colocar = findViewById(R.id.nombre_colocar2);
        fecha_colocar = findViewById(R.id.fecha_colocar2);
        hora_colocar = findViewById(R.id.hora_colocar2);
        nota_colocar = findViewById(R.id.nota_colocar2);
        boton_editar = findViewById(R.id.boton_editar);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        fecha_colocar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(Modificar_Nota.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month+1;
                        String date= day+"/"+month+"/"+year;
                        fecha_colocar.setText(date);

                    }
                },year,month,day);
                dialog.show();
            }
        });

        hora_colocar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(Modificar_Nota.this, new TimePickerDialog.OnTimeSetListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(0,0, 0, hourOfDay,minute);

                        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("hh:mm aa");
                        hora_colocar.setText( sdf.format(calendar.getTime()));

                    }
                },15,0,false);

                timePickerDialog.show();
            }
        });



        getAndSetIntentDataNota();

        ActionBar ab = getSupportActionBar();
        if(ab != null){

        }
        ab.setTitle(nombre);

        boton_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbNotas dbNotas = new DbNotas(Modificar_Nota.this);
                nombre = nombre_colocar.getText().toString().trim();
                fecha = fecha_colocar.getText().toString().trim();
                hora = hora_colocar.getText().toString().trim();
                nota = nota_colocar.getText().toString().trim();
                dbNotas.updateDataNotas(id,nombre,fecha,hora,nota);
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

    void getAndSetIntentDataNota(){
        if (getIntent().hasExtra("id") && getIntent().hasExtra("nombre") &&
                getIntent().hasExtra("fecha") && getIntent().hasExtra("hora")
                && getIntent().hasExtra("nota")) {

            id = getIntent().getStringExtra("id");
            nombre = getIntent().getStringExtra("nombre");
            fecha = getIntent().getStringExtra("fecha");
            hora = getIntent().getStringExtra("hora");
            nota = getIntent().getStringExtra("nota");

            nombre_colocar.setText(nombre);
            fecha_colocar.setText(fecha);
            hora_colocar.setText(hora);
            nota_colocar.setText(nota);


        }else{
            Toast.makeText(this, "No existe registro", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog (){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("¿Borrar evento  " + nombre + " ?");
        builder.setMessage("¿Quieres borrar el evento de " + nombre + " ?");

        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DbNotas dbNotas = new DbNotas(Modificar_Nota.this);
                dbNotas.deleteBoton(id);
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