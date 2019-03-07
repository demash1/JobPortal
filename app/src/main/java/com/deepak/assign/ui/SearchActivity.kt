package com.deepak.assign.ui

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.CheckBox
import androidx.browser.customtabs.CustomTabsIntent
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.deepak.assign.R
import com.deepak.assign.entity.PositionModel
import com.deepak.assign.util.hideSoftKeyboard
import com.deepak.assign.util.toPositionModel
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.AutocompleteActivity
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.content_main.*
import org.jetbrains.anko.toast
import java.util.*
import javax.inject.Inject


class SearchActivity : DaggerAppCompatActivity(), JobAdapter.OnItemClickListener {
    lateinit var adapter: JobAdapter
    var jobList = mutableListOf<PositionModel>()
    @Inject
    lateinit var viewModel: MainViewModel
    private var providerFirstStatus = false
    private var providerSecondStatus = false
    private val fields = Arrays.asList(Place.Field.ID, Place.Field.NAME)!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_main)
        setSupportActionBar(content_main_toolbar)
        initRecyclerView()
        initialize()
    }

    private fun initRecyclerView() {
        adapter = JobAdapter(jobList, this)
        content_main_job_recycler_view.layoutManager = LinearLayoutManager(this)
        content_main_job_recycler_view.adapter = adapter
    }

    private fun initialize() {
        observer()
        hideSoftKeyboard(this)
        if (!Places.isInitialized()) {
            Places.initialize(applicationContext, getString(R.string.google_maps_key))
        }
        content_main_filter_image_view.setOnClickListener {
            showBottomSheetDialog()
        }
        content_main_search_location_image_view.setOnClickListener {
            val intent = Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields).build(this)
            startActivityForResult(intent, 1)
        }
        content_main_search_button.setOnClickListener {
            jobList.clear()
            hideSoftKeyboard(this)
            when {
                content_main_search_edit_text.text.isEmpty() -> toast("Please enter title")
                else -> {
                    if (providerFirstStatus && !providerSecondStatus) {
                        viewModel.getPosition(
                            content_main_search_edit_text.text.toString(),
                            content_main_search_location_edit_text.text.toString()
                        )
                    } else if (providerSecondStatus && !providerFirstStatus)
                        viewModel.getPositionSecond(content_main_search_edit_text.text.toString())
                    else {
                        viewModel.getPosition(
                            content_main_search_edit_text.text.toString(),
                            content_main_search_location_edit_text.text.toString()
                        )
                        viewModel.getPositionSecond(content_main_search_edit_text.text.toString())
                    }
                }
            }
        }
    }

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

    private fun showBottomSheetDialog() {
        val mBottomSheetDialog = BottomSheetDialog(this)
        val sheetView = layoutInflater.inflate(R.layout.view_bottom_sheet, null)
        mBottomSheetDialog.setContentView(sheetView)
        mBottomSheetDialog.show()
        val providerFirst = sheetView.findViewById<CheckBox>(R.id.bottom_sheet_provider_first_check_box)
        val providerSecond = sheetView.findViewById<CheckBox>(R.id.bottom_sheet_provider_second_check_box)
        providerFirst.isChecked = providerFirstStatus
        providerSecond.isChecked = providerSecondStatus
        providerFirst.setOnCheckedChangeListener { _, p1 ->
            providerFirstStatus = p1
        }
        providerSecond.setOnCheckedChangeListener { _, p1 ->
            providerSecondStatus = p1
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1) {
            when (resultCode) {
                Activity.RESULT_OK -> {
                    val place = Autocomplete.getPlaceFromIntent(data!!)
                    content_main_search_location_edit_text.setText(place.name)
                }
                AutocompleteActivity.RESULT_ERROR -> {
                    val status = Autocomplete.getStatusFromIntent(data!!)
                    toast(status.statusMessage.toString())
                }
                Activity.RESULT_CANCELED -> {
                    // The user canceled the operation.
                }
            }
        }
    }

    override fun onItemClick(url: String) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(url))
    }
}