package com.sixfingers.botalov.mars.GalleryWindow.Views

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.format.DateUtils
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.sixfingers.botalov.mars.Adapters.PhotosAdapter
import com.sixfingers.botalov.mars.Entities.MarsData
import com.sixfingers.botalov.mars.Entities.Photo
import com.sixfingers.botalov.mars.GalleryWindow.Presenters.GalleryPresenter
import com.sixfingers.botalov.mars.GalleryWindow.Presenters.IGalleryPresenter
import com.sixfingers.botalov.mars.R
import java.text.SimpleDateFormat
import java.time.Duration
import java.time.Instant
import java.time.LocalDate
import java.util.*

class GalleryActivity : AppCompatActivity(), IGalleryView {
    private var progressBar: ProgressBar? = null
    private val presenter: IGalleryPresenter = GalleryPresenter()
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)
        progressBar = findViewById(R.id.progress_bar)
        initViews()
        presenter.attachView(this)
        getPhotos(date)
    }

    var date = Date()
    override fun showPhotos(data: MarsData) {
        if(data.photos != null) {
            if(data.photos!!.size > 20) {
                adapter.addData((data.photos as ArrayList<Photo>?)!!)
                adapter.notifyDataSetChanged()
            }
            else{
                requestPhoto()
            }
        }
    }

    override fun showError(error: String?) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
        finish()
    }

    override fun showProgress() {
        progressBar?.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar?.visibility = View.GONE
    }

    @SuppressLint("SimpleDateFormat")
    private fun getPhotos(date: Date){
        val df = SimpleDateFormat("yyyy-MM-dd")
        val strDate = df.format(date)
        presenter.getCuriosityPhotos(strDate)
    }

    private val dataList = ArrayList<Photo>()
    private var adapter = PhotosAdapter(dataList, this)
    private fun initViews(){
        recyclerView = findViewById(R.id.photos_recycler_view)
        recyclerView.layoutManager = GridLayoutManager(this, 3)
        recyclerView.adapter = adapter

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val lastVisibleItemPosition = (recyclerView.layoutManager!! as GridLayoutManager).findLastVisibleItemPosition()
                val totalItemCount = recyclerView.layoutManager!!.itemCount
                if (totalItemCount == lastVisibleItemPosition + 1) {
                    requestPhoto()
                }
            }
        })

    }

    private fun requestPhoto(){
        val cal = GregorianCalendar()
        cal.time = date
        cal.add(Calendar.DATE, -1)
        date = cal.time

        getPhotos(date)
    }
}
