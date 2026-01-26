package com.example.grandesmandos


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.grandesmandos.Home
import com.example.grandesmandos.Maps
import com.example.grandesmandos.R
import com.example.grandesmandos.databinding.ActivityMainBinding

public class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar:Toolbar= findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        replaceFragment(Home())

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home-> replaceFragment(Home())
                R.id.maps_nav->replaceFragment(Maps())

                else->{

                }

            }
            true
        }

    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager= supportFragmentManager
        val fragmentTransaction= fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_container,fragment)
        fragmentTransaction.commit()
    }

    override fun onCreateOptionsMenu(menu:Menu):Boolean{
        menuInflater.inflate(R.menu.menu_redes, menu)
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_item_face-> CallFace()
            R.id.nav_item_mess-> CallMess()
            R.id.nav_item_you->CallYou()
            R.id.acerca_de->CallAcerca()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun CallFace(){
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("https://www.facebook.com/Grandes.Mandos")
        startActivity(intent)
    }

    private fun CallMess(){
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("https://m.me/101997432683702")
        startActivity(intent)
    }

    private fun CallYou(){
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("https://youtu.be/cSE4cHbJ4Gw");
        startActivity(intent);
    }
    private fun CallAcerca(){
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("https://grandesmandos.blogspot.com/2022/10/pagina-de-grandes-mandos.html");
        startActivity(intent);
    }
}