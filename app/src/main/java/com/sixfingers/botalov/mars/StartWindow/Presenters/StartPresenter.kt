package com.sixfingers.botalov.mars.StartWindow.Presenters

import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import com.sixfingers.botalov.mars.GalleryWindow.Views.GalleryActivity
import com.sixfingers.botalov.mars.StartWindow.Views.StartActivity

class StartPresenter : IStartPresenter {

    private var view: StartActivity? = null

    override fun attachView(view: StartActivity){
        this.view = view
    }

    override  fun onLoad(){
        view?.startAnimation()
    }

    override fun onDestroy(){
        this.view = null
    }

    override fun onPressMars() {
        val intent = Intent(view, GalleryActivity::class.java)
        startActivity(view!!, intent, null)
    }

}