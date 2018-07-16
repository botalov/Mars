package com.sixfingers.botalov.mars.StartWindow.Views

import android.animation.Animator
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.constraint.ConstraintLayout
import android.view.View
import android.view.ViewAnimationUtils
import com.sixfingers.botalov.mars.R
import com.sixfingers.botalov.mars.StartWindow.Presenters.StartPresenter
import java.util.*

class StartActivity : AppCompatActivity() {

    private lateinit var mMarsButton: View
    private lateinit var parentView: View

    private var presenter: StartPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        presenter = StartPresenter()
        presenter?.attachView(this)

        initViews()
        presenter?.onLoad()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.onDestroy()
        presenter = null
    }

    private fun initViews() {
        mMarsButton = findViewById(R.id.marsView)
        parentView = findViewById(R.id.backgroundView)
    }

    fun startAnimation() {
        Handler().postDelayed({ showBack() }, 1000)
    }

    private fun showBack() {
        val x = parentView.width / 2
        val y = parentView.height / 2
        val endRadius = 0
        val startRadius = Math.hypot((parentView.width / 2).toDouble(), (parentView.height / 2).toDouble()).toInt()

        val anim = ViewAnimationUtils.createCircularReveal(parentView, x, y, startRadius.toFloat(), endRadius.toFloat())
        anim.duration = 3000
        anim.startDelay = 1000
        anim.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animator: Animator) {
                //startButton.setVisibility(View.GONE);
            }

            override fun onAnimationEnd(animator: Animator) {
                parentView.visibility = View.GONE
                showStars()

                mMarsButton.setOnClickListener { presenter?.onPressMars() }
            }

            override fun onAnimationCancel(animator: Animator) {

            }

            override fun onAnimationRepeat(animator: Animator) {

            }
        })
        parentView.visibility = View.VISIBLE

        anim.start()
    }

    private fun showStars() {
        val random = Random()
        val countStars = random.nextInt(40) + 40
        val parentView: ConstraintLayout = findViewById(R.id.mainLayout)
        for (i in 0..countStars) {
            val star = StarView(this, parentView)
            var bFlag = false
            while (!bFlag) {
                bFlag = star.inTheRightPlace(arrayListOf(mMarsButton))
                if(!bFlag){
                    star.updateCoordinates()
                }
            }
            star.show()
        }
    }
}
