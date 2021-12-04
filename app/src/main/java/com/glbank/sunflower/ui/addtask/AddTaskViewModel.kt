package com.glbank.sunflower.ui.addtask

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.glbank.sunflower.data.db.TaskDataBase
import com.glbank.sunflower.data.model.Task
import com.glbank.sunflower.data.repository.TaskRepository
import com.glbank.sunflower.data.repository.TaskRepositoryImpl
import kotlinx.coroutines.launch

class AddTaskViewModel(application: Application) : AndroidViewModel(application) {
    private var taskRepository: TaskRepository

    var isDeleteSuccess = MutableLiveData<Boolean>()
    var isInsertSuccess = MutableLiveData<Boolean>()
    var title = MutableLiveData<String>().apply { value = "" }
    var des = MutableLiveData<String>().apply { value = "" }
    var task = MutableLiveData<Task>().apply { value = null }

    init {
        val dataBase = TaskDataBase.getInstance(application)
        taskRepository = TaskRepositoryImpl(dataBase.taskDao())
    }

    private suspend fun insertNote(task: Task) = taskRepository.insertTask(task)

    private suspend fun updateNote(task: Task) = taskRepository.updateTask(task)

    fun onSaveNote() {
        val title = title.value.toString().trim()
        val desc = des.value.toString().trim()
        val task = Task(title = title, des = desc)

        if (title.isNullOrEmpty() || desc.isNullOrEmpty()) {
            isInsertSuccess.value = false
            return
        }

        viewModelScope.launch {
            insertNote(task).also {
                isInsertSuccess.value = true
            }
        }
    }

    fun onCloseOrDelete() {
        isDeleteSuccess.value = false
    }

}