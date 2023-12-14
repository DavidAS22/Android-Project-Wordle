package com.example.wordlegame

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.wordleproject.R

class MainActivity : AppCompatActivity() {

    private lateinit var guessInput: EditText
    private lateinit var submitButton: Button
    private lateinit var wordleGrid: RecyclerView

    private val wordList = listOf(
        "apple",
        "grape",
        "mango"
    )

    private val targetWord = wordList.random()
    private var currentGuess = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        guessInput = findViewById(R.id.guess_input)
        submitButton = findViewById(R.id.submit_button)
        wordleGrid = findViewById(R.id.wordle_grid)

        submitButton.setOnClickListener {
            val guess = guessInput.text.toString().lowercase()

            if (guess.length != 5) {
                Toast.makeText(this, "Please enter a 5-letter word", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val wordleAdapter = WordleAdapter(guess, targetWord)
            wordleGrid.adapter = wordleAdapter

            currentGuess++
            guessInput.text.clear()

            if (guess == targetWord) {
                Toast.makeText(this, "Congratulations! You guessed the word in $currentGuess guesses.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
