package com.deepak.assign.ui

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.deepak.assign.R
import com.deepak.assign.entity.PositionModel
import com.google.gson.Gson
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var gson: Gson

    lateinit var adapter: JobAdapter

    var jobList = mutableListOf<PositionModel>()

    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        initRecyclerView()
        viewModel.getPosition()
        viewModel.getJobList().observe(this, Observer {
            jobList.clear()
            jobList.addAll(it)
            adapter.notifyDataSetChanged()

        })

    }

    private fun initRecyclerView() {
        adapter = JobAdapter(jobList)
        content_main_job_recycler_view.layoutManager = LinearLayoutManager(this)
        content_main_job_recycler_view.adapter = adapter
    }
}
