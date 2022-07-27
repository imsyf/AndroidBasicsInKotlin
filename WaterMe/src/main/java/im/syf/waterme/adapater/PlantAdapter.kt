package im.syf.waterme.adapater

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import im.syf.waterme.databinding.ListItemBinding
import im.syf.waterme.model.Plant

class PlantAdapter(
    private val longClickListener: PlantListener,
) : ListAdapter<Plant, PlantAdapter.PlantViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PlantViewHolder(ListItemBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        holder.bind(longClickListener, getItem(position))
    }

    class PlantViewHolder(
        private val binding: ListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(longClickListener: PlantListener, plant: Plant) {
            binding.plant = plant
            binding.longClickListner = longClickListener
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Plant>() {
        override fun areItemsTheSame(oldItem: Plant, newItem: Plant): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Plant, newItem: Plant): Boolean {
            return oldItem.name == newItem.name
        }
    }
}
