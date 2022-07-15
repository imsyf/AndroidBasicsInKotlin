package im.syf.words

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import im.syf.words.databinding.ItemViewBinding

class LetterAdapter : RecyclerView.Adapter<LetterAdapter.LetterViewHolder>() {

    // Generates a [CharRange] from 'A' to 'Z' and converts it to a list
    private val list = ('A').rangeTo('Z').toList()

    /**
     * Provides a reference for the views needed to display items in your list.
     */
    class LetterViewHolder(val binding: ItemViewBinding) : RecyclerView.ViewHolder(binding.root)

    /**
     * Creates new views with [ItemViewBinding] as its template
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LetterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemViewBinding.inflate(layoutInflater, parent, false)

        // Setup custom accessibility delegate to set the text read
        binding.root.accessibilityDelegate = Accessibility

        return LetterViewHolder(binding)
    }

    /**
     * Replaces the content of an existing view with new data
     */
    override fun onBindViewHolder(holder: LetterViewHolder, position: Int) {
        val item = list[position].toString()
        with(holder.binding.buttonItem) {
            text = item
            setOnClickListener {
                val action = LetterListFragmentDirections.toWordListFragment(item)
                findNavController().navigate(action)
            }
        }
    }

    override fun getItemCount(): Int = list.size
}
