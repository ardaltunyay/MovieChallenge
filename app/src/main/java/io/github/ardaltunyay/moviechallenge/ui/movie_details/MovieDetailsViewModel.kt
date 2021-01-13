package io.github.ardaltunyay.moviechallenge.ui.movie_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import io.github.ardaltunyay.moviechallenge.core.base.BaseViewModel
import io.github.ardaltunyay.moviechallenge.core.livedata.LiveEvent
import io.github.ardaltunyay.moviechallenge.data.repository.MovieRepository
import io.github.ardaltunyay.moviechallenge.ui.movie_details.mapper.toUIModel
import io.github.ardaltunyay.moviechallenge.ui.movie_details.model.MovieDetailsActionState
import io.github.ardaltunyay.moviechallenge.ui.movie_details.model.MovieDetailsClickActions
import io.github.ardaltunyay.moviechallenge.ui.movie_details.model.MovieDetailsUIModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MovieDetailsViewModel(private val movieRepository: MovieRepository) : BaseViewModel() {

    val clickActions = LiveEvent<MovieDetailsClickActions>()

    val actionState = MutableLiveData<MovieDetailsActionState>()


    val movieDetails: LiveData<MovieDetailsUIModel> = actionState.map {
        if (it is MovieDetailsActionState.SetMovieState) {
            it.movieDetails
        } else {
            MovieDetailsUIModel()
        }
    }

    fun fetchMovieDetails(movieId: Int) {
        viewModelScope.launch {
            actionState.value = MovieDetailsActionState.LoadingState
            val result = checkErrorAndContinue { movieRepository.getMovie(movieId = movieId) }
            delay(300)
            result.onSuccess { movieDomain ->
                movieDomain?.let {
                    val uiModel = it.toUIModel()
                    actionState.value = MovieDetailsActionState.SetMovieState(uiModel)
                }
            }
        }
    }

    fun onBackClicked() {
        clickActions.value = MovieDetailsClickActions.OnBackClicked
    }
}