package com.example.MyProjectInAndroid.ui.theme.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.MyProjectInAndroid.R
import kotlinx.coroutines.delay

class FirstScreenActivity : AppCompatActivity() {

    private val delay = 500L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_screen)

        lifecycleScope.launchWhenStarted { // Используем корутины для задержки и показа стартового экраны
            delay(delay)
            startActivity(Intent(this@FirstScreenActivity, MainActivity::class.java))// переходим на основной экран
            finish()//красиво закрываем поток
        }
    }
}