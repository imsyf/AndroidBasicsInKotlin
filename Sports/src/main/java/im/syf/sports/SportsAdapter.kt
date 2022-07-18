package im.syf.sports

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import im.syf.sports.databinding.SportsListItemBinding
import im.syf.sports.model.Sport

class SportsAdapter(
    private val onItemClicked: (Sport) -> Unit,
) : ListAdapter<Sport, SportsAdapter.SportsViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SportsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = SportsListItemBinding.inflate(layoutInflater, parent, false)

        return SportsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SportsViewHolder, position: Int) {
        val sport = getItem(position)

        holder.bind(sport)
        holder.itemView.setOnClickListener { onItemClicked(sport) }
    }

    class SportsViewHolder(
        private val binding: SportsListItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(sport: Sport) = with(binding) {
            title.setText(sport.titleResourceId)
            subTitle.setText(sport.subTitleResourceId)
            sportsImage.load(sport.imageResourceId)
        }
    }

    companion object {

        private val DiffCallback = object : DiffUtil.ItemCallback<Sport>() {
            override fun areItemsTheSame(oldItem: Sport, newItem: Sport): Boolean {
                val id = oldItem.id == newItem.id
                val title = oldItem.titleResourceId == newItem.titleResourceId
                val subtitle = oldItem.subTitleResourceId == newItem.subTitleResourceId

                return id || title || subtitle
            }

            override fun areContentsTheSame(oldItem: Sport, newItem: Sport): Boolean {
                return oldItem == newItem
            }
        }
    }
}
