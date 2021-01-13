package io.github.ardaltunyay.moviechallenge.data.remote.api

import io.github.ardaltunyay.moviechallenge.data.remote.model.MovieDetailsResponse
import io.github.ardaltunyay.moviechallenge.data.remote.model.SearchMoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IMovieApi {

    @GET("search/movie")
    suspend fun getSearchMovie(
        @Query("query") searchText: String,
        @Query("page") page: Int,
    ): Response<SearchMoviesResponse>

    @GET("movie/{movieId}")
    suspend fun getMovie(@Path("movieId") movieId: Int): Response<MovieDetailsResponse>

}