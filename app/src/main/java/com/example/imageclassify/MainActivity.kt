package com.example.imageclassify

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var selectBtn: Button
    private lateinit var predictBtn: Button
    private lateinit var captureBtn: Button
    private lateinit var result: TextView
    private lateinit var imageView: ImageView
    private var bitmap: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar las vistas
        selectBtn = findViewById(R.id.selectBtn)
        predictBtn = findViewById(R.id.predictBtn)
        captureBtn = findViewById(R.id.captureBtn)
        result = findViewById(R.id.result)
        imageView = findViewById(R.id.imageView)

        // Agregar listeners para los botones
        selectBtn.setOnClickListener {
            val intent = Intent().apply {
                action = Intent.ACTION_GET_CONTENT
                type = "image/*"
            }
            startActivityForResult(intent, 10)
        }

        predictBtn.setOnClickListener {
            // Lógica para predecir la imagen
        }

        captureBtn.setOnClickListener {
            // Lógica para capturar una imagen
        }
    }

    // Sobrescribir onActivityResult para manejar el resultado de la selección de imagen
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 10 && resultCode == RESULT_OK) {
            data?.data?.let { uri ->
                try {
                    bitmap = contentResolver.openInputStream(uri)?.use { inputStream ->
                        BitmapFactory.decodeStream(inputStream)
                    }
                    imageView.setImageBitmap(bitmap)
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }
}
