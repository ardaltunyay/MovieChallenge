package io.github.ardaltunyay.moviechallenge.data.repository

import io.github.ardaltunyay.moviechallenge.core.network.Result
import io.github.ardaltunyay.moviechallenge.data.mapper.toDomain
import io.github.ardaltunyay.moviechallenge.data.remote.datasource.MovieRemoteDataSource
import io.github.ardaltunyay.moviechallenge.domain.MovieDetailsDomain
import io.github.ardaltunyay.moviechallenge.domain.SearchMoviesDomain

class MovieRepository(private val movieRemoteDataSource: MovieRemoteDataSource) {

    suspend fun getSearchMovie(searchText: String, page: Int): Result<SearchMoviesDomain> =
        when (val response = movieRemoteDataSource.getSearchMovie(searchText = searchText, page = page)) {
            is Result.Success -> Result.Success(response.data?.toDomain())
            is Result.Error -> Result.Error(response.exception)
        }


    suspend fun getMovie(movieId: Int): Result<MovieDetailsDomain> =
        when (val response = movieRemoteDataSource.getMovie(movieId = movieId)) {
            is Result.Success -> Result.Success(response.data?.toDomain())
            is Result.Error -> Result.Error(response.exception)
        }

}