package com.example.grandesmandos.notas;

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

import com.example.grandesmandos.Modificar_Nota;
import com.example.grandesmandos.R;

import java.util.ArrayList;

public class CustomAdapterNotas extends RecyclerView.Adapter<CustomAdapterNotas.MyViewHolder> {

    private Context context;
    Activity activity;
    private ArrayList nota_id, nota_nombre, nota_fecha, nota_hora, nota_nota;


    public CustomAdapterNotas(Activity activity, Context context, ArrayList nota_id, ArrayList nota_nombre,
                              ArrayList nota_fecha, ArrayList nota_hora, ArrayList nota_nota) {

        this.activity = activity;
        this.context = context;
        this.nota_id = nota_id;
        this.nota_nombre = nota_nombre;
        this.nota_fecha = nota_fecha;
        this.nota_hora = nota_hora;
        this.nota_nota = nota_nota;


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row_notas, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {



        holder.text_id.setText(String.valueOf(nota_id.get(position)));
        holder.text_nombre.setText(String.valueOf(nota_nombre.get(position)));
        holder.text_fecha.setText(String.valueOf(nota_fecha.get(position)));
        holder.text_hora.setText(String.valueOf(nota_hora.get(position)));
        holder.text_nota.setText(String.valueOf(nota_nota.get(position)));

        holder.MainLayoutNotas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, Modificar_Nota.class);
                intent.putExtra("id", String.valueOf(nota_id.get(holder.getAdapterPosition())));
                intent.putExtra("nombre", String.valueOf(nota_nombre.get(holder.getAdapterPosition())));
                intent.putExtra("fecha", String.valueOf(nota_fecha.get(holder.getAdapterPosition())));
                intent.putExtra("hora", String.valueOf(nota_hora.get(holder.getAdapterPosition())));
                intent.putExtra("nota", String.valueOf(nota_nota.get(holder.getAdapterPosition())));
                activity.startActivityForResult(intent, 1);

            }
        });




    }

    @Override
    public int getItemCount() {
        return nota_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView text_id, text_nombre, text_fecha, text_hora, text_nota;
        LinearLayout MainLayoutNotas;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            text_id = itemView.findViewById(R.id.text_id);
            text_nombre = itemView.findViewById(R.id.text_nombre);
            text_fecha = itemView.findViewById(R.id.text_fecha);
            text_hora = itemView.findViewById(R.id.text_hora);
            text_nota = itemView.findViewById(R.id.text_nota);
            MainLayoutNotas = itemView.findViewById(R.id.MainLayoutNotas);

        }
    }
}
