package com.example.MyProjectInAndroid.ui.theme.repository

import android.content.Context
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.MyProjectInAndroid.ui.theme.data.ResponseInDB
import com.example.MyProjectInAndroid.ui.theme.view.adapters.Adapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MoviesDBRepositoryImpl @Inject constructor()
: MoviesDBRepository {
    override fun getMovies(moviesId: List<String>, apiKey: String, callback: (ArrayList<ResponseInDB>) -> Unit
    ): ArrayList<ResponseInDB> {
        val results = arrayListOf<ResponseInDB>()
        var completedRequests = 0

        moviesId.forEach { imdbID ->
            ApiInterface.Companion.create().getMovieByImdbId(imdbID, apiKey)
                .enqueue(object : Callback<ResponseInDB>, Adapter.ItemClickListener {
                    override fun onResponse(
                        call: Call<ResponseInDB>,
                        response: Response<ResponseInDB>
                    ) {
                        if (response.isSuccessful && response.body() != null) {
                            results.add(response.body()!!)
                        }
                        completedRequests++
                        if (completedRequests >= moviesId.size) {
                            callback.invoke(results)
                        }
                    }

                    override fun onFailure(call: Call<ResponseInDB>, t: Throwable) {
                        Log.e(
                            "RetrofitError",
                            "Ошибка при загрузке фильма $imdbID: ${t.localizedMessage}",
                            t
                        )
                        completedRequests++
                        if (completedRequests >= moviesId.size) {
                            callback.invoke(results)
                        }
                    }
                    override fun onItemClick(id: String) {

                    }
                })
        }
        return results
    }

    override fun getMovie(movieId: String?, apiKey: String, callback: (ResponseInDB) -> Unit
    ) {
        ApiInterface.Companion.create().getMovieByImdbId(movieId, apiKey)
            .enqueue(object : Callback<ResponseInDB> {
                override fun onResponse(call: Call<ResponseInDB>, response: Response<ResponseInDB>) {
                    if (response.isSuccessful && response.body() != null) {
                        callback(response.body()!!)
                    }
                }
                override fun onFailure(call: Call<ResponseInDB>, t: Throwable) {
                    Log.e("RetrofitError", "Ошибка при загрузке фильма: ${t.localizedMessage}", t)
                }
            })
    }
}