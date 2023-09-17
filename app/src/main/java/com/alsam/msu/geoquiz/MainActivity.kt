package com.alsam.msu.geoquiz

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.snackbar.Snackbar
import com.alsam.msu.geoquiz.databinding.ActivityMainBinding  // Import your binding class

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var prevButton: Button  // Add the previous button

    private val Questions = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )
    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout using data binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.trueButton.setOnClickListener {
            val snackbar = Snackbar.make(
                it,
                "Correct",
                Snackbar.LENGTH_LONG
            )
            snackbar.show()
        }

        binding.falseButton.setOnClickListener {
            val snackbar = Snackbar.make(
                it,
                "Incorrect",
                Snackbar.LENGTH_LONG
            )
            snackbar.setTextColor(Color.BLACK)
            snackbar.setBackgroundTint(Color.RED)
            snackbar.show()
        }

        binding.nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % Questions.size
            updateQuestion()
        }

        // Initialize the previous button and set its click listener
        binding.prevButton.setOnClickListener {
            currentIndex = (currentIndex - 1 + Questions.size) % Questions.size
            updateQuestion()
        }

        updateQuestion()
    }

    private fun updateQuestion() {
        val questionTextResId = Questions[currentIndex].textResId
        binding.questionTextView.setText(questionTextResId)
    }
}
