package com.sixfingers.botalov.mars.GalleryWindow.Views

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.sixfingers.botalov.mars.Adapters.PhotosAdapter
import com.sixfingers.botalov.mars.Entities.MarsData
import com.sixfingers.botalov.mars.GalleryWindow.Presenters.GalleryPresenter
import com.sixfingers.botalov.mars.GalleryWindow.Presenters.IGalleryPresenter
import com.sixfingers.botalov.mars.R

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
        getPhotos()
    }

    override fun showPhotos(data: MarsData) {
        if(data.photos != null) {
            val adapter = PhotosAdapter(data.photos!!, this)
            recyclerView.adapter = adapter
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

    private fun getPhotos(){
        presenter.getCuriosityPhotos()
    }

    private fun initViews(){
        recyclerView = findViewById(R.id.photos_recycler_view)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
    }
}
