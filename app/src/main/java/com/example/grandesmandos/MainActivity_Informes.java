package com.example.grandesmandos;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity_Informes extends AppCompatActivity implements View.OnClickListener {

    TextView txtiphdel, txtiphedit, txtiphlin, txtiphfc,txtiphgfc, txtrcc, txtrccedit;

    public static final String IPH_DEL="https://www.gob.mx/cms/uploads/attachment/file/527373/IPH-DELITOS.pdf";
    public static final String IPH_EDIT="https://drive.google.com/file/d/1urcI6Jylu5KjPNMdOnaixLc-ILzmgNN_/view?usp=sharing";
    public static final String IPH_LIN="https://www.gob.mx/cms/uploads/attachment/file/527372/LINEAMIENTOS_INFORME_POLICIAL_HOMOLOGADO__IPH_.pdf";
    public static  final String IPH_FC="https://www.gob.mx/cms/uploads/attachment/file/527371/IPH-JUSTICIA_CIVICA.pdf";
    public static  final String IPH_GFC="https://www.gob.mx/cms/uploads/attachment/file/394019/Gu_a_IPH_Infracciones_Administrativas.pdf";
    public static  final String RCC="https://drive.google.com/file/d/1l94ZBr5LNmwqvSLZwr4dmMaN81_a7fix/view?usp=sharing";
    public static  final String RCC_EDIT="https://drive.google.com/file/d/1PIsq3oKil9y8xms4T00HJcxQUSvyzvym/view?usp=sharing";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_informes);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.teal_700)));

        txtiphdel = findViewById(R.id.iphdel);
        txtiphedit = findViewById(R.id.iphedit);
        txtiphlin = findViewById(R.id.iphlin);
        txtiphfc = findViewById(R.id.iphfc);
        txtiphgfc= findViewById(R.id.iphguiafc);
        txtrcc = findViewById(R.id.rcc);
        txtrccedit = findViewById(R.id.rccedit);

        txtiphdel.setOnClickListener(this);
        txtiphedit.setOnClickListener(this);
        txtiphlin.setOnClickListener(this);
        txtiphfc.setOnClickListener(this);
        txtiphgfc.setOnClickListener(this);
        txtrcc.setOnClickListener(this);
        txtrccedit.setOnClickListener(this);





    }

    @SuppressLint("NonConstantResourceId")
    public void onClick(View v) {

        Intent intent = new Intent(Intent.ACTION_VIEW);

        switch (v.getId()){

            case R.id.iphdel:
                intent.setData(Uri.parse(IPH_DEL));
                startActivity(intent);

                break;

            case R.id.iphedit:
                intent.setData(Uri.parse(IPH_EDIT));
                startActivity(intent);

                break;

            case R.id.iphlin:
                intent.setData(Uri.parse(IPH_LIN));
                startActivity(intent);

                break;

            case R.id.iphfc:
                intent.setData(Uri.parse(IPH_FC));
                startActivity(intent);

                break;

            case R.id.iphguiafc:
                intent.setData(Uri.parse(IPH_GFC));
                startActivity(intent);

                break;



            case R.id.rcc:
                intent.setData(Uri.parse(RCC));
                startActivity(intent);

                break;

            case R.id.rccedit:
                intent.setData(Uri.parse(RCC_EDIT));
                startActivity(intent);

                break;

        }

    }
}