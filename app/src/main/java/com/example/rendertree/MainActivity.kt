package com.example.rendertree

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    val parentWidth = 1000
    val parentHeight = 1000
    lateinit var parentLayout: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parentLayout = createLinearLayout(this, LinearLayout.VERTICAL, parentWidth, parentHeight)
        parentLayout.setBackgroundColor(Color.GRAY)
        parentLayout.gravity = Gravity.CENTER
        val arr = intArrayOf(2,2,1,3)
        parentLayout = renderLayout(arr, parentLayout)
        setContentView(renderLayout(arr, parentLayout))
    }

    private fun renderLayout(arr: IntArray, layout: LinearLayout): LinearLayout {
        return if (arr.isEmpty()) {
            layout
        } else if (arr.size == 1) {
            layout.addView(createChildView(parentWidth, parentHeight, Color.BLUE))
            layout
        } else {
            divideAndConquer(layout, arr)
        }
    }

    private fun divideAndConquer(parent: LinearLayout, arr: IntArray): LinearLayout {
        val mid = arr.size/2
        val arrLeft = arr.sliceArray(0 until mid)
        val arrRight = arr.sliceArray(mid until arr.size)
        addUpLayout(arrLeft, parent, Color.RED)
        addUpLayout(arrRight, parent, Color.BLUE)
        return parent
    }

    private fun addUpLayout(arr: IntArray, parent: LinearLayout, color: Int) {
        if (arr.size == 1) {
            val childWidth = (parentWidth/100)*(arr[0])
            val childHeight = parentHeight
            parent.addView(createChildView(childWidth,childHeight, color))
        } else if (arr.size == 2) {
            val childWidth = (parentWidth/100)*(arr[0]+ arr[1])
            val childHeight = (parentWidth/100)*(arr[0]+arr[1])
            parent.addView(createChildView(childWidth,childHeight, color))
        }
    }

    private fun createChildView(child1: Int, child2: Int, color: Int): TextView {
        val textView  = createTextViewLayout(this, child1,child2)
        textView.setBackgroundColor(color)
        return textView
    }

    // create initial layout
    private fun createLinearLayout(
        context: Context,
        orientation: Int = LinearLayout.VERTICAL,
        width: Int = ViewGroup.LayoutParams.MATCH_PARENT,
        height: Int = ViewGroup.LayoutParams.MATCH_PARENT
    ): LinearLayout {
        val linearLayout = LinearLayout(context)
        linearLayout.orientation = orientation
        linearLayout.layoutParams = ViewGroup.LayoutParams(
            width,
            height
        )
        return linearLayout
    }

    fun createTextViewLayout(
        context: Context,
        width: Int = LinearLayout.LayoutParams.WRAP_CONTENT,
        height: Int = LinearLayout.LayoutParams.WRAP_CONTENT
    ): TextView {
        val textView = TextView(context)
        textView.layoutParams = LinearLayout.LayoutParams(
            width,
            height
        )
        textView.textSize = 22F
        return textView
    }

}