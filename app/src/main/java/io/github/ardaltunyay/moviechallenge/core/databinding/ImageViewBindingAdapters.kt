package io.github.ardaltunyay.moviechallenge.core.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object ImageViewBindingAdapters {

    @JvmStatic
    @BindingAdapter("imageFromUrl")
    fun getImageFromUrl(imageView: ImageView, url: String?) {
        Glide.with(imageView).load(url).into(imageView)
    }
}