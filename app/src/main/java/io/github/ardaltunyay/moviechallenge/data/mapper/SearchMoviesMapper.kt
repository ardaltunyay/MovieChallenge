package io.github.ardaltunyay.moviechallenge.data.mapper

import io.github.ardaltunyay.moviechallenge.data.remote.model.SearchMovie
import io.github.ardaltunyay.moviechallenge.data.remote.model.SearchMoviesResponse
import io.github.ardaltunyay.moviechallenge.domain.MovieDomain
import io.github.ardaltunyay.moviechallenge.domain.SearchMoviesDomain

fun SearchMovie.toDomain(): MovieDomain =
    MovieDomain(
        id = id ?: 0,
        posterImage = poster_path ?: "",
        backdropImage = backdrop_path ?: "",
        orginalTitle = original_title ?: "",
        title = title ?: "",
        releaseDate = release_date ?: "",
        averageVote = vote_average ?: 0.0
    )

fun SearchMoviesResponse.toDomain(): SearchMoviesDomain =
    SearchMoviesDomain(
        page = page ?: 1,
        totalPages = total_pages ?: 0,
        totalResults = total_results ?: 0,
        results = results?.map { it.toDomain() } ?: listOf()
    )