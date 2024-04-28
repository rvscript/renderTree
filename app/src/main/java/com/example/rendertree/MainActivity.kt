package com.example.rendertree

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    val parentWidth = 1000
    val parentHeight = 1000
    val TAG = "TREE*"
    var arrSum = 0
    var count = 0

    lateinit var parentLayout: RelativeLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parentLayout = createRelativeLayout(this)
        parentLayout.setBackgroundColor(Color.LTGRAY)
        parentLayout.gravity = Gravity.CENTER
        val arr = intArrayOf(10, 10, 9, 1,7,5,3,4)
        arrSum = arr.sum()
        Log.d(TAG, "init Array ${arr.contentToString()}, count = $count")

        // paint
        val paint = Paint()

        // canvas
        val bg = Bitmap.createBitmap(parentWidth, parentHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bg)

        // function for drawing other squares
        renderTree(arr, paint, canvas, 0F, 0F, 1000F, 1000F)

        // Image
        val imageView = ImageView(this)
        imageView.setImageBitmap(bg)

        // activityLayout
        parentLayout.addView(imageView)
        setContentView(parentLayout)
    }

    // recursive function for drawing rectangles
    private fun renderTree(
        array: IntArray,
        paint: Paint,
        canvas: Canvas,
        left: Float = 0F,
        top: Float = 0F,
        right: Float = 0F,
        bottom: Float = 0F
    ) {
        count++
        val perc = (array.sum().toDouble())/arrSum.toDouble()
        val mid = array.size / 2
        val subArray1 = array.sliceArray(0 until mid)
        val subArray2 = array.sliceArray(mid until array.size)

        val subArray1Sum = subArray1.sum()
        val subArray2Sum = subArray2.sum()

        val subArray1perc = (subArray1Sum.toDouble()/array.sum().toDouble()).toFloat()
        val subArray2perc = (subArray2Sum.toDouble()/array.sum().toDouble()).toFloat()
        val canvasWidth = right - left
        val canvasHeight = bottom - top

        Log.d(
            TAG,
            "renderTree: arr1 = ${subArray1.contentToString()}, arr2 = ${subArray2.contentToString()}, count = $count"
        )

        if (subArray1.size > 1) {
            renderTree(subArray1, paint, canvas, left, top, right, bottom)
        } else {
            if (subArray1.isNotEmpty()) {
                paint.color = Color.parseColor("#008899");
                canvas.drawRect(left, top, right*subArray1perc, bottom, paint)
            }
        }

        if (subArray2.size > 1) {
            renderTree(subArray2, paint, canvas, right*subArray1perc, top, right, bottom)
        } else {
            if (subArray2.isNotEmpty()) {
                paint.color = Color.parseColor("#CD5C5C");
                canvas.drawRect(right*subArray1perc, top, right, bottom, paint)
            }
        }
    }

    // create initial layout
    private fun createRelativeLayout (
        context: Context,
        width: Int = ViewGroup.LayoutParams.MATCH_PARENT,
        height: Int = ViewGroup.LayoutParams.MATCH_PARENT
    ): RelativeLayout {
        val relativeLayout = RelativeLayout(context)
        relativeLayout.layoutParams = ViewGroup.LayoutParams(
            width,
            height
        )
        return relativeLayout
    }
}