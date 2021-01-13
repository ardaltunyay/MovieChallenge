package io.github.ardaltunyay.moviechallenge.data.mapper

import io.github.ardaltunyay.moviechallenge.data.remote.model.Genre
import io.github.ardaltunyay.moviechallenge.data.remote.model.MovieDetailsResponse
import io.github.ardaltunyay.moviechallenge.domain.GenreDomain
import io.github.ardaltunyay.moviechallenge.domain.MovieDetailsDomain

fun Genre.toDomain(): GenreDomain =
    GenreDomain(
        id = id ?: 0,
        name = name ?: ""
    )

fun MovieDetailsResponse.toDomain(): MovieDetailsDomain =
    MovieDetailsDomain(
        id = id ?: 0,
        posterImage = poster_path ?: "",
        backdropImage = backdrop_path ?: "",
        title = title ?: "",
        orginalTitle = original_title ?: "",
        overview = overview ?: "",
        releaseDate = release_date ?: "",
        averageVote = vote_average ?: 0.0,
        genres = genres?.map { it.toDomain() } ?: emptyList()
    )
