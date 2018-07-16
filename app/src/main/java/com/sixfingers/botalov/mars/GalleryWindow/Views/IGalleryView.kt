package com.sixfingers.botalov.mars.GalleryWindow.Views

import com.sixfingers.botalov.mars.Entities.MarsData

interface IGalleryView{
    fun showPhotos(data: MarsData)

    fun showError(error: String?)

    fun showProgress()

    fun hideProgress()
}