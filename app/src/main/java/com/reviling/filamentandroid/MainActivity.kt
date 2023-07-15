package com.reviling.filamentandroid

import android.os.Bundle
import android.view.SurfaceView
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    var surfaceView: SurfaceView? = null
    var customViewer: CustomViewer = CustomViewer()

    override fun onCreate(savedInstanceState: Bundle?) {
      try {
          super.onCreate(savedInstanceState)
          setContentView(R.layout.activity_main)
          surfaceView = findViewById<View>(R.id.surface_view) as SurfaceView
          val fileName = intent.getStringExtra("fileName").toString()
          val title = findViewById<TextView>(R.id.filename);
          title.text = fileName
          customViewer.run {
              loadEntity()
              setSurfaceView(requireNotNull(surfaceView))

              //directory and model each as param
              loadGlb(this@MainActivity, fileName, fileName)
              //loadGltf(this@MainActivity, "warcraft", "scene");

              //directory and model as one
              //loadGlb(this@MainActivity, "grogu/grogu");

              //Enviroments and Lightning (OPTIONAL)
              //loadIndirectLight(this@MainActivity, "venetian_crossroads_2k")
              loadEnviroment(this@MainActivity, "venetian_crossroads_2k");
          }
      }
      catch (e:Exception){
          print(e);//todo
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