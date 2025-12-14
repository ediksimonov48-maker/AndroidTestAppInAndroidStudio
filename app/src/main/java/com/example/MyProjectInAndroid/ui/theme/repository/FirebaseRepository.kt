package com.example.MyProjectInAndroid.ui.theme.repository

import com.example.MyProjectInAndroid.ui.theme.data.User

interface FirebaseRepository {
    fun updateUserData(userFirebase: User)
}