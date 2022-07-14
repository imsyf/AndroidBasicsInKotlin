package im.syf.dogglers.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import im.syf.dogglers.R
import im.syf.dogglers.const.Layout
import im.syf.dogglers.data.DataSource

/**
 * Adapter to inflate the appropriate list item layout and populate the view with information
 * from the appropriate data source
 */
class DogCardAdapter(
    private val context: Context?,
    private val layout: Int,
) : RecyclerView.Adapter<DogCardAdapter.DogCardViewHolder>() {

    // TODO: Initialize the data using the List found in data/DataSource
    private val dogs = DataSource.dogs

    /**
     * Initialize view elements
     */
    class DogCardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // TODO: Declare and initialize all of the list item UI components
        val dogPicture: ImageView = view.findViewById(R.id.dog_picture)
        val dogName: TextView = view.findViewById(R.id.dog_name)
        val dogAge: TextView = view.findViewById(R.id.dog_age)
        val dogHobbies: TextView = view.findViewById(R.id.dog_hobbies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogCardViewHolder {
        // TODO: Use a conditional to determine the layout type and set it accordingly.
        //  if the layout variable is Layout.GRID the grid list item should be used. Otherwise the
        //  the vertical/horizontal list item should be used.
        @LayoutRes val itemLayoutId = if (layout == Layout.GRID) {
            R.layout.grid_list_item
        } else {
            R.layout.vertical_horizontal_list_item
        }

        // TODO Inflate the layout
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(itemLayoutId, parent, false)

        // TODO: Null should not be passed into the view holder. This should be updated to reflect
        //  the inflated layout.
        return DogCardViewHolder(view)
    }

    override fun onBindViewHolder(holder: DogCardViewHolder, position: Int) {
        // TODO: Get the data at the current position
        val (imageResourceId, name, age, hobbies) = dogs[position]
        // TODO: Set the image resource for the current dog
        holder.dogPicture.setImageResource(imageResourceId)
        // TODO: Set the text for the current dog's name
        holder.dogName.text = name

        val resources = context?.resources
        // TODO: Set the text for the current dog's age
        holder.dogAge.text = resources?.getString(R.string.dog_age, age)
        // TODO: Set the text for the current dog's hobbies by passing the hobbies to the
        //  R.string.dog_hobbies string constant.
        //  Passing an argument to the string resource looks like:
        //  resources?.getString(R.string.dog_hobbies, dog.hobbies)
        holder.dogHobbies.text = resources?.getString(R.string.dog_hobbies, hobbies)
    }

    // TODO: return the size of the data set instead of 0
    override fun getItemCount(): Int = dogs.size
}
