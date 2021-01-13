package io.github.ardaltunyay.moviechallenge.ui.search_movies.adapter.viewholder


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.ardaltunyay.moviechallenge.core.listeners.ItemClickListener
import io.github.ardaltunyay.moviechallenge.databinding.ListItemMovieBinding
import io.github.ardaltunyay.moviechallenge.ui.search_movies.model.MovieUIModel

class MovieHolder(private val binding: ListItemMovieBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun createHolder(parent: ViewGroup): MovieHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ListItemMovieBinding.inflate(inflater, parent, false)
            return MovieHolder(binding)
        }
    }

    fun bind(movieUIModel: MovieUIModel?, clickListener: ItemClickListener<MovieUIModel>?) {
        with(binding) {
            this.clickListener = clickListener
            this.movieUIModel = movieUIModel
            executePendingBindings()
        }
    }
}