package io.github.ardaltunyay.moviechallenge

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import io.github.ardaltunyay.moviechallenge.di.appModule
import io.github.ardaltunyay.moviechallenge.di.dataSourceModule
import io.github.ardaltunyay.moviechallenge.di.networkModule
import io.github.ardaltunyay.moviechallenge.di.repositoryModule
import io.github.ardaltunyay.moviechallenge.ui.movie_details.movieDetailsModule
import io.github.ardaltunyay.moviechallenge.ui.search_movies.searchMoviesModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(applicationContext)
            modules(
                appModule,
                networkModule,
                dataSourceModule,
                repositoryModule,
                searchMoviesModule,
                movieDetailsModule
            )
        }
    }
}