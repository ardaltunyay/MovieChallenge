package io.github.ardaltunyay.moviechallenge.domain

data class MovieDetailsDomain(
    val id: Int,
    val posterImage: String,
    val backdropImage: String,
    val title: String,
    val orginalTitle: String,
    val overview: String,
    val releaseDate: String,
    val averageVote: Double,
    val genres: List<GenreDomain>
)

data class GenreDomain(
    val id: Int,
    val name: String
)