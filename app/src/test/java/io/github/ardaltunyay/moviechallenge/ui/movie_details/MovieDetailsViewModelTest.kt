package io.github.ardaltunyay.moviechallenge.ui.movie_details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.flextrade.jfixture.JFixture
import io.github.ardaltunyay.moviechallenge.UnitTestCoroutineDispatcherProvider
import io.github.ardaltunyay.moviechallenge.core.network.Result
import io.github.ardaltunyay.moviechallenge.data.repository.MovieRepository
import io.github.ardaltunyay.moviechallenge.domain.MovieDetailsDomain
import io.github.ardaltunyay.moviechallenge.getOrAwaitValue
import io.github.ardaltunyay.moviechallenge.invoke
import io.github.ardaltunyay.moviechallenge.ui.movie_details.model.MovieDetailsActionState
import io.github.ardaltunyay.moviechallenge.ui.movie_details.model.MovieDetailsClickActions
import io.github.ardaltunyay.moviechallenge.ui.movie_details.model.MovieDetailsUIModel
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
internal class MovieDetailsViewModelTest {

    @get: Rule
    val rule = InstantTaskExecutorRule()

    private val dispatchers = UnitTestCoroutineDispatcherProvider()
    private val fixture = JFixture()

    private lateinit var sut: MovieDetailsViewModel
    private lateinit var movieRepository: MovieRepository


    @Before
    fun setup() {
        Dispatchers.setMain(dispatchers.main)

        movieRepository = mockk(relaxed = true)
        sut = MovieDetailsViewModel(movieRepository)

    }

    @After
    fun reset() {
        Dispatchers.resetMain()
    }

    @Test
    fun `clickActions value should OnBackClicked of MovieDetailsClickActions when call onBackClicked`() =
        runBlockingTest {

            sut.onBackClicked()

            sut.clickActions.getOrAwaitValue() shouldBe MovieDetailsClickActions.OnBackClicked

        }

    @Test
    fun `movieDetails should return a MovieDetailsUIModel when actionState value SetMovieState of MovieDetailsActionState`() =
        runBlockingTest {
            val movieUIModel = MovieDetailsUIModel(movieTitle = "title", posterImage = "poster")

            sut.actionState.value = MovieDetailsActionState.SetMovieState(movieUIModel)

            sut.movieDetails.getOrAwaitValue() shouldBe movieUIModel
        }


    @Test
    fun `actionState value should SetMovieState of MovieDetailsActionState when call fetchMovieDetails and response success from repository`() =
        runBlockingTest {
            val movieId = fixture<Int>()
            val movieDetailsDomain = fixture<MovieDetailsDomain>()

            coEvery { movieRepository.getMovie(movieId) } returns Result.Success(movieDetailsDomain)

            sut.fetchMovieDetails(movieId)

            sut.actionState.getOrAwaitValue().shouldBeTypeOf<MovieDetailsActionState.LoadingState>()
            
            //TODO complete the test..


        }


}