package im.syf.busschedule

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import im.syf.busschedule.database.schedule.Schedule
import im.syf.busschedule.databinding.BusStopItemBinding
import java.text.SimpleDateFormat
import java.util.Date

class BusStopAdapter(
    private val onItemClicked: ((Schedule) -> Unit)? = null,
) : ListAdapter<Schedule, BusStopAdapter.BusStopViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusStopViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = BusStopItemBinding.inflate(layoutInflater, parent, false)
        val viewHolder = BusStopViewHolder(binding)

        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            onItemClicked?.invoke(getItem(position))
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: BusStopViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class BusStopViewHolder(
        private val binding: BusStopItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        private val simpleDateFormat = SimpleDateFormat("h:mm a")

        fun bind(schedule: Schedule) = with(binding) {
            stopNameTextView.text = schedule.stopName
            arrivalTimeTextView.text = simpleDateFormat.format(
                Date(schedule.arrivalTime.toLong() * 1000)
            )
        }
    }

    companion object {

        private val DiffCallback = object : DiffUtil.ItemCallback<Schedule>() {
            override fun areItemsTheSame(oldItem: Schedule, newItem: Schedule): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Schedule, newItem: Schedule): Boolean {
                return oldItem == newItem
            }
        }
    }
}
