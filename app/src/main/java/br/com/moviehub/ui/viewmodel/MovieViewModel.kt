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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch


class MovieViewModel(private val repository: MovieRepository) : ViewModel() {
    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = _movies

    private val _searchQuery = MutableStateFlow("")
    val searchQuery get() = _searchQuery

    init {
        // Observa o Flow de busca com debounce
        viewModelScope.launch {
            _searchQuery
                .debounce(300) // Aguarda 300ms após o último caractere digitado
                .distinctUntilChanged() // Evita a mesma busca se o texto não mudou
                .collect { query ->
                    if (query.isNotEmpty()) {
                        searchMovies(query)
                    } else {
                        fetchMovies(MovieCategory.POPULAR)
                    }
                }
        }
    }

    fun fetchMovies(category: MovieCategory) {
        viewModelScope.launch {
            val response = repository.getMovies(category = category)
            _movies.postValue(response.results)
        }
    }
    fun searchMovies(query: String) {
        viewModelScope.launch {
            if (query.isNotEmpty()) {
                val response = repository.searchMovies(query = query)
                _movies.postValue(response.results)
            } else {
                // Se a busca estiver vazia, volte a mostrar os filmes populares
                fetchMovies(MovieCategory.POPULAR)
            }
        }
    }
    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
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
