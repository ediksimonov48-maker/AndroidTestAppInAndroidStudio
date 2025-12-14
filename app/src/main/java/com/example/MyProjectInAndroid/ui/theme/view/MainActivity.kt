package com.example.MyProjectInAndroid.ui.theme.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import com.example.MyProjectInAndroid.R
import com.example.MyProjectInAndroid.ui.theme.data.User
import com.example.MyProjectInAndroid.ui.theme.viewmodel.MainActivityViewModel
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database
import dagger.hilt.android.AndroidEntryPoint

// этот экран реализует экран регистрации блягодаря FireBase
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mMainActivityViewModel: MainActivityViewModel by viewModels()
    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract(),
    ) { res ->
        this.onSignInResult(res)// регестрируем результат аунтификации
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openRegistrationScreen()
        }
    private fun openRegistrationScreen(){
        val intentToAnotherScreen = Intent(this, MoviesActivity::class.java)
        startActivity(intentToAnotherScreen)
        // создание списка возможных авторизаций (в данном случае только почта)
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            //настройка провайдера аунтификации по почте
        )

        // создание настройки подключения
        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .setTheme(R.style.Theme_AppCompat_Light_NoActionBar)
            .build()
        signInLauncher.launch(signInIntent)
    }

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        val response = result.idpResponse
        if (result.resultCode == RESULT_OK) {
            // Успешно выполнен вход в систему БД вернула -1
            val authUser = FirebaseAuth.getInstance().currentUser
            val user = authUser?.let {
                val firebaseUser = User(it.email.toString(), it.uid)
                mMainActivityViewModel.updateUserData(firebaseUser)
                    // передали данные в БД и выполнили Переход на главный экран
                    startActivity(Intent(this, MoviesActivity::class.java))
                    finish()
                }
            }
         else {
            Toast.makeText(this, "ошибка с подключением", Toast.LENGTH_SHORT).show()
        }
    }
}
