package im.syf.unscramble.ui.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import im.syf.unscramble.ui.game.Words.MAX_NO_OF_WORDS
import im.syf.unscramble.ui.game.Words.SCORE_INCREASE

class GameViewModel : ViewModel() {

    private val _currentScrambledWord = MutableLiveData<String>()
    val currentScrambledWord: LiveData<String>
        get() = _currentScrambledWord

    private val _currentWordCount = MutableLiveData(0)
    val currentWordCount: LiveData<Int>
        get() = _currentWordCount

    private var _score = 0
    val score: Int
        get() = _score

    private var wordsList: MutableList<String> = mutableListOf()

    private lateinit var currentWord: String

    init {
        Log.d("GameFragment", "GameViewModel created!")
        getNextWord()
    }

    fun isUserWordCorrect(playerWord: String): Boolean {
        if (playerWord.equals(currentWord, true)) {
            increaseScore()
            return true
        }
        return false
    }

    /**
     * Returns true if the current word count is less than [MAX_NO_OF_WORDS].
     * Updates the next word.
     */
    fun nextWord(): Boolean {
        return if (currentWordCount.value!! < MAX_NO_OF_WORDS) {
            getNextWord()
            true
        } else false
    }

    /**
     * Re-initializes the game data to restart the game.
     */
    fun reinitializeData() {
        _score = 0
        _currentWordCount.value = 0
        wordsList.clear()
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
            _currentScrambledWord.value = String(tempWord)
            _currentWordCount.value = _currentWordCount.value?.inc()
            wordsList.add(currentWord)
        }
    }

    private fun increaseScore() {
        _score += SCORE_INCREASE
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("GameFragment", "GameViewModel destroyed!")
    }
}
