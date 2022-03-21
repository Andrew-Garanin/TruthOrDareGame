package com.example.truthordaregame

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.runner.RunWith
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.example.truthordaregame.database.TruthOrDareGameDatabase
import com.example.truthordaregame.database.TruthOrDareGameDatabaseDao
import com.example.truthordaregame.database.Question
import com.example.truthordaregame.database.Dare
import org.junit.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class TruthOrDareGameDatabaseTest {

    private lateinit var truthOrDareGameDao: TruthOrDareGameDatabaseDao
    private lateinit var db: TruthOrDareGameDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.databaseBuilder(context, TruthOrDareGameDatabase::class.java, "truth_dare_game_db")
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries().createFromAsset("database/truth_dare_game_db.db")
            .build()
        truthOrDareGameDao = db.getTruthOrDareGameDatabaseDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

//    @Test
//    @Throws(Exception::class)
//    fun insertAndGetNight() {
//        val question = Question(1, "Jopa", 1)
//        truthOrDareGameDao.insertQuestion(question)
//        val tonight = truthOrDareGameDao.getLastQuestion()
//        assertEquals(tonight?.questionString, "Jopa")
//    }

    @Test
    @Throws(Exception::class)
    fun getQuestion() {
        val question = truthOrDareGameDao.getQuestion(1)?.questionID
        assertEquals(question, 1)
    }

}