package io.github.ardaltunyay.moviechallenge.core.databinding

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter

object ViewBindingAdapters {

    @JvmStatic
    @BindingAdapter("visibleWhen")
    fun getVisibleWhen(view: View, isVisible: Boolean?) {
        view.isVisible = isVisible ?: false
    }
}