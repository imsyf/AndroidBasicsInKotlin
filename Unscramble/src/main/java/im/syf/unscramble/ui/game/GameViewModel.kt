package im.syf.unscramble.ui.game

import android.util.Log
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    private lateinit var _currentScrambledWord: String
    val currentScrambledWord: String
        get() = _currentScrambledWord

    private var _currentWordCount = 0
    private var wordsList: MutableList<String> = mutableListOf()

    private lateinit var currentWord: String

    init {
        Log.d("GameFragment", "GameViewModel created!")
        getNextWord()
    }

    /**
     * Updates [currentWord] and [currentScrambledWord] with the next word.
     */
    private fun getNextWord() {
        currentWord = Words.list.random()
        val tempWord = currentWord.toCharArray()
        tempWord.shuffle()

        // Make sure tempWord is scrambled
        while (String(tempWord).equals(currentWord, false)) {
            tempWord.shuffle()
        }

        if (wordsList.contains(currentWord)) {
            getNextWord()
        } else {
            _currentScrambledWord = String(tempWord)
            ++_currentWordCount
            wordsList.add(currentWord)
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("GameFragment", "GameViewModel destroyed!")
    }
}
