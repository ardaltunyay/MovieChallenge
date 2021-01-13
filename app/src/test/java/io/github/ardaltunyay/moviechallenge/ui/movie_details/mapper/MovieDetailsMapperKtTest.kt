package io.github.ardaltunyay.moviechallenge.ui.movie_details.mapper

import com.flextrade.jfixture.JFixture
import io.github.ardaltunyay.moviechallenge.BuildConfig
import io.github.ardaltunyay.moviechallenge.domain.MovieDetailsDomain
import io.github.ardaltunyay.moviechallenge.invoke
import io.github.ardaltunyay.moviechallenge.ui.movie_details.model.MovieDetailsUIModel
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.types.shouldBeTypeOf
import org.junit.Test


internal class MovieDetailsMapperKtTest {

    private val fixture = JFixture()

    @Test
    fun `test movie details domain to ui model mapping`() {
        val movieDetailsDomain = fixture<MovieDetailsDomain>().copy(
            posterImage = "",
            backdropImage = "",
            averageVote = 0.0
        )

        val actual = movieDetailsDomain.toUIModel()

        actual.shouldBeTypeOf<MovieDetailsUIModel>()
        actual.movieTitle shouldBe movieDetailsDomain.title
        actual.movieOverview shouldBe movieDetailsDomain.overview
        actual.releaseDate shouldBe movieDetailsDomain.releaseDate
        actual.genres shouldBe movieDetailsDomain.genres.map { it.name }

        actual.posterImage shouldBe ""
        actual.backdropImage shouldBe ""
        actual.averageVote shouldBe ""

    }

    @Test
    fun `should complete posterImage value when posterImage is not empty`() {
        val movieDetailsDomain = fixture<MovieDetailsDomain>()

        val actual = movieDetailsDomain.toUIModel()

        actual.posterImage shouldBe "${BuildConfig.BASE_IMAGE_URL}w300${movieDetailsDomain.posterImage}"

    }

    @Test
    fun `should complete backdropImage value when backdropImage is not empty`() {
        val movieDetailsDomain = fixture<MovieDetailsDomain>()

        val actual = movieDetailsDomain.toUIModel()

        actual.backdropImage shouldBe "${BuildConfig.BASE_IMAGE_URL}w500${movieDetailsDomain.backdropImage}"

    }

    @Test
    fun `should not empty backdropImage value when averageVote bigger than 0`() {
        val movieDetailsDomain = fixture<MovieDetailsDomain>()

        val actual = movieDetailsDomain.toUIModel()

        actual.averageVote shouldNotBe ""
        actual.averageVote shouldBe movieDetailsDomain.averageVote.toString()

    }

}