package com.example.MyProjectInAndroid.ui.theme.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.MyProjectInAndroid.R
import com.example.MyProjectInAndroid.ui.theme.view.adapters.Adapter
import com.example.MyProjectInAndroid.ui.theme.viewmodel.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.String

@AndroidEntryPoint
class MoviesActivity : AppCompatActivity() {
    private val mViewModel: MoviesViewModel by viewModels()
    private val films = listOf(
        "tt0111161", "tt0068646", "tt0071562", "tt0110912", "tt0137523",
        "tt0060196", "tt0108052", "tt0110357", "tt0102926", "tt0114369",
        "tt0063350", "tt0107290", "tt0114709", "tt0109830", "tt0080684",
        "tt0116728", "tt0110413", "tt0118715", "tt0119116", "tt0088763"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)
        val itemClick = object : Adapter.ItemClickListener {
            override fun onItemClick(id: String) {
                val intent = Intent(this@MoviesActivity, MoviesDetailsActivity::class.java)
                intent.putExtra("id", id)
                startActivity(intent)
            }}
        val adapter = initViewAndGetAdapter(itemClick)
        mViewModel.getMovies(films, "a31275fa") { movies ->
            adapter.addItems(movies)
        }
    }
    private fun initViewAndGetAdapter(itemClick: Adapter.ItemClickListener): Adapter{
        val recyclerView = findViewById<RecyclerView>(R.id.recycleView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = Adapter(
            arrayListOf(), itemClick
        )
        recyclerView.adapter = adapter
        return adapter
    }
}