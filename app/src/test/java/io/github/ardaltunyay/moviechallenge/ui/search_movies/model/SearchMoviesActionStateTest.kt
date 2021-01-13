package io.github.ardaltunyay.moviechallenge.ui.search_movies.model

import io.github.ardaltunyay.moviechallenge.R
import io.kotest.matchers.shouldBe
import org.junit.Test

internal class SearchMoviesActionStateTest {

    @Test
    fun `getVisibility should return true when model is IdleState`() {
        val model = SearchMoviesActionState.IdleState

        val actual = model.getVisibility()

        actual shouldBe true

    }

    @Test
    fun `getVisibility should return true when model is EmptyState`() {
        val model = SearchMoviesActionState.EmptyListState

        val actual = model.getVisibility()

        actual shouldBe true

    }

    @Test
    fun `getVisibility should return false when model is MovieListState`() {
        val model = SearchMoviesActionState.MovieListState

        val actual = model.getVisibility()

        actual shouldBe false

    }

    @Test
    fun `getIcon should return ic_search in drawable when model is IdleState`() {
        val model = SearchMoviesActionState.IdleState

        val actual = model.getIcon()

        actual shouldBe R.drawable.ic_search

    }

    @Test
    fun `getIcon should return ic_error in drawable when model is EmptyState`() {
        val model = SearchMoviesActionState.EmptyListState

        val actual = model.getIcon()

        actual shouldBe R.drawable.ic_error

    }

    @Test
    fun `getIcon should return 0 when model is MovieListState`() {
        val model = SearchMoviesActionState.MovieListState

        val actual = model.getIcon()

        actual shouldBe 0

    }

    @Test
    fun `getMessage should return search_movies_idle_state in drawable when model is IdleState`() {
        val model = SearchMoviesActionState.IdleState

        val actual = model.getMessage()

        actual shouldBe R.string.search_movies_idle_state

    }

    @Test
    fun `getMessage should return search_movies_empty_list_state in drawable when model is EmptyState`() {
        val model = SearchMoviesActionState.EmptyListState

        val actual = model.getMessage()

        actual shouldBe R.string.search_movies_empty_list_state

    }

    @Test
    fun `getMessage should return empty when model is MovieListState`() {
        val model = SearchMoviesActionState.MovieListState

        val actual = model.getMessage()

        actual shouldBe R.string.empty

    }
}