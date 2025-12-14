package com.example.MyProjectInAndroid.ui.theme.repository

import com.example.MyProjectInAndroid.ui.theme.data.User
import com.google.firebase.Firebase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database
import javax.inject.Inject

class FirebaseRepositoryImpl @Inject constructor(): FirebaseRepository {
    private lateinit var database: DatabaseReference// создаем объект типа DatabaseReference

    override fun updateUserData(
        userFirebase: User,
    ) {
        database = Firebase.database("https://androidapp-7a91a-default-rtdb.europe-west1.firebasedatabase.app/").reference// подключение к БД и вставка URL

        database.child("users").child(userFirebase.uid).setValue(userFirebase)

    }

}