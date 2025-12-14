package com.example.MyProjectInAndroid.ui.theme.view

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.MyProjectInAndroid.R
import com.example.MyProjectInAndroid.ui.theme.repository.ApiInterface
import com.example.MyProjectInAndroid.ui.theme.data.ResponseInDB
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.MyProjectInAndroid.ui.theme.viewmodel.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue

@AndroidEntryPoint
class MoviesDetailsActivity : AppCompatActivity() {
    private val mViewModel: MoviesViewModel by viewModels()
    private lateinit var title: TextView
    private lateinit var releaseDate: TextView
    private lateinit var score: TextView
    private lateinit var actors: TextView
    private lateinit var poster: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_details)

        title = findViewById(R.id.movies_details_title)
        releaseDate = findViewById(R.id.movies_details_data)
        score = findViewById(R.id.movies_details_score)
        actors = findViewById(R.id.movies_details_body_Actors)
        poster = findViewById(R.id.movies_details_imageView)

        val id = intent.getStringExtra("id")
        Log.d("id проверка", "$id - айди")

        mViewModel.getDetailsMovie(id, "a31275fa", "title") { titleValue ->
            runOnUiThread {
                title.text = titleValue
            }
        }

        mViewModel.getDetailsMovie(id, "a31275fa", "releaseDate") { dateValue ->
            runOnUiThread {
                releaseDate.text = dateValue
            }
        }

        mViewModel.getDetailsMovie(id, "a31275fa", "score") { scoreValue ->
            runOnUiThread {
                score.text = scoreValue
            }
        }

        mViewModel.getDetailsMovie(id, "a31275fa", "actors") { actorsValue ->
            runOnUiThread {
                actors.text = actorsValue
            }
        }

        mViewModel.getDetailsMovie(id, "a31275fa", "poster") { posterUrl ->
            runOnUiThread {
                Glide.with(this).load(posterUrl).into(poster)
            }
        }
    }
}