package br.com.moviehub.data.api

import br.com.moviehub.data.model.MovieResponse
import br.com.moviehub.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("movie/{category}")
    suspend fun getMovies(
        @Path("category") category: String,
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("include_video") includeVideo: Boolean = false,
        @Query("language") language: String = "pt-BR",
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Header("accept") accept: String = "application/json",
    ): MovieResponse
    @GET("search/movie")
    suspend fun searchMovies(
        @Query("query") query: String,
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("language") language: String = "pt-BR",
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Header("accept") accept: String = "application/json",
    ): MovieResponse
}
