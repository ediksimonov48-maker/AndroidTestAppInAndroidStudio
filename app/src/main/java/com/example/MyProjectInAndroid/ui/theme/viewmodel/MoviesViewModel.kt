package com.example.MyProjectInAndroid.ui.theme.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.MyProjectInAndroid.ui.theme.data.ResponseInDB
import com.example.MyProjectInAndroid.ui.theme.repository.MoviesDBRepository
import com.example.MyProjectInAndroid.ui.theme.repository.MoviesDBRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
 class MoviesViewModel @Inject constructor(
    private val mMoviesRepository: MoviesDBRepository
): ViewModel(){
   fun getMovies(moviesId: List<String>, apiKey: String, callBack:(ArrayList<ResponseInDB>) -> Unit) {
        mMoviesRepository.getMovies(moviesId,apiKey, callBack)
    }
    fun getDetailsMovie(movieId: String?, apiKey: String, fieldName: String, callback: (String) -> Unit) {
        mMoviesRepository.getMovie(movieId, apiKey) { film ->
            val value = when(fieldName) {
                "title" -> film.Title ?: ""
                "actors" -> film.Actors ?: ""
                "score" -> film.Metascore ?: ""
                "releaseDate" -> film.Year ?: ""
                "poster" -> film.Poster ?: ""
                else -> ""
            }
            callback(value)
        }
    }
}