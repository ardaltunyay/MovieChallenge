package io.github.ardaltunyay.moviechallenge.core.extensions

import android.view.View
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar

fun snackbar(view: View, message: String, duration: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(view, message, duration).show()
}

fun snackbar(view: View, @StringRes resId: Int, duration: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(view, resId, duration).show()
}