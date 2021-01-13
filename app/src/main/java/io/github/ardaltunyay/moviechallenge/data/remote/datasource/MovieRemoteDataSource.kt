package io.github.ardaltunyay.moviechallenge.data.remote.datasource

import io.github.ardaltunyay.moviechallenge.core.network.Result
import io.github.ardaltunyay.moviechallenge.core.network.safeApiCall
import io.github.ardaltunyay.moviechallenge.data.remote.api.IMovieApi
import io.github.ardaltunyay.moviechallenge.data.remote.model.MovieDetailsResponse
import io.github.ardaltunyay.moviechallenge.data.remote.model.SearchMoviesResponse

class MovieRemoteDataSource(private val movieApi: IMovieApi) {

    suspend fun getSearchMovie(searchText: String, page: Int): Result<SearchMoviesResponse> =
        safeApiCall { movieApi.getSearchMovie(searchText = searchText, page = page) }

    suspend fun getMovie(movieId: Int): Result<MovieDetailsResponse> =
        safeApiCall { movieApi.getMovie(movieId = movieId) }

}