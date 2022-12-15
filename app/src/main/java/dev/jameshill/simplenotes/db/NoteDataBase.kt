package dev.jameshill.simplenotes.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDataBase: RoomDatabase() {
    /* The following line tells Room to use the data access methods specified in NoteDao */
    abstract val noteDao: NoteDao

    companion object {
        @Volatile
        private var INSTANCE: NoteDataBase? = null

        /* This method is used to get an instance of the db that will look nearly the same
        * for every db you create */
        fun getInstance(context: Context): NoteDataBase {
            synchronized(this) {
                var instance = INSTANCE
                /* If the db doesn't already exist, getInstance() creates it */
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        NoteDataBase::class.java,
                        "note_database"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}