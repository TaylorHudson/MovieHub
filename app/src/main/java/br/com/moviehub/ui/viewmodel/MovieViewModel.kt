package br.com.moviehub.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import br.com.moviehub.data.api.MovieApiInstance
import br.com.moviehub.data.model.Movie
import br.com.moviehub.data.model.MovieCategory
import br.com.moviehub.data.repository.MovieRepository
import kotlinx.coroutines.launch

class MovieViewModel(private val repository: MovieRepository) : ViewModel() {
    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = _movies

    fun fetchMovies(category: MovieCategory) {
        viewModelScope.launch {
            val response = repository.getMovies(category = category)
            _movies.postValue(response.results)
        }
    }

    class Factory() : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
                val repository = MovieRepository(MovieApiInstance.instance)
                val viewModel = MovieViewModel(repository)
                return modelClass.cast(viewModel)!!
            }
            throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }

}
