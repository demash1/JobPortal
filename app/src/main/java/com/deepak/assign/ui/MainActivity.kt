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
import org.jetbrains.anko.intentFor
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), JobAdapter.OnItemClickListener {

    lateinit var adapter: JobAdapter
    var jobList = mutableListOf<PositionModel>()
    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(activity_main_toolbar)
        initRecyclerView()
        initialize()
    }

    private fun initialize() {
        viewModel.getPosition("", "")
        viewModel.getPositionSecond("")
        observer()
        activity_main_search_image_view.setOnClickListener {
            startActivity(intentFor<SearchActivity>())
        }
    }

    /***
     * Observes Live data
     */
    private fun observer() {
        viewModel.getJobList().observe(this, Observer {
            jobList.addAll(it)
            adapter.notifyDataSetChanged()
        })
        viewModel.jobResponseSecond.observe(this, Observer {
            it.forEach { searchModel ->
                searchModel.toPositionModel().apply {
                    jobList.add(this)
                }
            }
            adapter.notifyDataSetChanged()
        })
    }


    /***
     * Initialize recycler view
     */
    private fun initRecyclerView() {
        adapter = JobAdapter(jobList, this)
        activity_main_job_recycler_view.layoutManager = LinearLayoutManager(this)
        activity_main_job_recycler_view.adapter = adapter
    }

    /***
     * Handles recycler view item click
     */
    override fun onItemClick(url: String) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(url))
    }
}
