package com.glbank.sunflower.data.repository

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.glbank.sunflower.data.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    suspend fun insertTask(task: Task)
    suspend fun deleteTask(task: Task)
    suspend fun updateTask(task: Task)
    suspend fun getTasks(): List<Task>
    fun getTaskPaged(config: PagingConfig): Flow<PagingData<Task>>
}