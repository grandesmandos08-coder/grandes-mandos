package com.example.grandesmandos;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.example.grandesmandos.notas.DbNotas;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Agregar_notas extends AppCompatActivity {
    EditText nombre_colocar, hora_colocar, fecha_colocar, nota_colocar;
    Button boton_agregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_notas);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.teal_700)));

        nombre_colocar = findViewById(R.id.nombre_colocar);
        hora_colocar = findViewById(R.id.hora_colocar);
        fecha_colocar = findViewById(R.id.fecha_colocar);
        nota_colocar = findViewById(R.id.nota_colocar);
        boton_agregar = findViewById(R.id.boton_agregar);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        fecha_colocar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(Agregar_notas.this, new DatePickerDialog.OnDateSetListener() {
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
                TimePickerDialog timePickerDialog = new TimePickerDialog(Agregar_notas.this, new TimePickerDialog.OnTimeSetListener() {
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


        boton_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbNotas dbNotas = new DbNotas(Agregar_notas.this);
                dbNotas.agregarNota(nombre_colocar.getText().toString().trim(),
                        fecha_colocar.getText().toString().trim(),
                        hora_colocar.getText().toString().trim(),
                        nota_colocar.getText().toString().trim());
            }

        });
    }
}