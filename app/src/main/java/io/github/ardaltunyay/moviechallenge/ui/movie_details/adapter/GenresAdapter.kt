package io.github.ardaltunyay.moviechallenge.ui.movie_details.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.ardaltunyay.moviechallenge.ui.movie_details.adapter.viewholder.GenreHolder

class GenresAdapter(private val genres: List<String>) : RecyclerView.Adapter<GenreHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreHolder =
        GenreHolder.createHolder(parent)

    override fun onBindViewHolder(holder: GenreHolder, position: Int) {
        holder.bind(genres[position])
    }

    override fun getItemCount(): Int = genres.size

}