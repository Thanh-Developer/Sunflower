package com.glbank.sunflower.data.db

import androidx.paging.PagingSource
import androidx.room.*
import com.glbank.sunflower.data.model.Task

@Dao
interface TaskDao {
    @Insert
    suspend fun insertTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("SELECT * FROM Task")
    suspend fun getTasks(): List<Task>

    @Query("SELECT * FROM task")
    fun getTaskPaged(): PagingSource<Int, Task>

    @Update
    suspend fun updateTask(task: Task)
}