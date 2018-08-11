package com.sixfingers.botalov.mars.Adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentStatePagerAdapter
import com.sixfingers.botalov.mars.GalleryWindow.Views.PhotoFullscreenFragment

class ImagePagerAdapter(fragment: Fragment, val photos: List<Array<Int>>): FragmentStatePagerAdapter(fragment.childFragmentManager) {


    override fun getItem(p0: Int): Fragment {
        return PhotoFullscreenFragment.fragment.newInstance()
    }

    override fun getCount(): Int {
        return photos.size
    }
}