package io.github.ardaltunyay.moviechallenge.data.remote.datasource

import com.flextrade.jfixture.JFixture
import io.github.ardaltunyay.moviechallenge.data.remote.api.IMovieApi
import io.github.ardaltunyay.moviechallenge.invoke
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test


@ExperimentalCoroutinesApi
internal class MovieRemoteDataSourceTest {

    private val fixture = JFixture()

    private lateinit var sut: MovieRemoteDataSource
    private lateinit var movieApi: IMovieApi

    @Before
    fun setup() {
        movieApi = mockk(relaxed = true)
        sut = MovieRemoteDataSource(movieApi = movieApi)
    }

    @Test
    fun `should call getSearchMovie service from movieApi when call getSearchMovie in datasource`() =
        runBlockingTest {

            val searchText = fixture<String>()
            val page = fixture<Int>()

            sut.getSearchMovie(searchText, page)

            coVerify {
                movieApi.getSearchMovie(searchText, page)
            }
        }

    @Test
    fun `should call getMovie service from movieApi when call getMovie in datasource`() =
        runBlockingTest {

            val movieId = fixture<Int>()

            sut.getMovie(movieId)

            coVerify {
                movieApi.getMovie(movieId)
            }
        }
}