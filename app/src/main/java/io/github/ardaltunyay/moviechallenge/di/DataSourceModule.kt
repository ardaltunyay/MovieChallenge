package io.github.ardaltunyay.moviechallenge.di

import io.github.ardaltunyay.moviechallenge.data.remote.datasource.MovieRemoteDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    single { MovieRemoteDataSource(movieApi = get()) }
}