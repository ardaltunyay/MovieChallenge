package io.github.ardaltunyay.moviechallenge.domain

data class SearchMoviesDomain(
    val page: Int,
    val results: List<MovieDomain>,
    val totalPages: Int,
    val totalResults: Int
)

data class MovieDomain(
    val id: Int,
    val posterImage: String,
    val backdropImage: String,
    val orginalTitle: String,
    val title: String,
    val releaseDate: String,
    val averageVote: Double
)