package im.syf.unscramble.ui.game

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import im.syf.unscramble.R
import im.syf.unscramble.databinding.FragmentGameBinding
import im.syf.unscramble.ui.game.Words.MAX_NO_OF_WORDS

/**
 * Fragment where the game is played, contains the game logic.
 */
class GameFragment : Fragment() {

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
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)
        Log.d("GameFragment", "GameFragment created/re-created!")
        with(viewModel) {
            val word = currentScrambledWord.value
            val count = currentWordCount.value
            val score = score.value

            Log.d(
                "GameFragment",
                "Word: $word; Score: $score; Count: $count;"
            )
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup a click listener for the Submit and Skip buttons.
        binding.submit.setOnClickListener { onSubmitWord() }
        binding.skip.setOnClickListener { onSkipWord() }

        // Observe the currentScrambledWord LiveData
        viewModel.currentScrambledWord.observe(viewLifecycleOwner) {
            binding.textViewUnscrambledWord.text = it
        }

        viewModel.currentWordCount.observe(viewLifecycleOwner) {
            binding.wordCount.text = getString(
                R.string.word_count, it, MAX_NO_OF_WORDS
            )
        }

        viewModel.score.observe(viewLifecycleOwner) {
            binding.score.text = getString(R.string.score, it)
        }
    }

    /*
    * Checks the user's word, and updates the score accordingly.
    * Displays the next scrambled word.
    */
    private fun onSubmitWord() {
        val playerWord = binding.textInputEditText.text.toString()

        if (viewModel.isUserWordCorrect(playerWord)) {
            setErrorTextField(false)

            if (!viewModel.nextWord()) {
                showFinalScoreDialog()
            }
        } else {
            setErrorTextField(true)
        }
    }

    /*
     * Skips the current word without changing the score.
     * Increases the word count.
     */
    private fun onSkipWord() {
        if (viewModel.nextWord()) {
            setErrorTextField(false)
        } else {
            showFinalScoreDialog()
        }
    }

    /*
     * Re-initializes the data in the ViewModel and updates the views with the new data, to
     * restart the game.
     */
    private fun restartGame() {
        viewModel.reinitializeData()
        setErrorTextField(false)
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

    /**
     * Creates and shows an AlertDialog with the final score.
     */
    private fun showFinalScoreDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.congratulations))
            .setMessage(getString(R.string.you_scored, viewModel.score.value))
            .setCancelable(false)
            .setNegativeButton(getString(R.string.exit)) { _, _ ->
                exitGame()
            }
            .setPositiveButton(getString(R.string.play_again)) { _, _ ->
                restartGame()
            }
            .show()
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
