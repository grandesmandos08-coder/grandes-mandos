package com.example.grandesmandos;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class MainActivity_Repositorio extends AppCompatActivity implements View.OnClickListener {

    TextView txtpnapr, txtpnt, txtpnpcp, txtgncc, txtpss,txtpvg;

    public static final String PNAPR_URL="https://www.gob.mx/cms/uploads/attachment/file/334174/PROTOCOLO_NACIONAL_DE_ACTUACION_PRIMER_RESPONDIENTE.pdf";
    public static final String PNT_URL="http://admiweb.col.gob.mx/archivos_prensa/banco_img/file_5bf6f6d85f506_Protocolo_Nacional_de_Traslados.pdf";
    public static final String PNPCP_URL="https://transparencia.info.jalisco.gob.mx/sites/default/files/u37/Protocolo%20de%20Polic%C3%ADa%20con%20Capacidades%20para%20Procesar%20el%20Lugar%20de%20la%20Intervenci%C3%B3n.pdf";
    public static final String GNCC_URL="https://www.criminalistasforenses.org.mx/docs/cadena-de-custodia_guia-nacional.pdf";
    public static final String PSS_URL="https://policiaactualizado.com/wp-content/uploads/2018/08/ProtocoloNacionaldeActuacionSeguridadSalas.pdf";
    public static final String PVG_URL="https://www.gob.mx/cms/uploads/attachment/file/614682/DOF_-_PROTOCOLO_NACIONAL_DE_ACTUACI_N_POLICIAL_PARA_LA_ATENCI_N_DE_G_NERO_CONTRA_LAS_MUERES_EN_EL__MBITO_FAMILIAR_VF.pdf";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_repositorio);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.teal_700)));

        txtpnapr= findViewById(R.id.pnapr);
        txtpnt=findViewById(R.id.pnt);
        txtpnpcp=findViewById(R.id.pnpcp);
        txtgncc=findViewById(R.id.gncc);
        txtpss=findViewById(R.id.pss);
        txtpvg=findViewById(R.id.pvg);

        txtpnapr.setOnClickListener(this);
        txtpnt.setOnClickListener(this);
        txtpnpcp.setOnClickListener(this);
        txtgncc.setOnClickListener(this);
        txtpss.setOnClickListener(this);
        txtpvg.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(Intent.ACTION_VIEW);

        switch (v.getId()){

            case R.id.pnapr:
                intent.setData(Uri.parse(PNAPR_URL));
                startActivity(intent);

                break;

            case R.id.pnt:
                intent.setData(Uri.parse(PNT_URL));
                startActivity(intent);

                break;

            case R.id.pnpcp:
                intent.setData(Uri.parse(PNPCP_URL));
                startActivity(intent);

                break;

            case R.id.gncc:
                intent.setData(Uri.parse(GNCC_URL));
                startActivity(intent);

                break;

            case R.id.pss:
                intent.setData(Uri.parse(PSS_URL));
                startActivity(intent);

                break;

            case R.id.pvg:
                intent.setData(Uri.parse(PVG_URL));
                startActivity(intent);

                break;



        }

    }
}