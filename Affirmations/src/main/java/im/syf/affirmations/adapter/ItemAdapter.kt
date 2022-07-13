package im.syf.affirmations.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import im.syf.affirmations.R
import im.syf.affirmations.model.Affirmation

class ItemAdapter(
    private val context: Context,
    private val data: List<Affirmation>
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = data[position]
        holder.itemTitle.text = context.getString(item.stringResourceId)
        holder.itemImage.setImageResource(item.imageResourceId)
    }

    override fun getItemCount(): Int = data.size

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemTitle: TextView = view.findViewById(R.id.item_title)
        val itemImage: ImageView = view.findViewById(R.id.item_image)
    }
}
