package io.github.ardaltunyay.moviechallenge.ui.search_movies.pagination

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import io.github.ardaltunyay.moviechallenge.core.livedata.LiveEvent
import io.github.ardaltunyay.moviechallenge.core.network.Result
import io.github.ardaltunyay.moviechallenge.data.repository.MovieRepository
import io.github.ardaltunyay.moviechallenge.ui.search_movies.mapper.toUIModel
import io.github.ardaltunyay.moviechallenge.ui.search_movies.model.MovieUIModel
import io.github.ardaltunyay.moviechallenge.ui.search_movies.model.SearchMoviesActionState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchMoviesDataSource(
    private val movieRepository: MovieRepository,
    private val searchText: String,
    private val scope: CoroutineScope,
    private val actionState: MutableLiveData<SearchMoviesActionState>?,
    private val errorState: LiveEvent<Result.Error>?
) : PageKeyedDataSource<Int, MovieUIModel>() {


    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, MovieUIModel>
    ) {
        scope.launch {

            delay(300)
            if (searchText.isEmpty()) {
                handleActionState(SearchMoviesActionState.IdleState)

            } else when (val result = movieRepository.getSearchMovie(searchText, 1)) {
                is Result.Success -> {
                    val list = result.data?.results?.map { it.toUIModel() } ?: emptyList()

                    val state = when {
                        list.isNotEmpty() -> SearchMoviesActionState.MovieListState
                        else -> SearchMoviesActionState.EmptyListState
                    }
                    handleActionState(state)
                    callback.onResult(list, null, result.data?.page?.plus(1))
                }
                is Result.Error -> {
                    handleErrorState(result)
                }

            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, MovieUIModel>) {
        scope.launch {

            when (val result = movieRepository.getSearchMovie(searchText, params.key)) {
                is Result.Success -> {
                    val list = result.data?.results?.map { it.toUIModel() } ?: emptyList()
                    callback.onResult(list, result.data?.page?.plus(1))
                }
                is Result.Error -> {
                    handleErrorState(result)
                }

            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, MovieUIModel>) {

    }

    private fun handleErrorState(value: Result.Error) {
        errorState?.value = value
    }

    private fun handleActionState(value: SearchMoviesActionState) {
        actionState?.value = value
    }


}