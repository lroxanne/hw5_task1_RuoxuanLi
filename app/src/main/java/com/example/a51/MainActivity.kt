package com.example.a51

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var imageView: ImageView
    private lateinit var editText: EditText
    private var currentImageIndex: Int = 0
    private val images = arrayOf(
        R.drawable.blue ,
        R.drawable.yellow ,
        R.drawable.gradient

    )
    companion object {
        const val PREFS_NAME = "AppPrefs"
        const val IMAGE_KEY = "image"
        const val TEXT_KEY = "text"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageView = findViewById(R.id.imageView)
        editText = findViewById(R.id.editText)

        val button: Button = findViewById(R.id.button)
        val prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
        val savedImageIndex = prefs.getInt(IMAGE_KEY, -1)
        val savedText = prefs.getString(TEXT_KEY, null)
        if (savedImageIndex != -1) {
            currentImageIndex = savedImageIndex
            imageView.setImageResource(images[currentImageIndex])
        }

        if (savedText != null) {
            editText.setText(savedText)
        }

        button.setOnClickListener {
            currentImageIndex = Random.nextInt(images.size)
            imageView.setImageResource(images[currentImageIndex])
            editText.setText("color")
        }
    }
        override fun onDestroy() {
            super.onDestroy()
            val prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit()
            prefs.putInt(IMAGE_KEY, currentImageIndex)
            prefs.putString(TEXT_KEY, editText.text.toString())
            prefs.apply()
        }
    }
