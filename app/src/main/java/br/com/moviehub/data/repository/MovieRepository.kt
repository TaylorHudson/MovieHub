package br.com.moviehub.data.repository

import br.com.moviehub.data.api.MovieApi
import br.com.moviehub.data.model.MovieCategory

class MovieRepository(private val api: MovieApi) {
    suspend fun getMovies(category: MovieCategory, page: Int = 1) = api.getMovies(category = category.category, page = page)
    suspend fun searchMovies(query: String, page: Int = 1) = api.searchMovies(query = query, page = page)
}
