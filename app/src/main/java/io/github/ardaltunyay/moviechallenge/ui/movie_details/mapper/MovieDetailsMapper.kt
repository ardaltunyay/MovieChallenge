package io.github.ardaltunyay.moviechallenge.ui.movie_details.mapper

import io.github.ardaltunyay.moviechallenge.BuildConfig
import io.github.ardaltunyay.moviechallenge.domain.MovieDetailsDomain
import io.github.ardaltunyay.moviechallenge.ui.movie_details.model.MovieDetailsUIModel

fun MovieDetailsDomain.toUIModel(): MovieDetailsUIModel =
    MovieDetailsUIModel(
        movieTitle = title,
        movieOverview = overview,
        posterImage = if (posterImage.isEmpty()) "" else "${BuildConfig.BASE_IMAGE_URL}w300$posterImage",
        backdropImage = if (backdropImage.isEmpty()) "" else "${BuildConfig.BASE_IMAGE_URL}w500$backdropImage",
        averageVote = if (averageVote == 0.0) "" else averageVote.toString(),
        releaseDate = releaseDate,
        genres = genres.map { it.name }
    )