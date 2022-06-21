package com.joshowen.newsapp.utils

import android.view.View

fun View.hideView() {
    this.visibility = View.INVISIBLE
}

fun View.setVisible() {
    this.visibility = View.VISIBLE
}