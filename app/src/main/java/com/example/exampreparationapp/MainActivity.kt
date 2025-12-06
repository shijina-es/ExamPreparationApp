package com.example.exampreparationapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnCreateQuestion = findViewById<Button>(R.id.btnCreateQuestion)
        val btnStartPractice = findViewById<Button>(R.id.btnStartPractice)

        btnCreateQuestion.setOnClickListener {
            startActivity(Intent(this, CreateQuestionActivity::class.java))
        }

        btnStartPractice.setOnClickListener {
            startActivity(Intent(this, PracticeActivity::class.java))
        }
    }
}
