package io.github.ardaltunyay.moviechallenge.core.databinding

import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.databinding.BindingAdapter

object ToolbarBindingAdapters {

    @JvmStatic
    @BindingAdapter("navigationOnClick")
    fun setNavigationClickListener(toolbar: Toolbar, listener: View.OnClickListener) {
        toolbar.setNavigationOnClickListener(listener)
    }
}