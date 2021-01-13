package io.github.ardaltunyay.moviechallenge.core.base

import androidx.lifecycle.ViewModel
import io.github.ardaltunyay.moviechallenge.core.livedata.LiveEvent
import io.github.ardaltunyay.moviechallenge.core.network.Result

open class BaseViewModel : ViewModel() {

    val errorState = LiveEvent<Result.Error>()

    suspend fun <T : Any> checkErrorAndContinue(result: suspend () -> Result<T>): Result<T> =
        result().onError {
            errorState.value = Result.Error(it)
        }


}