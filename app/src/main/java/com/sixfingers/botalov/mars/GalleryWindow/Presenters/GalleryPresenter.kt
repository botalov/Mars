package com.sixfingers.botalov.mars.GalleryWindow.Presenters

import com.sixfingers.botalov.mars.App
import com.sixfingers.botalov.mars.Entities.MarsData
import com.sixfingers.botalov.mars.GalleryWindow.Views.IGalleryView
import com.sixfingers.botalov.mars.Network.INetwork
import com.sixfingers.botalov.mars.Network.NetworkClient
import com.sixfingers.botalov.mars.R
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.util.*


class GalleryPresenter: IGalleryPresenter{
    private val client = NetworkClient()
    private val retrofit = client.getRetrofit()

    private var view: IGalleryView? = null

    override fun attachView(view: IGalleryView){
        this.view = view
    }

    override fun onDestroy() {
        this.view = null
    }

    override fun getOpportunityPhotos() {
        view?.showProgress()
        val observable: Observable<MarsData>? = retrofit?.create(INetwork::class.java)
                ?.getOpportunityPhotos(App.getAppContext().getString(R.string.api_key), "2018-10-10")
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())

        observable?.subscribeWith(getObserver())
    }

    override fun getCuriosityPhotos(date: String) {
        view?.showProgress()
        val observable: Observable<MarsData>? = retrofit?.create(INetwork::class.java)
                ?.getCuriosityPhotos(App.getAppContext().getString(R.string.api_key), date)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())

        observable?.subscribeWith(getObserver())
    }

    private fun getObserver(): DisposableObserver<MarsData>{
        return object : DisposableObserver<MarsData>() {

            override fun onNext(@NonNull marsData: MarsData) {
                view?.showPhotos(marsData)
                //updateRealData(marsData)
                view?.hideProgress()
            }

            override fun onError(@NonNull e: Throwable) {
                /*e.printStackTrace()
                mvi.displayError("Error fetching Movie Data")*/
                view?.hideProgress()
                view?.showError(e.message)
            }

            override fun onComplete() {

            }
        }
    }
}