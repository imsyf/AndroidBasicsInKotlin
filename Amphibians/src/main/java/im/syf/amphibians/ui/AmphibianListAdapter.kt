package im.syf.amphibians.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import im.syf.amphibians.databinding.ListViewItemBinding
import im.syf.amphibians.network.Amphibian

class AmphibianListAdapter(
    private val clickListener: AmphibianListener
) : ListAdapter<Amphibian, AmphibianListAdapter.AmphibianViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AmphibianViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListViewItemBinding.inflate(layoutInflater, parent, false)
        return AmphibianViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AmphibianViewHolder, position: Int) {
        holder.bind(clickListener, getItem(position))
    }

    class AmphibianViewHolder(
        private val binding: ListViewItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(clickListener: AmphibianListener, amphibian: Amphibian) {
            binding.amphibian = amphibian
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Amphibian>() {

        override fun areItemsTheSame(oldItem: Amphibian, newItem: Amphibian): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Amphibian, newItem: Amphibian): Boolean {
            return oldItem.type == newItem.type && oldItem.description == newItem.description
        }
    }
}
