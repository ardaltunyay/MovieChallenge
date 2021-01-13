package io.github.ardaltunyay.moviechallenge.core.databinding

import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import com.google.android.material.textview.MaterialTextView

object TextViewBindingAdapters {

    @JvmStatic
    @BindingAdapter("justDrawableTop")
    fun setDrawableTop(textview: MaterialTextView, @DrawableRes resId: Int) {
        with(textview) {
            setCompoundDrawablesWithIntrinsicBounds(0, resId, 0, 0)
        }
    }
}