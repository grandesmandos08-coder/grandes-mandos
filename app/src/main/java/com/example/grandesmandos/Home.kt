package com.example.grandesmandos

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.navigation.fragment.findNavController


class Home : Fragment() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }
    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
      val view= inflater.inflate(R.layout.fragment_home, container, false)
        val carprot:CardView= view.findViewById(R.id.Protocoloscard)
        val carbit:CardView= view.findViewById(R.id.bitacoracard)
        val carentrev:CardView= view.findViewById(R.id.entrevistacard)
        val carinf:CardView= view.findViewById(R.id.informescard)

        carprot.setOnClickListener{
            val intent1 = Intent(context, MainActivity_Repositorio::class.java)
            startActivity(intent1)
        }
        carbit.setOnClickListener{
            val intent1 = Intent(context, MainActivity_Notas::class.java)
            startActivity(intent1)
        }
        carentrev.setOnClickListener{
            val intent1 = Intent(context, MainActivity_Entrevistas::class.java)
            startActivity(intent1)
        }
        carinf.setOnClickListener{
            val intent1 = Intent(context, MainActivity_Informes::class.java)
            startActivity(intent1)
        }




        return view
    }



}