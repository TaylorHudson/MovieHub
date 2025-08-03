package br.com.moviehub.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.com.moviehub.data.model.MovieCategory
import br.com.moviehub.databinding.ActivityMainBinding
import br.com.moviehub.ui.adapter.MovieListAdapter
import br.com.moviehub.ui.viewmodel.MovieViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MovieViewModel
    private lateinit var adapter: MovieListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = MovieListAdapter()
        binding.recyclerViewMovies.adapter = adapter

        val factory = MovieViewModel.Factory()
        viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]
        viewModel.movies.observe(this) { movies ->
            adapter.update(movies)
        }
        viewModel.fetchMovies(MovieCategory.POPULAR)
    }
}