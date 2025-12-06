package com.example.exampreparationapp.data

import androidx.room.*

@Dao
interface QuestionDao {

    @Insert
    suspend fun insert(question: Question)

    @Query("SELECT * FROM questions")
    suspend fun getAll(): List<Question>

    @Query("DELETE FROM questions")
    suspend fun deleteAll()
}
