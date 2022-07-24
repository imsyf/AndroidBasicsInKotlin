package im.syf.inventory

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import im.syf.inventory.data.Item
import im.syf.inventory.data.getFormattedPrice
import im.syf.inventory.databinding.ItemListItemBinding

class ItemListAdapter(
    private val onItemClicked: (Item) -> Unit,
) : ListAdapter<Item, ItemListAdapter.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemListItemBinding.inflate(layoutInflater, parent, false)
        val viewHolder = ViewHolder(binding)

        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            onItemClicked(getItem(position))
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ItemListItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Item) = with(binding) {
            itemName.text = item.itemName
            itemPrice.text = item.getFormattedPrice()
            itemQuantity.text = item.quantityInStock.toString()
        }
    }

    companion object {

        private val DiffCallback = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem.itemName == newItem.itemName
            }
        }
    }
}
