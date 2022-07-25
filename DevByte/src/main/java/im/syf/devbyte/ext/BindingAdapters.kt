package im.syf.devbyte.ext

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import im.syf.devbyte.playlist.DevByteVideo
import im.syf.devbyte.playlist.DevByteVideoAdapter

/**
 * Updates the data shown in the [RecyclerView]
 */
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<DevByteVideo>?) {
    val adapter = recyclerView.adapter as DevByteVideoAdapter
    adapter.submitList(data)
}

/**
 * Binding adapter used to hide the spinner once data is available.
 */
@BindingAdapter("isNetworkError", "playlist")
fun hideIfNetworkError(view: View, isNetWorkError: Boolean, playlist: Any?) {
    view.visibility = if (playlist != null) View.GONE else View.VISIBLE

    if (isNetWorkError) {
        view.visibility = View.GONE
    }
}

/**
 * Binding adapter used to display images from URL using Glide
 */
@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String) {
    Glide.with(imageView.context).load(url).into(imageView)
}
