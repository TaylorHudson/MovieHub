package br.com.moviehub.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.moviehub.R
import br.com.moviehub.data.model.Movie
import br.com.moviehub.utils.toDate
import br.com.moviehub.utils.toDateString


class MovieAdapter(
    private var movies: List<Movie>
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.movieTitle)
        val ratingBar: RatingBar = view.findViewById(R.id.ratingBar)
        val movieReleaseDate: TextView = view.findViewById(R.id.movieReleaseDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.title.text = movie.title
        holder.ratingBar.rating = (movie.voteAverage / 2).toFloat()
        val releaseDate = movie.releaseDate.toDate()
        holder.movieReleaseDate.text = releaseDate.toDateString()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateMovies(newMovies: List<Movie>) {
        movies = newMovies
        notifyDataSetChanged()
    }
}