package com.example.exampreparationapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.exampreparationapp.data.AppDatabase
import com.example.exampreparationapp.data.Question
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PracticeActivity : AppCompatActivity() {

    private lateinit var db: AppDatabase
    private lateinit var questions: List<Question>
    private lateinit var userAnswers: IntArray
    private var currentIndex = 0

    private lateinit var txtQuestionNumber: TextView
    private lateinit var txtQuestion: TextView
    private lateinit var rbOptionA: RadioButton
    private lateinit var rbOptionB: RadioButton
    private lateinit var rbOptionC: RadioButton
    private lateinit var rbOptionD: RadioButton
    private lateinit var radioGroup: RadioGroup
    private lateinit var btnNext: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practice)

        db = AppDatabase.getInstance(this)

        txtQuestionNumber = findViewById(R.id.txtQuestionNumber)
        txtQuestion = findViewById(R.id.txtQuestion)
        rbOptionA = findViewById(R.id.rbOptionA)
        rbOptionB = findViewById(R.id.rbOptionB)
        rbOptionC = findViewById(R.id.rbOptionC)
        rbOptionD = findViewById(R.id.rbOptionD)
        radioGroup = findViewById(R.id.radioGroupOptions)
        btnNext = findViewById(R.id.btnNext)

        lifecycleScope.launch {
            questions = db.questionDao().getAll().shuffled()

            if (questions.isEmpty()) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@PracticeActivity, "No questions available. Please create some first!", Toast.LENGTH_LONG).show()
                    finish()
                }
                return@launch
            }

            userAnswers = IntArray(questions.size) { -1 }

            withContext(Dispatchers.Main) {
                showQuestion()
            }
        }

        btnNext.setOnClickListener {
            val selectedIndex = when (radioGroup.checkedRadioButtonId) {
                R.id.rbOptionA -> 0
                R.id.rbOptionB -> 1
                R.id.rbOptionC -> 2
                R.id.rbOptionD -> 3
                else -> -1
            }

            if (selectedIndex == -1) {
                Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            userAnswers[currentIndex] = selectedIndex

            if (currentIndex == questions.lastIndex) {
                // Go to result screen
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("userAnswers", userAnswers)
                startActivity(intent)
                finish()
            } else {
                currentIndex++
                showQuestion()
            }
        }
    }

    private fun showQuestion() {
        val question = questions[currentIndex]
        txtQuestionNumber.text = "Question ${currentIndex + 1} of ${questions.size}"
        txtQuestion.text = question.text
        rbOptionA.text = question.optionA
        rbOptionB.text = question.optionB
        rbOptionC.text = question.optionC
        rbOptionD.text = question.optionD
        radioGroup.clearCheck()

        btnNext.text = if (currentIndex == questions.lastIndex) "Finish" else "Next"
    }
}
