package io.github.ardaltunyay.moviechallenge.ui.search_movies.model

data class MovieUIModel(
    val movieId: Int,
    val posterImage: String,
    val movieTitle: String,
    val averageVote: String
) {
    fun getAverageVoteVisibility(): Boolean = averageVote.isNotEmpty()
}