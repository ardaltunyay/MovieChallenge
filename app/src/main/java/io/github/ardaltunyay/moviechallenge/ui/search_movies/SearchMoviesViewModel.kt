package io.github.ardaltunyay.moviechallenge.ui.search_movies

import androidx.lifecycle.*
import androidx.paging.PagedList
import androidx.paging.toLiveData
import io.github.ardaltunyay.moviechallenge.core.base.BaseViewModel
import io.github.ardaltunyay.moviechallenge.ui.search_movies.model.MovieUIModel
import io.github.ardaltunyay.moviechallenge.ui.search_movies.model.SearchMoviesActionState
import io.github.ardaltunyay.moviechallenge.ui.search_movies.pagination.SearchMoviesDataSourceFactory

class SearchMoviesViewModel(
    private val config: PagedList.Config,
    private val searchMoviesDataSourceFactory: SearchMoviesDataSourceFactory
) : BaseViewModel() {

    private val _actionState =
        MutableLiveData<SearchMoviesActionState>(SearchMoviesActionState.IdleState)
    val actionState: LiveData<SearchMoviesActionState>
        get() = _actionState

    val queryText = MutableLiveData("")

    private val queryControl = queryText.map {
        if (it.length > 2) it else ""
    }

    private val searchPagedListLiveData = queryControl.switchMap {
        searchMoviesDataSourceFactory.setSearchText(it)
        searchMoviesDataSourceFactory.setScope(viewModelScope)
        searchMoviesDataSourceFactory.setActionState(_actionState)
        searchMoviesDataSourceFactory.setErrorState(errorState)
        searchMoviesDataSourceFactory.toLiveData(config)
    }

    val searchMoviesCombination = MediatorLiveData<PagedList<MovieUIModel>>().apply {
        addSource(searchPagedListLiveData) {
            value = it
        }
    }

}