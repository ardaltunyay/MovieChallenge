package io.github.ardaltunyay.moviechallenge.di

import io.github.ardaltunyay.moviechallenge.data.repository.MovieRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { MovieRepository(movieRemoteDataSource = get()) }
}