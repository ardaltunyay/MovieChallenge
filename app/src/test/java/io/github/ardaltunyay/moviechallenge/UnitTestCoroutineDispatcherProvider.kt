package io.github.ardaltunyay.moviechallenge

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface CoroutineDispatcherProvider {
    val main: CoroutineDispatcher
        get() = Dispatchers.Main
    val io: CoroutineDispatcher
        get() = Dispatchers.IO

    class Default : CoroutineDispatcherProvider
}

class UnitTestCoroutineDispatcherProvider : CoroutineDispatcherProvider {
    override val main: CoroutineDispatcher
        get() = Dispatchers.Unconfined
    override val io: CoroutineDispatcher
        get() = Dispatchers.Unconfined
}

