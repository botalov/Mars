package com.sixfingers.botalov.mars.Adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.sixfingers.botalov.mars.Entities.Photo
import com.sixfingers.botalov.mars.R
import android.os.Bundle
import com.sixfingers.botalov.mars.GalleryWindow.Views.FullscreenPhotoFragment
import android.graphics.Bitmap
import android.graphics.drawable.TransitionDrawable
import android.support.v7.app.AppCompatActivity
import java.io.ByteArrayOutputStream
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable




class PhotosAdapter(private val photos: List<Photo>, private val context: Context) : RecyclerView.Adapter<PhotosAdapter.PhotoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        var view: View? = null
        try {
            view = LayoutInflater.from(context).inflate(R.layout.item_photo_view_holder, parent, false)
        }
        catch (e: Exception){
            e.printStackTrace()
        }
        return PhotoViewHolder(view!!)
    }

    override fun getItemCount(): Int {
        return photos.count()
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val photo: Photo = photos[position]
        holder.dateTextView.text = photo.earthDate.toString()
        holder.context = context
        Glide.with(context).load(photo.imgSrc)
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imageView)
    }

    class PhotoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {
        override fun onClick(p0: View?) {
            try {
                val stream = ByteArrayOutputStream()
                val bitmap = ((imageView.drawable as TransitionDrawable).getDrawable(1) as GlideBitmapDrawable).bitmap
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
                val byteArray = stream.toByteArray()

                val fullPhotoFragment = FullscreenPhotoFragment()
                val args = Bundle()
                args.putByteArray("BTMP_ARG", byteArray)
                fullPhotoFragment.arguments = args
                fullPhotoFragment.show((context as AppCompatActivity).supportFragmentManager, "fullPhotoFragment")
            }
            catch (e: Exception){
                e.printStackTrace()
            }
        }

        var context: Context? = null
        val imageView: ImageView = itemView.findViewById(R.id.photo_image_view)
        val dateTextView: TextView = itemView.findViewById(R.id.date_text_view)

        init {
            imageView.setOnClickListener(this)
        }
    }
}