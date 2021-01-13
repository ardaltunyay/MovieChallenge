package io.github.ardaltunyay.moviechallenge.ui.movie_details.adapter.viewholder


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.ardaltunyay.moviechallenge.databinding.ListItemGenreBinding

class GenreHolder(private val binding: ListItemGenreBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun createHolder(parent: ViewGroup): GenreHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ListItemGenreBinding.inflate(inflater, parent, false)
            return GenreHolder(binding)
        }
    }

    fun bind(genre: String) {
        with(binding) {
            this.genre = genre
            executePendingBindings()
        }
    }
}