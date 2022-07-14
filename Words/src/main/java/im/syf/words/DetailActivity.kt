package im.syf.words

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import im.syf.words.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Retrieve the LETTER from the Intent extras
        // intent.extras.getString returns String? (String or null)
        // so toString() guarantees that the value will be a String
        val letterId = intent.extras?.getString(LETTER) ?: "A"

        with(binding.recyclerView) {
            adapter = WordAdapter(this@DetailActivity, letterId)
            addItemDecoration(
                DividerItemDecoration(this@DetailActivity, DividerItemDecoration.VERTICAL)
            )
        }

        title = getString(R.string.detail_prefix, letterId)
    }

    companion object {
        const val LETTER = "letter"
        const val SEARCH_PREFIX = "https://www.google.com/search?q="
    }
}
