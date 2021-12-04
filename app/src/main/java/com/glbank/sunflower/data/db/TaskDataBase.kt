package com.glbank.sunflower.data.db

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.glbank.sunflower.data.model.Task

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class TaskDataBase : RoomDatabase() {
    abstract fun taskDao(): TaskDao

    companion object {
        const val DB_NAME = "task_database.db"
        var instance: TaskDataBase? = null

        fun getInstance(application: Application): TaskDataBase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    application.applicationContext,
                    TaskDataBase::class.java,
                    DB_NAME
                ).fallbackToDestructiveMigration().build()
            }
            return instance as TaskDataBase
        }
    }
}