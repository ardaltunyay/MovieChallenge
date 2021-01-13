package io.github.ardaltunyay.moviechallenge.data.repository

import com.flextrade.jfixture.JFixture
import io.github.ardaltunyay.moviechallenge.core.network.Result
import io.github.ardaltunyay.moviechallenge.data.mapper.toDomain
import io.github.ardaltunyay.moviechallenge.data.remote.datasource.MovieRemoteDataSource
import io.github.ardaltunyay.moviechallenge.data.remote.model.MovieDetailsResponse
import io.github.ardaltunyay.moviechallenge.data.remote.model.SearchMoviesResponse
import io.github.ardaltunyay.moviechallenge.domain.MovieDetailsDomain
import io.github.ardaltunyay.moviechallenge.domain.SearchMoviesDomain
import io.github.ardaltunyay.moviechallenge.invoke
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test


@ExperimentalCoroutinesApi
internal class MovieRepositoryTest {

    private val fixture = JFixture()

    private lateinit var sut: MovieRepository
    private lateinit var movieRemoteDataSource: MovieRemoteDataSource

    @Before
    fun setup() {
        movieRemoteDataSource = mockk(relaxed = true)
        sut = MovieRepository(movieRemoteDataSource = movieRemoteDataSource)
    }

    @Test
    fun `should return SearchMoviesDomain in result success when return response success from getSearchMovie of datasource`() =
        runBlockingTest {
            val searchText = fixture<String>()
            val page = fixture<Int>()
            val searchMoviesResponse = fixture<SearchMoviesResponse>()

            coEvery {
                movieRemoteDataSource.getSearchMovie(
                    searchText,
                    page
                )
            } returns Result.Success(searchMoviesResponse)

            val actual = sut.getSearchMovie(searchText, page)

            actual.shouldBeTypeOf<Result.Success<SearchMoviesDomain>>()
            actual.data shouldBe searchMoviesResponse.toDomain()

        }

    @Test
    fun `should return Exception in result error when return response error from getSearchMovie of datasource`() =
        runBlockingTest {
            val searchText = fixture<String>()
            val page = fixture<Int>()
            val exception = Exception("Error!!!")

            coEvery {
                movieRemoteDataSource.getSearchMovie(searchText, page)
            } returns Result.Error(exception)

            val actual = sut.getSearchMovie(searchText, page)

            actual.shouldBeTypeOf<Result.Error>()
            actual.exception shouldBe exception

        }

    @Test
    fun `should return MovieDetailsDomain in result success when return response success from getMovie of datasource`() =
        runBlockingTest {
            val movieId = fixture<Int>()
            val movieDetailsResponse = fixture<MovieDetailsResponse>()

            coEvery {
                movieRemoteDataSource.getMovie(movieId)
            } returns Result.Success(movieDetailsResponse)

            val actual = sut.getMovie(movieId)

            actual.shouldBeTypeOf<Result.Success<MovieDetailsDomain>>()
            actual.data shouldBe movieDetailsResponse.toDomain()

        }

    @Test
    fun `should return Exception in result error when return response error from getMovie of datasource`() =
        runBlockingTest {
            val movieId = fixture<Int>()
            val exception = Exception("Error!!!")

            coEvery {
                movieRemoteDataSource.getMovie(movieId)
            } returns Result.Error(exception)

            val actual = sut.getMovie(movieId)

            actual.shouldBeTypeOf<Result.Error>()
            actual.exception shouldBe exception

        }


}