package com.example.quizgame

// MainActivity.kt
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Handle Start Game button click
        val startGameButton: Button = findViewById(R.id.startGameButton)
        startGameButton.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra("quizzes", QuizRepository.quizzes.toTypedArray())
            startActivity(intent)
        }

        // Handle Close App button click
        val closeAppButton: Button = findViewById(R.id.closeAppButton)
        closeAppButton.setOnClickListener {
            finish()
        }
    }
}
