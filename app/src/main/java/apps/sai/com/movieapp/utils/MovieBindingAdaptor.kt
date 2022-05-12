package apps.sai.com.movieapp.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
const val BASE_IMAGE_PREFIX ="https://image.tmdb.org/t/p/original/"
@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(BASE_IMAGE_PREFIX+imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }
}
