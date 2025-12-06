package com.example.exampreparationapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.exampreparationapp.data.AppDatabase
import com.example.exampreparationapp.data.Question
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ResultActivity : AppCompatActivity() {

    private lateinit var db: AppDatabase
    private lateinit var questions: List<Question>
    private lateinit var userAnswers: IntArray

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        db = AppDatabase.getInstance(this)
        userAnswers = intent.getIntArrayExtra("userAnswers") ?: IntArray(0)

        val txtScore = findViewById<TextView>(R.id.txtScore)
        val layoutReview = findViewById<LinearLayout>(R.id.layoutReview)
        val btnBackHome = findViewById<Button>(R.id.btnBackHome)

        lifecycleScope.launch {
            questions = db.questionDao().getAll()

            val score = questions.indices.count { i ->
                userAnswers[i] == questions[i].correctIndex
            }

            withContext(Dispatchers.Main) {
                txtScore.text = "Score: $score / ${questions.size}"

                // Show review
                questions.forEachIndexed { index, question ->
                    val reviewItem = TextView(this@ResultActivity).apply {
                        val userAnswer = userAnswers[index]
                        val correctAnswer = question.correctIndex
                        val isCorrect = userAnswer == correctAnswer

                        val options = listOf(question.optionA, question.optionB, question.optionC, question.optionD)

                        text = """
                            Q${index + 1}: ${question.text}
                            Your answer: ${if (userAnswer >= 0) options[userAnswer] else "Not answered"}
                            Correct answer: ${options[correctAnswer]}
                            ${if (isCorrect) "✓ Correct" else "✗ Incorrect"}
                        """.trimIndent()

                        textSize = 14f
                        setPadding(16, 16, 16, 32)
                        setTextColor(if (isCorrect) Color.GREEN else Color.RED)
                    }
                    layoutReview.addView(reviewItem)
                }
            }
        }

        btnBackHome.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}
