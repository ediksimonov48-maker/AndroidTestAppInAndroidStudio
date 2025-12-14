package com.example.MyProjectInAndroid.ui.theme.viewmodel

import androidx.lifecycle.ViewModel
import com.example.MyProjectInAndroid.ui.theme.data.User
import com.example.MyProjectInAndroid.ui.theme.repository.FirebaseRepository
import com.example.MyProjectInAndroid.ui.theme.repository.FirebaseRepositoryImpl
import com.google.firebase.Firebase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val fFirebaseRepository: FirebaseRepository
): ViewModel() {
    fun updateUserData(userData: User){
        fFirebaseRepository.updateUserData(userData)
    }
}