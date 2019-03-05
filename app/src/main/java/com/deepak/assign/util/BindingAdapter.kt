package com.deepak.assign.util

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.deepak.assign.R

@BindingAdapter(value = ["imageUrl"], requireAll = false)
fun setImageUrl(imageView: ImageView, url: String?) {
    if (url != null)
        Glide.with(imageView.context).load(Uri.parse(url)).into(imageView)
    else
        Glide.with(imageView.context).load(R.mipmap.ic_launcher).into(imageView)

}
