package io.github.ardaltunyay.moviechallenge.ui.search_movies

import androidx.paging.PagedList
import io.github.ardaltunyay.moviechallenge.ui.search_movies.pagination.SearchMoviesDataSourceFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val searchMoviesModule = module {
    viewModel { SearchMoviesViewModel(config = get(named("searchMoviesPageConfig")), searchMoviesDataSourceFactory = get()) }
    single(
        named("searchMoviesPageConfig")
    ) {
        PagedList.Config.Builder()
            .setPageSize(SEARCH_RESULT_LIMIT)
            .setPrefetchDistance(PREFETCH_DISTANCE)
            .setEnablePlaceholders(false)
            .build()
    }
    single { SearchMoviesDataSourceFactory(movieRepository = get()) }
}

private const val SEARCH_RESULT_LIMIT = 50
private const val PREFETCH_DISTANCE = 20