package br.com.moviehub.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.moviehub.R
import br.com.moviehub.data.model.Movie
import br.com.moviehub.databinding.MovieItemBinding
import br.com.moviehub.utils.toDate
import br.com.moviehub.utils.toDateString
import coil.load

class MovieListAdapter(
    private var movies: List<Movie> = emptyList()
) : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    inner class ViewHolder(
        private val movieItemBinding: MovieItemBinding
    ) : RecyclerView.ViewHolder(movieItemBinding.root) {

        fun bind(movie: Movie) {
            movieItemBinding.movieImage.load("https://image.tmdb.org/t/p/w185${movie.posterPath}?api_key=208abee5480f7ab1291d57c6d9f5c69a") {
                crossfade(true)
                placeholder(R.drawable.ic_image_placeholder)
                error(R.drawable.ic_image_placeholder)
            }
            movieItemBinding.movieTitle.text = movie.title
            movieItemBinding.ratingBar.rating = (movie.voteAverage / 2).toFloat()
            movieItemBinding.movieReleaseDate.text = movie.releaseDate.toDate().toDateString()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            MovieItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount() = movies.size

    fun update(newMovies: List<Movie>) {
        movies = newMovies
        notifyDataSetChanged()
    }
}