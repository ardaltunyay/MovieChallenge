package io.github.ardaltunyay.moviechallenge.ui.search_movies.pagination

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import io.github.ardaltunyay.moviechallenge.core.livedata.LiveEvent
import io.github.ardaltunyay.moviechallenge.core.network.Result
import io.github.ardaltunyay.moviechallenge.data.repository.MovieRepository
import io.github.ardaltunyay.moviechallenge.ui.search_movies.model.MovieUIModel
import io.github.ardaltunyay.moviechallenge.ui.search_movies.model.SearchMoviesActionState
import kotlinx.coroutines.CoroutineScope

class SearchMoviesDataSourceFactory(private val movieRepository: MovieRepository) :
    DataSource.Factory<Int, MovieUIModel>() {

    private lateinit var _searchText: String

    fun setSearchText(searchText: String) {
        _searchText = searchText
    }

    private lateinit var _scope: CoroutineScope

    fun setScope(scope: CoroutineScope) {
        _scope = scope
    }

    private lateinit var _actionState: MutableLiveData<SearchMoviesActionState>

    fun setActionState(actionState: MutableLiveData<SearchMoviesActionState>) {
        _actionState = actionState
    }

    private var _errorState: LiveEvent<Result.Error>? = null
    fun setErrorState(errorState: LiveEvent<Result.Error>) {
        _errorState = errorState
    }

    private var _searchMoviesDataSource: SearchMoviesDataSource? = null

    override fun create(): DataSource<Int, MovieUIModel> {
        return SearchMoviesDataSource(
            movieRepository = movieRepository,
            searchText = _searchText,
            scope = _scope,
            actionState = _actionState,
            errorState = _errorState
        ).also {
            _searchMoviesDataSource = it
        }
    }

    fun invalidate() {
        _searchMoviesDataSource?.invalidate()
    }

}