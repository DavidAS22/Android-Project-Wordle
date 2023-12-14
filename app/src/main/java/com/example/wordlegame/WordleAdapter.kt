package com.example.wordlegame

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wordleproject.R


enum class LetterState {
    UNKNOWN,
    CORRECT,
    IN_WRONG_POSITION,
    NOT_IN_WORD
}

class WordleAdapter(private val guess: String, private val targetWord: String) :
    RecyclerView.Adapter<WordleAdapter.ViewHolder>() {

    private val letters = guess.toCharArray()
    private val letterStates = Array(guess.length) { LetterState.UNKNOWN }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.wordle_letter_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val letter = letters[position]
        val letterState = letterStates[position]
        val color = getColor(letterState)

        val letterTextView = holder.itemView.findViewById<TextView>(R.id.letter_text_view)
        val backgroundView = holder.itemView.findViewById<View>(R.id.letter_background_view)

        letterTextView.text = letter.toString()
        backgroundView.setBackgroundColor(color)
    }

    private fun getColor(letterState: LetterState): Int {
        return when (letterState) {
            LetterState.CORRECT -> R.color.wordle_correct
            LetterState.IN_WRONG_POSITION -> R.color.wordle_wrong_position
            LetterState.NOT_IN_WORD -> R.color.wordle_not_in_word
            else -> R.color.wordle_unknown
        }
    }

    override fun getItemCount(): Int {
        return letters.size
    }

    fun evaluateGuess() {
        for (i in 0 until guess.length) {
            val guessLetter = guess[i]
            val targetLetter = targetWord[i]

            if (guessLetter == targetLetter) {
                letterStates[i] = LetterState.CORRECT
            } else if (targetWord.contains(guessLetter)) {
                letterStates[i] = LetterState.IN_WRONG_POSITION
            } else {
                letterStates[i] = LetterState.NOT_IN_WORD
            }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
