package com.example.quizgame

// ResultsActivity.kt
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        val correctAnswers = intent.getIntExtra("correctAnswers", 0)
        val incorrectAnswers = intent.getIntExtra("incorrectAnswers", 0)

        val resultsTextView: TextView = findViewById(R.id.resultsTextView)
        val correctAnswersTextView: TextView = findViewById(R.id.correctAnswersTextView)
        val incorrectAnswersTextView: TextView = findViewById(R.id.incorrectAnswersTextView)
        val backButton: Button = findViewById(R.id.backButton)

        // Display results
        resultsTextView.text = "Results"
        correctAnswersTextView.text = "Correct Answers: $correctAnswers"
        incorrectAnswersTextView.text = "Incorrect Answers: $incorrectAnswers"

        // Handle Back button click
        backButton.setOnClickListener {
            // Navigate back to the main activity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
