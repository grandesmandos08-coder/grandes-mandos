package com.example.grandesmandos;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.grandesmandos.entrevistas.DbEntrevistas;

public class Add_Entrevistas extends AppCompatActivity {
    EditText carpeta_input, nombre_input, domicilio_input, telefono_input, entrevista_input;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entrevistas);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.teal_700)));

        carpeta_input = findViewById(R.id.carpeta_input);
        nombre_input = findViewById(R.id.nombre_input);
        domicilio_input = findViewById(R.id.domicilio_input);
        telefono_input = findViewById(R.id.telefono_input);
        entrevista_input = findViewById(R.id.entrevista_input);
        add_button = findViewById(R.id.add_button);

        add_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                DbEntrevistas myDb = new DbEntrevistas(Add_Entrevistas.this);
                myDb.addEntrevista(carpeta_input.getText().toString().trim(),
                        nombre_input.getText().toString().trim(),
                        domicilio_input.getText().toString().trim(),
                        telefono_input.getText().toString().trim(),
                        entrevista_input.getText().toString().trim());
            }
        });

    }
}