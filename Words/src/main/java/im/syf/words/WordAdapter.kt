package im.syf.words

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import im.syf.words.databinding.ItemViewBinding

class WordAdapter(
    context: Context,
    private val letterId: String,
) : RecyclerView.Adapter<WordAdapter.WordViewHolder>() {

    private val filteredWords: List<String>

    init {
        // Retrieve the list of words from res/values/arrays.xml
        val words = context.resources.getStringArray(R.array.words).toList()

        filteredWords = words
            // Returns items in a collection if the conditional clause is true,
            // in this case if an item starts with the given letter,
            // ignoring UPPERCASE or lowercase.
            .filter { it.startsWith(letterId, ignoreCase = true) }
            // Returns a collection that it has shuffled in place
            .shuffled()
            // Returns the first n items as a [List]
            .take(5)
            // Returns a sorted version of that [List]
            .sorted()
    }

    /**
     * Provides a reference for the views needed to display items in your list.
     */
    class WordViewHolder(val binding: ItemViewBinding) : RecyclerView.ViewHolder(binding.root)

    /**
     * Creates new views with [ItemViewBinding] as its template
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemViewBinding.inflate(layoutInflater, parent, false)

        // Setup custom accessibility delegate to set the text read
        binding.root.accessibilityDelegate = Accessibility

        return WordViewHolder(binding)
    }

    /**
     * Replaces the content of an existing view with new data
     */
    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val item = filteredWords[position]

        // Needed to call startActivity
        val context = holder.binding.root.context

        // Set the text of the WordViewHolder
        holder.binding.buttonItem.text = item
    }

    override fun getItemCount(): Int = filteredWords.size
}
