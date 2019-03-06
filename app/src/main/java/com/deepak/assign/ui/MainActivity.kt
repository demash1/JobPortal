package com.deepak.assign.ui

import android.net.Uri
import android.os.Bundle
import androidx.browser.customtabs.CustomTabsIntent
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.deepak.assign.R
import com.deepak.assign.entity.PositionModel
import com.deepak.assign.util.toPositionModel
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import timber.log.Timber
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), JobAdapter.OnItemClickListener {


    lateinit var adapter: JobAdapter

    var jobList = mutableListOf<PositionModel>()

    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        initialize()
        initRecyclerView()
        viewModel.getPosition("", "")
        viewModel.getPositionSecond("")

        viewModel.getJobList().observe(this, Observer {
            jobList.addAll(it)
            adapter.notifyDataSetChanged()
        })
        viewModel.jobResponseSecond.observe(this, Observer {
            it.forEach {
                it.toPositionModel().apply {
                    jobList.add(this)
                }
            }
            Timber.e("mapping ${jobList.size}")

            adapter.notifyDataSetChanged()
        })

    }

    private fun initialize() {
        content_main_search_button.setOnClickListener {
            when {
                content_main_search_edit_text.text.isEmpty() -> "Please enter title"
                else -> {
                    jobList.clear()
                    viewModel.getPosition(
                        content_main_search_edit_text.text.toString(),
                        content_main_search_location_edit_text.text.toString()
                    )
                    viewModel.getPositionSecond(content_main_search_edit_text.text.toString())
                }
            }
        }
    }

    private fun initRecyclerView() {
        adapter = JobAdapter(jobList, this)
        content_main_job_recycler_view.layoutManager = LinearLayoutManager(this)
        content_main_job_recycler_view.adapter = adapter
    }

    override fun onItemClick(url: String) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(url))
    }
}
