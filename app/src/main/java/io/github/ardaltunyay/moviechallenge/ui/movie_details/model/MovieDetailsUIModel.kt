package io.github.ardaltunyay.moviechallenge.ui.movie_details.model

data class MovieDetailsUIModel(
    val movieTitle: String = "",
    val movieOverview: String = "",
    val posterImage: String = "",
    val backdropImage: String = "",
    val averageVote: String = "",
    val releaseDate: String = "",
    val genres: List<String> = emptyList()
) {
    fun getAverageVoteVisibility(): Boolean = averageVote.isNotEmpty()
}