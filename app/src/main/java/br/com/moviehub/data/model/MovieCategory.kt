package br.com.moviehub.data.model

enum class MovieCategory(
    val category: String
) {
    POPULAR("popular"),
    NOW_PLAYING("now_playing"),
    TOP_RATED("top_rated"),
    UPCOMING("upcoming")
}