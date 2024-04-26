package com.example.rendertree

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
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
        val arr = intArrayOf(2, 3, 5, 7, 9, 10, 11)
        renderTree(arr, parentLayout)
        parentLayout = renderTree(arr, parentLayout)
        setContentView(renderTree(arr, parentLayout))
    }

    private fun renderTree(array: IntArray, layout: LinearLayout): LinearLayout {
        val mid = array.size / 2
        val subArray1 = array.sliceArray(0 until mid)
        val subArray2 = array.sliceArray(mid until array.size)

        Log.d("TREE*", "renderTree: arr1 = ${subArray1.contentToString()}, arr2 = ${subArray2.contentToString()}")
        if (subArray1.size > 2) {
            renderTree(subArray1, layout)
        } else {
            if (subArray1.size == 1) {
                val textView = createChildView(subArray1[0], subArray1[0], Color.BLUE)
                layout.addView(textView)
            } else {
                val textView = createChildView(subArray1[0], subArray1[0], Color.BLUE)
                layout.addView(textView)
            }
        }

        if (subArray2.size > 2) {
            renderTree(subArray2, layout)
        } else {
            if (subArray2.size == 1) {
                val textView = createChildView(subArray2[0], subArray2[0], Color.BLUE)
                layout.addView(textView)
            } else {
                val textView = createChildView(subArray2[0], subArray2[0], Color.BLUE)
                layout.addView(textView)
            }
        }
        return layout
    }

    private fun divideAndConquer(parent: LinearLayout, arr: IntArray): LinearLayout {
        val mid = arr.size / 2
        val arrLeft = arr.sliceArray(0 until mid)
        val arrRight = arr.sliceArray(mid until arr.size)
        addUpLayout(arrLeft, parent, Color.RED)
        addUpLayout(arrRight, parent, Color.BLUE)
        return parent
    }

    private fun addUpLayout(arr: IntArray, parent: LinearLayout, color: Int) {
        if (arr.size == 1) {
            val childWidth = (parentWidth / 100) * (arr[0])
            val childHeight = parentHeight
            parent.addView(createChildView(childWidth, childHeight, color))
        } else if (arr.size == 2) {
            val childWidth = (parentWidth / 100) * (arr[0] + arr[1])
            val childHeight = (parentWidth / 100) * (arr[0] + arr[1])
            parent.addView(createChildView(childWidth, childHeight, color))
        }
    }

    private fun createChildView(child1: Int, child2: Int, color: Int): TextView {
        val textView = createTextViewLayout(this, child1, child2)
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
        return textView
    }

}