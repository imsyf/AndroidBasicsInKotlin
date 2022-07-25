package im.syf.devbyte.playlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import im.syf.devbyte.R
import im.syf.devbyte.databinding.DevbyteItemBinding

class DevByteVideoAdapter(
    private val clickListener: DevByteVideoListener,
) : ListAdapter<DevByteVideo, DevByteVideoAdapter.DevByteVideoViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DevByteVideoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: DevbyteItemBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.devbyte_item,
            parent,
            false
        )

        return DevByteVideoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DevByteVideoViewHolder, position: Int) {
        holder.binding.video = getItem(position)
        holder.binding.clickListener = clickListener
        holder.binding.executePendingBindings()
    }

    class DevByteVideoViewHolder(
        val binding: DevbyteItemBinding,
    ) : RecyclerView.ViewHolder(binding.root)

    companion object DiffCallback : DiffUtil.ItemCallback<DevByteVideo>() {

        override fun areItemsTheSame(oldItem: DevByteVideo, newItem: DevByteVideo): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: DevByteVideo, newItem: DevByteVideo): Boolean {
            return oldItem == newItem
        }
    }
}
