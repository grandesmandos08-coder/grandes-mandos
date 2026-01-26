package com.example.grandesmandos.entrevistas;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.grandesmandos.R;
import com.example.grandesmandos.Update_Activity_Entrevista;

import java.util.ArrayList;

public class CustomAdapterEntrev extends RecyclerView.Adapter<CustomAdapterEntrev.MyViewHolder> {

    private Context context;

    Activity activity;

    private ArrayList entrev_id, entrev_carpeta, entrev_nombre, entrev_domicilio, entrev_telefono, entrev_entrevista;



    public CustomAdapterEntrev(Activity activity, Context context, ArrayList entrev_id, ArrayList entrev_carpeta, ArrayList entrev_nombre,
                               ArrayList entrev_domicilio, ArrayList entrev_telefono, ArrayList entrev_entrevista){
        this.activity = activity;
        this.context = context;
        this.entrev_id = entrev_id;
        this.entrev_carpeta = entrev_carpeta;
        this.entrev_nombre = entrev_nombre;
        this.entrev_domicilio = entrev_domicilio;
        this.entrev_telefono = entrev_telefono;
        this.entrev_entrevista = entrev_entrevista;


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_arrow_entrev, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {


        holder.text_id.setText(String.valueOf(entrev_id.get(position)));
        holder.text_carpeta.setText(String.valueOf(entrev_carpeta.get(position)));
        holder.text_nombre.setText(String.valueOf(entrev_nombre.get(position)));
        holder.text_domicilio.setText(String.valueOf(entrev_domicilio.get(position)));
        holder.text_telefono.setText(String.valueOf(entrev_telefono.get(position)));
        holder.text_entrevista.setText(String.valueOf(entrev_entrevista.get(position)));






        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Update_Activity_Entrevista.class);
                intent.putExtra("id", String.valueOf(entrev_id.get(holder.getAdapterPosition())));
                intent.putExtra("carpeta", String.valueOf(entrev_carpeta.get(holder.getAdapterPosition())));
                intent.putExtra("nombre", String.valueOf(entrev_nombre.get(holder.getAdapterPosition())));
                intent.putExtra("domicilio", String.valueOf(entrev_domicilio.get(holder.getAdapterPosition())));
                intent.putExtra("telefono", String.valueOf(entrev_telefono.get(holder.getAdapterPosition())));
                intent.putExtra("entrevista", String.valueOf(entrev_entrevista.get(holder.getAdapterPosition())));

                activity.startActivityForResult(intent, 1);

            }
        });


    }

    @Override
    public int getItemCount() {
        return entrev_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView text_id, text_carpeta, text_nombre, text_domicilio, text_telefono, text_entrevista;
        LinearLayout  mainLayout;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            text_id =itemView.findViewById(R.id.text_id);
            text_carpeta = itemView.findViewById(R.id.text_carpeta);
            text_nombre = itemView.findViewById(R.id.text_nombre);
            text_domicilio = itemView.findViewById(R.id.text_domicilio);
            text_telefono = itemView.findViewById(R.id.text_telefono);
            text_entrevista = itemView.findViewById(R.id.text_entrevista);

            mainLayout = itemView.findViewById(R.id.mainLayout);


        }
    }
}
