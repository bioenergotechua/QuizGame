package com.example.quizgame

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

@Suppress("DEPRECATION")
class GameActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var questionTextView: TextView
    private lateinit var radioGroup: RadioGroup

    private lateinit var quizzes: Array<Quiz>
    private var currentQuizIndex = 0
    private var correctAnswers = 0
    private var incorrectAnswers = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        // Initialize UI components
        imageView = findViewById(R.id.imageView)
        questionTextView = findViewById(R.id.questionTextView)
        radioGroup = findViewById(R.id.radioGroup)

        quizzes = intent.getParcelableArrayExtra("quizzes")?.map { it as Quiz }?.toTypedArray() ?: emptyArray()

        // Display the first quiz
        displayQuiz()

        // Handle Ok button click
        val okButton: Button = findViewById(R.id.okButton)
        okButton.setOnClickListener {
            checkAnswer()
        }
    }

    @SuppressLint("DiscouragedApi")
    private fun displayQuiz() {
        // Check if there are more quizzes to display
        if (currentQuizIndex < quizzes.size) {
            // Retrieve the current quiz
            val currentQuiz = quizzes[currentQuizIndex]

            // Update UI components with quiz information
            val imageResId = resources.getIdentifier(currentQuiz.pictureName, "drawable", packageName)
            if (imageResId != 0) {
                // Set the image resource if it exists
                imageView.setImageResource(imageResId)
            } else {
                // Set a default image or handle the case where the resource is not found
                imageView.setImageResource(R.drawable.standart)
            }

            questionTextView.text = currentQuiz.question

            // Clear existing radio buttons
            radioGroup.removeAllViews()

            // Add new radio buttons for answer options
            for (option in currentQuiz.options) {
                val radioButton = RadioButton(this)
                radioButton.text = option
                radioGroup.addView(radioButton)
            }
        } else {
            // No more quizzes to display, finish the activity
            finish()
        }
    }


    private fun checkAnswer() {
        // Get the selected radio button index
        val selectedRadioButtonIndex = radioGroup.indexOfChild(findViewById(radioGroup.checkedRadioButtonId))

        // Check if the selected answer is correct
        if (selectedRadioButtonIndex != -1 && currentQuizIndex < quizzes.size) {
            val selectedAnswer = quizzes[currentQuizIndex].options[selectedRadioButtonIndex]
            if (selectedAnswer == quizzes[currentQuizIndex].correctAnswer) {
                correctAnswers++
            } else {
                incorrectAnswers++
            }

            // Move to the next quiz
            currentQuizIndex++
            displayQuiz()
        } else {
            // Handle the case where no answer is selected (skipped)
            incorrectAnswers++
        }

        // Check if all quizzes are answered
        if (currentQuizIndex >= quizzes.size) {
            // All quizzes are answered, move to the ResultsActivity
            val intent = Intent(this, ResultsActivity::class.java)
            intent.putExtra("correctAnswers", correctAnswers)
            intent.putExtra("incorrectAnswers", incorrectAnswers)
            startActivity(intent)
            finish()
        }
    }
}
