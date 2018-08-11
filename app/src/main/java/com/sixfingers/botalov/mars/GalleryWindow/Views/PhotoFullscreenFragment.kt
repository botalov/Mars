package com.sixfingers.botalov.mars.GalleryWindow.Views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.sixfingers.botalov.mars.R

class PhotoFullscreenFragment: Fragment() {

    object fragment {
        const val BYTES_ARG = "BYTES_ARG"
        fun newInstance(): PhotoFullscreenFragment {
            val newFragment = PhotoFullscreenFragment()

            return newFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.image_fullscreen_preview, container, false)

        if(arguments != null){
            val bytes: ByteArray? = arguments?.getByteArray(fragment.BYTES_ARG)
            val imageView = view.findViewById<ImageView>(R.id.image_preview)
            //imageView.transitionName = ""
        }


        return view
    }

}