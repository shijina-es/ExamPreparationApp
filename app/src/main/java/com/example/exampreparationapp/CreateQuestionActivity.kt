package com.example.exampreparationapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.exampreparationapp.data.AppDatabase
import com.example.exampreparationapp.data.Question
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CreateQuestionActivity : AppCompatActivity() {

    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_question)

        db = AppDatabase.getInstance(this)

        val edtQuestion = findViewById<EditText>(R.id.edtQuestion)
        val edtOptionA = findViewById<EditText>(R.id.edtOptionA)
        val edtOptionB = findViewById<EditText>(R.id.edtOptionB)
        val edtOptionC = findViewById<EditText>(R.id.edtOptionC)
        val edtOptionD = findViewById<EditText>(R.id.edtOptionD)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroupCorrect)
        val btnSave = findViewById<Button>(R.id.btnSave)

        btnSave.setOnClickListener {
            val questionText = edtQuestion.text.toString().trim()
            val optionA = edtOptionA.text.toString().trim()
            val optionB = edtOptionB.text.toString().trim()
            val optionC = edtOptionC.text.toString().trim()
            val optionD = edtOptionD.text.toString().trim()

            if (questionText.isEmpty() || optionA.isEmpty() || optionB.isEmpty() ||
                optionC.isEmpty() || optionD.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val correctIndex = when (radioGroup.checkedRadioButtonId) {
                R.id.rbA -> 0
                R.id.rbB -> 1
                R.id.rbC -> 2
                R.id.rbD -> 3
                else -> {
                    Toast.makeText(this, "Please select correct answer", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }

            val question = Question(
                text = questionText,
                optionA = optionA,
                optionB = optionB,
                optionC = optionC,
                optionD = optionD,
                correctIndex = correctIndex
            )

            lifecycleScope.launch {
                db.questionDao().insert(question)
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@CreateQuestionActivity, "Saved!", Toast.LENGTH_SHORT).show()
                    edtQuestion.text.clear()
                    edtOptionA.text.clear()
                    edtOptionB.text.clear()
                    edtOptionC.text.clear()
                    edtOptionD.text.clear()
                    radioGroup.clearCheck()
                }
            }
        }
    }
}
