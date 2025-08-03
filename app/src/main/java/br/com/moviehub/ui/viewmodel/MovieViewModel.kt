package br.com.moviehub.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.moviehub.data.model.Movie
import br.com.moviehub.data.model.MovieCategory
import br.com.moviehub.data.repository.MovieRepository
import kotlinx.coroutines.launch

class MovieViewModel(private val repository: MovieRepository) : ViewModel() {
    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = _movies

    fun fetchMovies(category: MovieCategory) {
        _movies.postValue(listOf<Movie>(Movie(
            id = 1, title = "A procura do nemo", voteAverage = 10.0,
            backdropPath = "",
            genreIds = listOf(1, 2),
            originalLanguage = "",
            originalTitle = "A procura do nemo",
            overview = "",
            popularity = 10.0,
            posterPath = "",
            releaseDate = "2025-08-02",
            voteCount = 100,
        )))
//        viewModelScope.launch {
//            val response = repository.getMovies(category = category)
//            _movies.postValue(response.results)
//        }
    }
}
