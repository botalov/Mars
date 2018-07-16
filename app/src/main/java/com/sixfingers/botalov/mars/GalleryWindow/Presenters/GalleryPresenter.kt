package com.sixfingers.botalov.mars.GalleryWindow.Presenters

import android.util.Log
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
                ?.getOpportunityPhotos(App.getAppContext().getString(R.string.api_key), 0)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())

        observable?.subscribeWith(getObserver())
    }

    override fun getCuriosityPhotos() {
        view?.showProgress()
       // val realmData: MarsData? = getDataFromRealm()
      //  if(realmData == null){
            val observable: Observable<MarsData>? = retrofit?.create(INetwork::class.java)
                    ?.getCuriosityPhotos(App.getAppContext().getString(R.string.api_key), 0)
                    ?.subscribeOn(Schedulers.io())
                    ?.observeOn(AndroidSchedulers.mainThread())

            observable?.subscribeWith(getObserver())
     //   }
      //  else{
          //  view?.showPhotos(realmData)
      //  }

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

    /*private fun getDataFromRealm(): MarsData?{
        val realm: Realm = Realm.getDefaultInstance()
        if(realm.isInTransaction){
            realm.commitTransaction()
        }
        try{
            realm.beginTransaction()
            val data: MarsRealmData? = realm.where(MarsRealmData::class.java).findFirst()
            return data?.data
        }
        finally {
            realm.commitTransaction()
        }
    }
    private fun updateRealData(data: MarsData){
        val realm: Realm = Realm.getDefaultInstance()
        if(realm.isInTransaction){
            realm.commitTransaction()
        }
        try{
            realm.beginTransaction()
            val realmData: MarsRealmData? = realm.where(MarsRealmData::class.java).findFirst()
            if(realmData != null){
                realm.delete(MarsRealmData::class.java)
            }
            val nData: MarsRealmData = realm.createObject(MarsRealmData::class.java)
            nData.data = data
            nData.updateDate = Date()
            realm.insert(nData)
        }
        finally {
            realm.commitTransaction()
        }
    }*/

}