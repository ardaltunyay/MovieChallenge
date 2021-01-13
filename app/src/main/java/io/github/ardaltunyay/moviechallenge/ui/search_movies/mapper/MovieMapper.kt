package io.github.ardaltunyay.moviechallenge.ui.search_movies.mapper

import io.github.ardaltunyay.moviechallenge.BuildConfig
import io.github.ardaltunyay.moviechallenge.domain.MovieDomain
import io.github.ardaltunyay.moviechallenge.ui.search_movies.model.MovieUIModel

fun MovieDomain.toUIModel(): MovieUIModel =
    MovieUIModel(
        movieId = id,
        posterImage = if (posterImage.isEmpty()) "" else "${BuildConfig.BASE_IMAGE_URL}w300$posterImage",
        movieTitle = title,
        averageVote = if (averageVote <= 0.0) "" else averageVote.toString()
    )

