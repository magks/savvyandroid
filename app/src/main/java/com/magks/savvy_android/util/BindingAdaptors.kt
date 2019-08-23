package com.magks.savvy_android.util

import android.widget.ImageButton
import androidx.databinding.BindingAdapter
import android.view.View
import android.widget.ImageView

object BindingAdapters {
    /**
     * A Binding Adapter that is called whenever the value of the attribute `app:goneUnless`
     * changes and sets
     */
    @BindingAdapter("app:goneUnless")
    @JvmStatic fun goneUnless(imageView: ImageView, visible: Boolean) {
        imageView.visibility = if (visible) View.VISIBLE else View.GONE
    }

}