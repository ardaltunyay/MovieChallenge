package io.github.ardaltunyay.moviechallenge.data.mapper

import com.flextrade.jfixture.JFixture
import io.github.ardaltunyay.moviechallenge.data.remote.model.Genre
import io.github.ardaltunyay.moviechallenge.data.remote.model.MovieDetailsResponse
import io.github.ardaltunyay.moviechallenge.domain.GenreDomain
import io.github.ardaltunyay.moviechallenge.domain.MovieDetailsDomain
import io.github.ardaltunyay.moviechallenge.invoke
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf
import org.junit.Test

internal class MovieDetailsMapperKtTest {

    private val fixture = JFixture()

    @Test
    fun `test genre to domain mapping`() {
        val genre = fixture<Genre>()

        val actual = genre.toDomain()

        actual.shouldBeTypeOf<GenreDomain>()
        actual.id shouldBe genre.id
        actual.name shouldBe actual.name

    }

    @Test
    fun `test movie details response to domain mapping`() {
        val movieDetailsResponse = fixture<MovieDetailsResponse>()

        val actual = movieDetailsResponse.toDomain()

        actual.shouldBeTypeOf<MovieDetailsDomain>()
        actual.id shouldBe movieDetailsResponse.id
        actual.posterImage shouldBe movieDetailsResponse.poster_path
        actual.backdropImage shouldBe movieDetailsResponse.backdrop_path
        actual.title shouldBe movieDetailsResponse.title
        actual.orginalTitle shouldBe movieDetailsResponse.original_title
        actual.overview shouldBe movieDetailsResponse.overview
        actual.releaseDate shouldBe movieDetailsResponse.release_date
        actual.averageVote shouldBe movieDetailsResponse.vote_average
        actual.genres.size shouldBe movieDetailsResponse.genres!!.size

    }
}