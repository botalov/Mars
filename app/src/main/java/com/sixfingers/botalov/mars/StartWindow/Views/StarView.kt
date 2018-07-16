package com.sixfingers.botalov.mars.StartWindow.Views

import android.animation.Animator
import android.content.Context
import android.support.constraint.ConstraintLayout
import android.view.View
import android.view.ViewAnimationUtils
import android.widget.AbsListView
import com.sixfingers.botalov.mars.R
import java.util.*

class StarView(context: Context?, parentView: ConstraintLayout) : View(context) {

    private val random = Random()
    private val parentView = parentView
    private var xPosition: Int = random.nextInt(parentView.width)
    private var yPosition: Int = random.nextInt(parentView.height)
    private val radius = random.nextInt(10) + 5
    private val startDelay = random.nextInt(3000)
    private val delay = random.nextInt(1000)

    /**
     * Показывает звезду на родительской вьюхе
     */
    fun show() {


        background = context!!.getDrawable(R.drawable.star_shape)
        layoutParams = AbsListView.LayoutParams(radius, radius)
        x = xPosition.toFloat()
        y = yPosition.toFloat()
        visibility = View.GONE

        parentView.addView(this)

        val anim = ViewAnimationUtils.createCircularReveal(this, xPosition, yPosition, 0f, radius.toFloat())
        anim.startDelay = startDelay.toLong()
        anim.duration = delay.toLong()
        anim.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animator: Animator) {
                visibility = View.VISIBLE
            }

            override fun onAnimationEnd(animator: Animator) {

            }

            override fun onAnimationCancel(animator: Animator) {

            }

            override fun onAnimationRepeat(animator: Animator) {

            }
        })
        anim.start()
    }

    fun updateCoordinates() {
        yPosition = random.nextInt(parentView.height)
        yPosition = random.nextInt(parentView.height)

    }
    fun inTheRightPlace(views: ArrayList<View>): Boolean{
        for(view in views) {
            if (Math.sqrt(Math.pow((xPosition - parentView.width / 2).toDouble(), 2.0) + Math.pow((yPosition - parentView.height / 2).toDouble(), 2.0)) <= view.width / 2 + 20) {
                return false
            }
        }

        return true
    }

}