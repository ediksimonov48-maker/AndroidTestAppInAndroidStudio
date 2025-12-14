package com.example.MyProjectInAndroid.ui.theme.repository

import com.example.MyProjectInAndroid.ui.theme.data.ResponseInDB
import dagger.Binds

interface MoviesDBRepository{
    fun getMovies(moviesId: List<String>, apiKey: String, callback: (ArrayList<ResponseInDB>) -> Unit): ArrayList<ResponseInDB>

    fun getMovie(moviesId: String?, apiKey: String, callback: (ResponseInDB) -> Unit)
}