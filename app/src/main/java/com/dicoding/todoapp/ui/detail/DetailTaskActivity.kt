package com.dicoding.todoapp.ui.detail

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dicoding.todoapp.R
import com.dicoding.todoapp.ui.ViewModelFactory
import com.dicoding.todoapp.utils.DateConverter
import com.dicoding.todoapp.utils.TASK_ID

class DetailTaskActivity : AppCompatActivity() {

    private lateinit var detailTaskViewModel: DetailTaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)

        //TODO 11 : Show detail task and implement delete action
        detailTaskViewModel = ViewModelProvider(
            this, ViewModelFactory.getInstance(this))[DetailTaskViewModel::class.java]
        detailTaskViewModel.setTaskId(intent.getIntExtra(TASK_ID, 0))
        detailTaskViewModel.apply {
            val detailTitle: TextView = findViewById(R.id.detail_ed_title)
            val detailDescription: TextView = findViewById(R.id.detail_ed_description)
            val detailDueDate: TextView = findViewById(R.id.detail_ed_due_date)
            val btnDelete: Button = findViewById(R.id.btn_delete_task)

            task.observe(this@DetailTaskActivity) {

                if (it != null) {
                    detailTitle.text = it.title
                    detailDescription.text = it.description
                    detailDueDate.text = DateConverter.convertMillisToString(it.dueDateMillis)

                    btnDelete.setOnClickListener {
                        detailTaskViewModel.deleteTask()
                        Toast.makeText(applicationContext, "Task has been deleted", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }
            }
        }
    }
}