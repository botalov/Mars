package com.sixfingers.botalov.mars.GalleryWindow.Views

import android.graphics.Bitmap
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sixfingers.botalov.mars.R
import android.widget.ImageView
import com.sixfingers.botalov.mars.Entities.Photo
import android.graphics.BitmapFactory




class FullscreenPhotoFragment: DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar_Fullscreen)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_image_slider, container, false)

        //return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = arguments
        if(args != null) {
            val byteArray = args.getByteArray("BTMP_ARG")
            val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
            val imageView: ImageView = view.findViewById(R.id.image_preview)
            imageView.setImageBitmap(bitmap)
        }

    }

}