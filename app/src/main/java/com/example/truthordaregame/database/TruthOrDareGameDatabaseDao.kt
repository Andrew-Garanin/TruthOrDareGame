package com.example.truthordaregame.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TruthOrDareGameDatabaseDao {

    //---------------------------Question---------------------------
    @Insert
    fun insertQuestion(question: Question)

    @Update
    fun updateQuestion(question: Question)

    @Query("SELECT * FROM question WHERE questionID = :key")
    fun getQuestion(key: Long): Question?

    @Query("DELETE FROM question")
    fun clearQuestions()

    @Query("SELECT * FROM question ORDER BY questionID DESC")
    fun getAllQuestions(): LiveData<List<Question>>

    @Query("SELECT * FROM question ORDER BY questionID DESC LIMIT 1")
    fun getLastQuestion(): Question?

    @Query("DELETE FROM question WHERE questionID = :id")
    fun deleteQuestion(id: Int)

    //---------------------------Dare---------------------------
    @Insert
    fun insertDare(dare: Dare)

    @Update
    fun updateDare(dare: Dare)

    @Query("SELECT * FROM dare WHERE dareID = :key")
    fun getDare(key: Long): Dare?

    @Query("DELETE FROM dare")
    fun clearDares()

    @Query("SELECT * FROM dare ORDER BY dareID DESC")
    fun getAllDares(): LiveData<List<Dare>>

    @Query("SELECT * FROM dare ORDER BY dareID DESC LIMIT 1")
    fun getLastDare(): Dare?

    @Query("DELETE FROM dare WHERE dareID = :id")
    fun deleteDare(id: Int)
}