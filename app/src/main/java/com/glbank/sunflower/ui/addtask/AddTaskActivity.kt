package com.glbank.sunflower.ui.addtask

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.glbank.sunflower.databinding.ActivityAddNoteBinding
import com.glbank.sunflower.ui.main.MainActivity
import com.google.android.material.snackbar.Snackbar

class AddTaskActivity : AppCompatActivity() {
    private lateinit var viewModel: AddTaskViewModel
    private lateinit var binding: ActivityAddNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initData()
        observeField()
    }

    private fun initView() {
        viewModel = ViewModelProvider(this).get(AddTaskViewModel::class.java)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            viewModel = this@AddTaskActivity.viewModel
            lifecycleOwner = this@AddTaskActivity
            executePendingBindings()
        }
    }

    private fun initData() {
        viewModel.task.value = intent.extras?.getParcelable(MainActivity.TASK_DATA)

        if (viewModel.task.value != null) {
            viewModel.apply {
                title.value = task.value?.title ?: ""
                des.value = task.value?.des ?: ""
            }
        }
    }

    private fun observeField() {
        viewModel.apply {
            isDeleteSuccess.observe(this@AddTaskActivity, {
                finish()
            })

            isInsertSuccess.observe(this@AddTaskActivity, {
                if (it == true) {
                    Snackbar.make(binding.root, "Success", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                    finish()
                }
            })
        }
    }
}