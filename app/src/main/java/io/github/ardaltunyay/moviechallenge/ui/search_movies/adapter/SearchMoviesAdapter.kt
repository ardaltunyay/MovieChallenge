package io.github.ardaltunyay.moviechallenge.ui.search_movies.adapter


import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import io.github.ardaltunyay.moviechallenge.core.listeners.ItemClickListener
import io.github.ardaltunyay.moviechallenge.ui.search_movies.adapter.viewholder.MovieHolder
import io.github.ardaltunyay.moviechallenge.ui.search_movies.model.MovieUIModel

class SearchMoviesAdapter : PagedListAdapter<MovieUIModel, MovieHolder>(MOVIE_ITEM_DIFF_UTIL) {

    private var _clickListener: ItemClickListener<MovieUIModel>? = null

    fun setClickListener(clickListener: ItemClickListener<MovieUIModel>) {
        _clickListener = clickListener
    }

    companion object {
        val MOVIE_ITEM_DIFF_UTIL = object : DiffUtil.ItemCallback<MovieUIModel>() {
            override fun areItemsTheSame(oldItem: MovieUIModel, newItem: MovieUIModel): Boolean {
                return oldItem.movieId == newItem.movieId
            }

            override fun areContentsTheSame(oldItem: MovieUIModel, newItem: MovieUIModel): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder =
        MovieHolder.createHolder(parent)

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.bind(getItem(position), _clickListener)
    }
}