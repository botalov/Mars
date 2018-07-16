package com.sixfingers.botalov.mars.GalleryWindow.Presenters

import com.sixfingers.botalov.mars.GalleryWindow.Views.IGalleryView

interface IGalleryPresenter {

    fun attachView(view: IGalleryView)

    fun getCuriosityPhotos()

    fun getOpportunityPhotos()

    fun onDestroy()

}