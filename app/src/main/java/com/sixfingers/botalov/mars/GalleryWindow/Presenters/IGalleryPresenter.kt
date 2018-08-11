package com.sixfingers.botalov.mars.GalleryWindow.Presenters

import com.sixfingers.botalov.mars.GalleryWindow.Views.IGalleryView
import java.util.*

interface IGalleryPresenter {

    fun attachView(view: IGalleryView)

    fun getCuriosityPhotos(date: String)

    fun getOpportunityPhotos()

    fun onDestroy()

}