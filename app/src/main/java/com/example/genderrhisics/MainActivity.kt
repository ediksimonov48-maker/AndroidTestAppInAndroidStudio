package com.example.genderrhisics

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Инициализация кнопки
        val button_click_registr = findViewById(R.id.activity_main_button_registr) as Button
        // обработчик кнопок
        button_click_registr.setOnClickListener {
            val intent = Intent(this, RegistrActivity::class.java)
            startActivity(intent)
            Toast.makeText(this@MainActivity, "загрузка", Toast.LENGTH_SHORT).show()
        }
    }
}
