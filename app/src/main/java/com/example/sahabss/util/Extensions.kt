package com.example.sahabss.util

import android.graphics.drawable.Drawable
import android.os.SystemClock
import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.sahabss.R
import com.google.android.material.snackbar.Snackbar



fun View.show(){
    animate().alpha(1f).withEndAction {
        visibility = View.VISIBLE
    }
}

fun View.invisible(){
    animate().alpha(0f).withEndAction { visibility = View.INVISIBLE }
}

fun View.hide(){
    animate().alpha(0f).withEndAction { visibility = View.GONE }
}

fun View.showSnackBar(message: String?){
    message?.let {
        Snackbar.make(this,it,Snackbar.LENGTH_LONG).show()
    }
}

fun ImageView.load(
    url: Any?, onLoadingFinished: () -> Unit = {},
    @DrawableRes placeholder: Int = R.mipmap.ic_launcher,
    cicular: Boolean = false
) {
    val listener = object : RequestListener<Drawable> {
        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<Drawable>?,
            isFirstResource: Boolean
        ): Boolean {
            onLoadingFinished()
            return false
        }

        override fun onResourceReady(
            resource: Drawable?,
            model: Any?,
            target: Target<Drawable>?,
            dataSource: DataSource?,
            isFirstResource: Boolean
        ): Boolean {
            onLoadingFinished()
            return false
        }
    }
    Glide.with(this)
        .load(url)
        .apply { if (cicular) circleCrop() }
        .apply(RequestOptions.placeholderOf(placeholder))
        .listener(listener)
        .into(this)
}

fun View.setOnSafeClickListener(block: (View) -> Unit) {
    setOnClickListener(SafeClickListener { block(it) })
}

fun View.setOnSafeClickListener(timeGap: Long, block: (View) -> Unit) {
    setOnClickListener(SafeClickListener(timeGap) { block(it) })
}
