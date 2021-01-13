package io.github.ardaltunyay.moviechallenge.ui.movie_details

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val movieDetailsModule = module {
    viewModel { MovieDetailsViewModel(movieRepository = get()) }
}