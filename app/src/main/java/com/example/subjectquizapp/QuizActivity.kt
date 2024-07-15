package com.example.subjectquizapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class QuizActivity : AppCompatActivity() {

    private lateinit var textQuestion: TextView
    private lateinit var radioGroup: RadioGroup
    private lateinit var radioOption1: RadioButton
    private lateinit var radioOption2: RadioButton
    private lateinit var radioOption3: RadioButton
    private lateinit var radioOption4: RadioButton
    private lateinit var buttonSubmit: Button
    private lateinit var buttonGoBack: Button

    private var currentQuestionIndex = 0
    private var questions: List<Question> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        textQuestion = findViewById(R.id.text_question)
        radioGroup = findViewById(R.id.radio_group)
        radioOption1 = findViewById(R.id.radio_option1)
        radioOption2 = findViewById(R.id.radio_option2)
        radioOption3 = findViewById(R.id.radio_option3)
        radioOption4 = findViewById(R.id.radio_option4)
        buttonSubmit = findViewById(R.id.button_submit)
        buttonGoBack = findViewById(R.id.button_go_back)

        val subject = intent.getStringExtra("subject")
        // Initialize questions based on the subject
        questions = getQuestionsForSubject(subject)

        displayQuestion()

        buttonSubmit.setOnClickListener {
            val selectedOption = findViewById<RadioButton>(radioGroup.checkedRadioButtonId)
            val answer = selectedOption.text.toString()
            val correctAnswer = questions[currentQuestionIndex].correctAnswer

            if (answer == correctAnswer) {
                Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Incorrect. The correct answer is $correctAnswer", Toast.LENGTH_SHORT).show()
            }

            // Move to the next question or finish the quiz
            currentQuestionIndex++
            if (currentQuestionIndex < questions.size) {
                displayQuestion()
            } else {
                // Finish quiz or navigate back to main activity
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }

        buttonGoBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun displayQuestion() {
        val currentQuestion = questions[currentQuestionIndex]
        textQuestion.text = currentQuestion.question
        radioOption1.text = currentQuestion.options[0]
        radioOption2.text = currentQuestion.options[1]
        radioOption3.text = currentQuestion.options[2]
        radioOption4.text = currentQuestion.options[3]
    }

    private fun getQuestionsForSubject(subject: String?): List<Question> {
        // Define your questions and answers here for each subject
        return when (subject) {
            "Machine Learning" -> listOf(
                Question("1. What is the primary goal of machine learning?", listOf(
                    "To create data",
                    "To analyze algorithms",
                    "To make predictions",
                    "To improve database performance"
                ), "To make predictions"),
                Question("2. What is overfitting in machine learning?", listOf(
                    "Underfitting the model",
                    "Fitting the training data too closely",
                    "Generalizing the model",
                    "Optimizing the model parameters"
                ), "Fitting the training data too closely")
                // Add more questions for Machine Learning here as needed
            )
            "AI" -> listOf(
                Question("1. What does AI stand for?", listOf(
                    "Artificial Information",
                    "Automated Interface",
                    "Artificial Intelligence",
                    "Automated Intelligence"
                ), "Artificial Intelligence"),


                // Add more questions for AI here as needed
            )
            "Natural Language Processing" -> listOf(
                Question("1. What is the term for the process of reducing a word to its base form by removing suffixes?", listOf(
                    "Tokenization",
                    "Stopword removal",
                    "Lemmatization",
                    "Stemming"
                ), "Lemmatization"),
                Question("2. Which text preprocessing step is most critical for ensuring privacy in redacting sensitive information like names or addresses?", listOf(
                    "Tokenization",
                    "Stopword removal",
                    "Lemmatization",
                    "Stemming"
                ), "Lemmatization")

                // Add more questions for AI here as needed
            )
            "Mobile Programming" -> listOf(
                Question("1. Which platform does Kotlin primarily target?", listOf(
                    "Python Bytecode",
                    "JavaScript",
                    "JVM (Java Virtual Machine) Bytecode",
                    "PHP"
                ), "JVM (Java Virtual Machine) Bytecode"),

                )
            "Data Science" -> listOf(
                Question("1. What is the primary goal of exploratory data analysis (EDA)?", listOf(
                    "Making predictions",
                    "Summarizing data characteristics",
                    "Testing hypotheses",
                    "Model deployment"
                ), "Summarizing data characteristics"),

                )
            // Define questions for other subjects similarly
            else -> emptyList()
        }
    }

    data class Question(
        val question: String,
        val options: List<String>,
        val correctAnswer: String
    )
}
