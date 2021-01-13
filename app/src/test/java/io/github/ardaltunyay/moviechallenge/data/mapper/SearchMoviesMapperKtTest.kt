package io.github.ardaltunyay.moviechallenge.data.mapper

import com.flextrade.jfixture.JFixture
import io.github.ardaltunyay.moviechallenge.data.remote.model.SearchMovie
import io.github.ardaltunyay.moviechallenge.data.remote.model.SearchMoviesResponse
import io.github.ardaltunyay.moviechallenge.domain.MovieDomain
import io.github.ardaltunyay.moviechallenge.domain.SearchMoviesDomain
import io.github.ardaltunyay.moviechallenge.invoke
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf
import org.junit.Test


internal class SearchMoviesMapperKtTest {

    private val fixture = JFixture()

    @Test
    fun `test search movie to domain mapping`() {
        val searchMovie = fixture<SearchMovie>()

        val actual = searchMovie.toDomain()

        actual.shouldBeTypeOf<MovieDomain>()
        actual.id shouldBe searchMovie.id
        actual.posterImage shouldBe searchMovie.poster_path
        actual.orginalTitle shouldBe searchMovie.original_title
        actual.title shouldBe searchMovie.title
        actual.releaseDate shouldBe searchMovie.release_date
        actual.averageVote shouldBe searchMovie.vote_average

    }

    @Test
    fun `test search movies response to domain mapping`() {
        val searchMoviesResponse = fixture<SearchMoviesResponse>()

        val actual = searchMoviesResponse.toDomain()

        actual.shouldBeTypeOf<SearchMoviesDomain>()
        actual.page shouldBe searchMoviesResponse.page
        actual.totalPages shouldBe searchMoviesResponse.total_pages
        actual.totalResults shouldBe searchMoviesResponse.total_results
        actual.results.size shouldBe searchMoviesResponse.results!!.size

    }


}