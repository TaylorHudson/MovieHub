package br.com.moviehub.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.moviehub.R
import br.com.moviehub.data.model.Movie
import br.com.moviehub.databinding.MovieItemBinding
import br.com.moviehub.utils.Constants
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
            loadMovieImage(movie)
            movieItemBinding.movieTitle.text = movie.title
            movieItemBinding.ratingBar.rating = (movie.voteAverage / 2).toFloat()
            movieItemBinding.movieReleaseDate.text = movie.releaseDate.toDate().toDateString()
        }

        fun getMovieImagePath(movie: Movie): String {
            return "${Constants.MOVIE_IMAGE_BASE_URL}w185${movie.posterPath}?api_key=${Constants.API_KEY}"
        }

        fun loadMovieImage(movie: Movie) {
            movieItemBinding.movieImage.load(getMovieImagePath(movie)) {
                crossfade(true)
                placeholder(R.drawable.ic_image_placeholder)
                error(R.drawable.ic_image_placeholder)
            }
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