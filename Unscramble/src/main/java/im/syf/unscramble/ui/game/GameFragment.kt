package im.syf.unscramble.ui.game

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import im.syf.unscramble.R
import im.syf.unscramble.databinding.FragmentGameBinding
import im.syf.unscramble.ui.game.Words.MAX_NO_OF_WORDS
import im.syf.unscramble.ui.game.Words.SCORE_INCREASE

/**
 * Fragment where the game is played, contains the game logic.
 */
class GameFragment : Fragment() {

    private var score = 0
    private var currentWordCount = 0
    private var currentScrambledWord = "test"

    // Binding object instance with access to the views in the game_fragment.xml layout
    private var _binding: FragmentGameBinding? = null
    private val binding: FragmentGameBinding
        get() = _binding!!

    // Create a ViewModel the first time the fragment is created.
    // If the fragment is re-created, it receives the same GameViewModel instance created by the
    // first fragment
    private val viewModel: GameViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout XML file and return a binding object instance
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        Log.d("GameFragment", "GameFragment created/re-created!")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel

        // Setup a click listener for the Submit and Skip buttons.
        binding.submit.setOnClickListener { onSubmitWord() }
        binding.skip.setOnClickListener { onSkipWord() }

        // Update the UI
        updateNextWordOnScreen()
        binding.score.text = getString(R.string.score, 0)
        binding.wordCount.text = getString(R.string.word_count, 0, MAX_NO_OF_WORDS)
    }

    /*
    * Checks the user's word, and updates the score accordingly.
    * Displays the next scrambled word.
    */
    private fun onSubmitWord() {
        currentScrambledWord = getNextScrambledWord()
        currentWordCount++
        score += SCORE_INCREASE
        binding.wordCount.text = getString(R.string.word_count, currentWordCount, MAX_NO_OF_WORDS)
        binding.score.text = getString(R.string.score, score)
        setErrorTextField(false)
        updateNextWordOnScreen()
    }

    /*
     * Skips the current word without changing the score.
     * Increases the word count.
     */
    private fun onSkipWord() {
        currentScrambledWord = getNextScrambledWord()
        currentWordCount++
        binding.wordCount.text = getString(R.string.word_count, currentWordCount, MAX_NO_OF_WORDS)
        setErrorTextField(false)
        updateNextWordOnScreen()
    }

    /*
     * Gets a random word for the list of words and shuffles the letters in it.
     */
    private fun getNextScrambledWord(): String {
        val tempWord = Words.list.random().toCharArray()
        tempWord.shuffle()
        return String(tempWord)
    }

    /*
     * Re-initializes the data in the ViewModel and updates the views with the new data, to
     * restart the game.
     */
    private fun restartGame() {
        setErrorTextField(false)
        updateNextWordOnScreen()
    }

    /*
     * Exits the game.
     */
    private fun exitGame() {
        activity?.finish()
    }

    /*
    * Sets and resets the text field error status.
    */
    private fun setErrorTextField(error: Boolean) {
        if (error) {
            binding.textField.isErrorEnabled = true
            binding.textField.error = getString(R.string.try_again)
        } else {
            binding.textField.isErrorEnabled = false
            binding.textInputEditText.text = null
        }
    }

    /*
     * Displays the next scrambled word on screen.
     */
    private fun updateNextWordOnScreen() {
        binding.textViewUnscrambledWord.text = currentScrambledWord
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("GameFragment", "GameFragment destroyed!")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}