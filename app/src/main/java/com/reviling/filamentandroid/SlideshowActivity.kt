package com.reviling.filamentandroid

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.SurfaceView
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.IOException



class SlideshowActivity : AppCompatActivity() {

    var surfaceView: SurfaceView? = null
    var customViewer: CustomViewer = CustomViewer()

//    var counter = 0 ;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slideshow)
        val files = fetchFileNames();
        var counter=intent.getIntExtra("counter",0);
        showslide(counter,files);

    var nextBtn = findViewById<Button>(R.id.next)
    nextBtn.setOnClickListener{
        var intent = Intent(this,SlideshowActivity::class.java);
        intent.putExtra("counter",counter+1);
        startActivity(intent);
//        showslide(counter++,files);
    }

    var prevBtn = findViewById<Button>(R.id.prev)
    prevBtn.setOnClickListener{
        var intent = Intent(this,SlideshowActivity::class.java);
        intent.putExtra("counter",counter-1);
        startActivity(intent);
//        showslide(counter--,files);
    }

    var homeBtn = findViewById<Button>(R.id.home)
    homeBtn.setOnClickListener{
        var intent = Intent(this,HomeActivity::class.java);
        startActivity(intent);
//        showslide(counter--,files);
    }


}

    fun showslide(counter:Int, files:Array<String>){
        var idx = counter %(  files.size);
        if(counter<0){
            idx=files.size-1
        }
        val fileName = files[idx];

        val title = findViewById<TextView>(R.id.filename);
        title.text = fileName
        surfaceView = findViewById<View>(R.id.surface_view) as SurfaceView
        customViewer.run {
            loadEntity()
            setSurfaceView(requireNotNull(surfaceView))

            //directory and model each as param
            loadGlb(this@SlideshowActivity, fileName, fileName)
            //loadEnviroment(this@SlideshowActivity, "venetian_crossroads_2k");
            loadIndirectLight(this@SlideshowActivity, "venetian_crossroads_2k")
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

    fun fetchFileNames():Array<String>{
        try {
            val assetManager = applicationContext.assets
            val modelFiles = assetManager.list("models")

            if (modelFiles != null && modelFiles.isNotEmpty()) {
                return modelFiles;
            } else {
                Log.d(ContentValues.TAG, "No files found in models directory.")
            }
        } catch (e: IOException) {
            Log.e(ContentValues.TAG, "Error accessing assets directory: ${e.message}")
        }
        return emptyArray()
    }
}