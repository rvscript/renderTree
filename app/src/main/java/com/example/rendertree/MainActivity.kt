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
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    val parentWidth = 1000
    val parentHeight = 1000
    lateinit var parentLayout: RelativeLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parentLayout = createRelativeLayout(this)
        parentLayout.setBackgroundColor(Color.LTGRAY)
        parentLayout.gravity = Gravity.CENTER
        val arr = intArrayOf(2, 3, 5, 7, 9, 10, 11)
        // paint
        val paint = Paint()
        // canvas
        val bg = Bitmap.createBitmap(parentWidth, parentHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bg)
        // init background for rectangles
        paint.color = Color.parseColor("#000000");
        canvas.drawRect(0F, 0F, 1000F, 1000F, paint)

        // function for drawing other squares
        renderTree(arr, paint, canvas)

        // Image
        val imageView = ImageView(this)
        imageView.setImageBitmap(bg)

        // activityLayout
        parentLayout.addView(imageView)
        setContentView(parentLayout)

    }

    // recursive function for drawing rectangles
    private fun renderTree(array: IntArray, paint: Paint, canvas: Canvas) {
        val mid = array.size / 2
        val subArray1 = array.sliceArray(0 until mid)
        val subArray2 = array.sliceArray(mid until array.size)

        Log.d(
            "TREE*",
            "renderTree: arr1 = ${subArray1.contentToString()}, arr2 = ${subArray2.contentToString()}"
        )
        if (subArray1.size > 2) {
            renderTree(subArray1, paint, canvas)
        } else {
            if (subArray1.size == 1) {
                paint.color = Color.parseColor("#008899");
                canvas.drawRect(210F, 80F, 360F, 200F, paint)

            } else {
                paint.color = Color.parseColor("#CD5C5C")
                canvas.drawRect(0F, 0F, 50F, 50F, paint)

            }
        }

        if (subArray2.size > 2) {
            renderTree(subArray2, paint, canvas)
        } else {
            if (subArray2.size == 1) {
                paint.color = Color.parseColor("#008899");
                canvas.drawRect(210F, 80F, 360F, 200F, paint)

            } else {
                paint.color = Color.parseColor("#CD5C5C")
                canvas.drawRect(0F, 0F, 50F, 50F, paint)
            }
        }
    }

    // create initial layout
    fun createRelativeLayout(
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