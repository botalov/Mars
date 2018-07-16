package com.sixfingers.botalov.mars.StartWindow.Presenters

import com.sixfingers.botalov.mars.StartWindow.Views.StartActivity

interface IStartPresenter {
    fun attachView(view: StartActivity)

    fun onLoad()

    fun onDestroy()

    fun onPressMars()
}