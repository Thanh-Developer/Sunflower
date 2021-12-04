package com.glbank.sunflower.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.glbank.sunflower.data.db.TaskDao
import com.glbank.sunflower.data.model.Task
import kotlinx.coroutines.flow.Flow

class TaskRepositoryImpl constructor(
    private val taskDao: TaskDao
) :TaskRepository {
    override suspend fun insertTask(task: Task) = taskDao.insertTask(task)

    override suspend fun deleteTask(task: Task) = taskDao.deleteTask(task)

    override suspend fun updateTask(task: Task) = taskDao.updateTask(task)

    override suspend fun getTasks(): List<Task> = taskDao.getTasks()

    override fun getTaskPaged(config: PagingConfig): Flow<PagingData<Task>> {
        return Pager(config = config) { taskDao.getTaskPaged() }.flow
    }
}