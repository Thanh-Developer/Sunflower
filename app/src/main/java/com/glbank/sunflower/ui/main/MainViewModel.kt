package com.glbank.sunflower.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.paging.PagingConfig
import com.glbank.sunflower.data.db.TaskDataBase
import com.glbank.sunflower.data.repository.TaskRepository
import com.glbank.sunflower.data.repository.TaskRepositoryImpl

class MainViewModel(application: Application) : AndroidViewModel(application) {
    val PAGE_SIZE = 5

    val PAGE_INITIAL_LOAD_SIZE_HINT = 10

    val PAGE_PREFETCH_DISTANCE = 5

    private var taskRepository: TaskRepository

    private val config = PagingConfig(
        // Number of items loaded for a page in one go from DataSource
        pageSize = PAGE_SIZE,
        // The number of items in the first load, if not set, it defaults = 3 * pageSize
        initialLoadSize = PAGE_INITIAL_LOAD_SIZE_HINT,
        // Determine the distance (number of items) from the loaded content to load the data, if not set, it defaults to pageSize.
        prefetchDistance = PAGE_PREFETCH_DISTANCE,
        // PagedList will display null placeholders for items that have not been loaded content, by default it will be true.
        enablePlaceholders = true
    )

    init {
        val dataBase = TaskDataBase.getInstance(application)
        taskRepository = TaskRepositoryImpl(dataBase.taskDao())
    }

    suspend fun getTasks() = taskRepository.getTasks()

    fun getTaskPaged() = taskRepository.getTaskPaged(config)
}