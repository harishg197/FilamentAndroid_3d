package com.reviling.filamentandroid

import android.os.Bundle
import android.util.Log
import android.view.SurfaceView
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import java.io.Console
import java.io.File
import java.io.IOException
import android.content.Intent

import android.widget.Button

class HomeActivity : AppCompatActivity() {

    var surfaceView: SurfaceView? = null
    var customViewer: CustomViewer = CustomViewer()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        surfaceView = findViewById<View>(R.id.surface_view) as SurfaceView
//        val fileName = intent.getStringExtra("fileName").toString()
        customViewer.run {
            loadEntity()
            setSurfaceView(requireNotNull(surfaceView))

            //directory and model each as param
            loadGlb(this@HomeActivity, "grogu", "grogu")
            //loadGltf(this@MainActivity, "warcraft", "scene");

            //directory and model as one
            //loadGlb(this@MainActivity, "grogu/grogu");

            //Enviroments and Lightning (OPTIONAL)
            loadIndirectLight(this@HomeActivity, "venetian_crossroads_2k")
            //loadEnviroment(this@HomeActivity, "venetian_crossroads_2k");
        }



        val btn = findViewById<Button>(R.id.btn3);
        btn.setOnClickListener{
            val intent = Intent(this,pickActivity::class.java) ;
            startActivity(intent);

        }
        val btn5 = findViewById<Button>(R.id.slidebtn);
        btn5.setOnClickListener{
            val intent = Intent(this,SlideshowActivity::class.java) ;
            startActivity(intent);

        }


    }

    override fun onResume() {
        super.onResume()
        customViewer.onResume()
    }

    override fun onPause() {
        super.onPause()
        customViewer.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        customViewer.onDestroy()
    }
}