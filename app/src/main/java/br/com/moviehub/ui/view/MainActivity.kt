package br.com.moviehub.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.moviehub.data.model.MovieCategory
import br.com.moviehub.databinding.ActivityMainBinding
import br.com.moviehub.ui.adapter.MovieAdapter
import br.com.moviehub.ui.viewmodel.MovieViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MovieViewModel
    private lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = MovieAdapter(emptyList())
        binding.recyclerViewMovies.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewMovies.adapter = adapter

        viewModel = ViewModelProvider(this)[MovieViewModel::class.java]

        viewModel.movies.observe(this) { movies ->
            adapter.updateMovies(movies)
        }

        viewModel.fetchMovies(MovieCategory.POPULAR)
    }
}