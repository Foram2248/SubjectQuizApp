package com.example.subjectquizapp

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var spinnerSubjects: Spinner
    private lateinit var buttonStartQuiz: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinnerSubjects = findViewById(R.id.spinner_subjects)
        buttonStartQuiz = findViewById(R.id.button_start_quiz)

        val subjects = listOf("Machine Learning", "AI", "Mobile Programming", "Data Science", "Natural Language Processing")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, subjects)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerSubjects.adapter = adapter

        buttonStartQuiz.setOnClickListener {
            val selectedSubject = spinnerSubjects.selectedItem.toString()
            val intent = Intent(this, QuizActivity::class.java)
            intent.putExtra("subject", selectedSubject)
            startActivity(intent)
        }
    }
}
