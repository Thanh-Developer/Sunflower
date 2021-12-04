package com.glbank.sunflower.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.glbank.sunflower.databinding.ActivityMainBinding
import com.glbank.sunflower.ui.addtask.AddTaskActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initData()
        observeField()
    }

    private fun initView() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.fab.setOnClickListener {
            val intent = Intent(this@MainActivity, AddTaskActivity::class.java)
            startActivity(intent)
        }

        mainAdapter = MainAdapter {
            val intent = Intent(this@MainActivity, AddTaskActivity::class.java)
            intent.putExtra(TASK_DATA, it)
            startActivity(intent)
        }

        binding.apply {
            rvTask.apply {
                adapter = mainAdapter
            }
        }
    }

    private fun initData() {}

    private fun observeField() {
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.getTaskPaged().collectLatest { pagingData ->
                mainAdapter.submitData(pagingData)
            }
        }
    }

    companion object {
        const val TASK_DATA = "TASK_DATA"
    }
}