package com.example.imad_assignment_two

import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.tooling.preview.Preview
import com.example.imad_assignment_two.ui.theme.IMAD_Assignment_TwoTheme
class MainActivity : ComponentActivity() {
    //Simple arrays
    private val questions = arrayOf(
        "T20 cricket is the most popular format in the world",
        "Virat Kohli has 83 hundreds in all formats",
        "Ab de Villiars has a T20 hundred",
        "India is the most successful cricketing nation in history",
        "The Proteas won the World Test Championship in 2025"
    )

    private val answers = arrayOf(
        true, true, false, false, true
    )

    private var currentQuestion = 0
    private var score = 0
    private var feedbackList = mutableListOf<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showWelcomeScreen()
    }

    private fun showWelcomeScreen() {
        setContentView(R.layout.activity_welcome)
        val startButton = findViewById<Button>(R.id.btnShow)
        startButton.setOnClickListener {
            currentQuestion = 0
            score = 0
            feedbackList.clear()
            showQuestionScreen()
        }
    }

    private fun showQuestionScreen() {
        //Declarations of the screens that are going to be used for the app
        setContentView(R.layout.activity_question1)
        setContentView(R.layout.activity_question2)
        setContentView(R.layout.activity_question3)
        setContentView(R.layout.activity_question4)
        setContentView(R.layout.activity_question5)

        //Main declarations for the questions page
        val questionText = findViewById<TextView>(R.id.questionText)
        val trueButton = findViewById<Button>(R.id.btnHack)
        val falseButton = findViewById<Button>(R.id.btnMyth)
        val feedbackText = findViewById<TextView>(R.id.txtRespond)
        val nextButton = findViewById<Button>(R.id.btnNext1)

        questionText.text = questions[currentQuestion]
        feedbackText.text = " "
        var answered = false

        trueButton.setOnClickListener {
            if (!answered) {
                checkAnswer(true, feedbackText)
                answered = true
            }
        }

        falseButton.setOnClickListener {
            if (!answered) {
                checkAnswer(false, feedbackText)
                answered = false
            }
        }

        nextButton.setOnClickListener {
            currentQuestion++
            if (currentQuestion < questions.size) {
                showQuestionScreen()
            } else {
                showScoreScreen()
            }
        }
    }
    //shows how the app will respond relative to if you got a question right or wrong
    //it is the processing power of the app and happening while we are using the app
    private fun checkAnswer(userAnswer: Boolean, feedbackText: TextView) {
        val correct = answers[currentQuestion]
        //start of the if statement showing the difference between right and wrong
        if (userAnswer == correct) {
            feedbackText.text = "You are CORRECT!"
            score++
            feedbackList.add("g${currentQuestion + 1}: ")
        } else {
            feedbackText.text = "WRONG!"
            feedbackList.add("g${currentQuestion + 1}: ")
        }
    }

    private fun showScoreScreen() {
        //linking the review page to the showScoreScreen
        setContentView(R.layout.activity_reviewpage)
        //Declarations for the review marks page
        val scoreText = findViewById<TextView>(R.id.textShow)
        val finalFeedback = findViewById<TextView>(R.id.txtFeedback)
        val reviewButton = findViewById<TextView>(R.id.scoreCheck)
        val exitButton = findViewById<TextView>(R.id.btnExit)

        //Coding showing the score you got and if and else statement for whether you did well or badly
        scoreText.text = "you scored $score out of ${questions.size}"
        finalFeedback.text = if (score >= 3) "Awesome! You know ball!" else "Do better next time!"

        //review your marks through this button shows compiled data
        reviewButton.setOnClickListener {
            val facts = questions.mapIndexed { index, q ->
                "${(index + 1)}, $q\nAnswer: ${answers[index]}"
            }.joinToString("\n\n")
            Toast.makeText(this, facts, Toast.LENGTH_LONG).show()
        }
        //exit button to end the operator and go to the start of the app
        exitButton.setOnClickListener {
            finish()
        }
    }
}







