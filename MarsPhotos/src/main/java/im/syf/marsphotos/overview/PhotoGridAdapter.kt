package im.syf.marsphotos.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import im.syf.marsphotos.databinding.GridViewItemBinding
import im.syf.marsphotos.network.MarsPhoto

class PhotoGridAdapter :
    ListAdapter<MarsPhoto, PhotoGridAdapter.MarsPhotoViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarsPhotoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = GridViewItemBinding.inflate(layoutInflater)
        return MarsPhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MarsPhotoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MarsPhotoViewHolder(
        private val binding: GridViewItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(marsPhoto: MarsPhoto) = with(binding) {
            photo = marsPhoto
            executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<MarsPhoto>() {
        override fun areItemsTheSame(oldItem: MarsPhoto, newItem: MarsPhoto): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: MarsPhoto, newItem: MarsPhoto): Boolean =
            oldItem.imgSrcUrl == newItem.imgSrcUrl
    }
}
