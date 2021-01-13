package io.github.ardaltunyay.moviechallenge.ui.search_movies.mapper

import com.flextrade.jfixture.JFixture
import io.github.ardaltunyay.moviechallenge.BuildConfig
import io.github.ardaltunyay.moviechallenge.domain.MovieDomain
import io.github.ardaltunyay.moviechallenge.invoke
import io.github.ardaltunyay.moviechallenge.ui.search_movies.model.MovieUIModel
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.types.shouldBeTypeOf
import org.junit.Test

internal class MovieMapperKtTest {

    private val fixture = JFixture()

    @Test
    fun `test movie domain to ui model mapping`() {
        val movieDomain = fixture<MovieDomain>().copy(
            posterImage = "",
            averageVote = 0.0
        )

        val actual = movieDomain.toUIModel()

        actual.shouldBeTypeOf<MovieUIModel>()
        actual.movieId shouldBe movieDomain.id
        actual.movieTitle shouldBe movieDomain.title

        actual.posterImage shouldBe ""
        actual.averageVote shouldBe ""

    }

    @Test
    fun `should complete posterImage value when posterImage is not empty`() {
        val movieDomain = fixture<MovieDomain>()

        val actual = movieDomain.toUIModel()

        actual.posterImage shouldBe "${BuildConfig.BASE_IMAGE_URL}w300${movieDomain.posterImage}"

    }

    @Test
    fun `should not empty backdropImage value when averageVote bigger than 0`() {
        val movieDomain = fixture<MovieDomain>()

        val actual = movieDomain.toUIModel()

        actual.averageVote shouldNotBe ""
        actual.averageVote shouldBe movieDomain.averageVote.toString()

    }

}