package com.joshowen.newsapp.ext

import android.view.View
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

fun View.clicks() = callbackFlow<Unit> {
    setOnClickListener {
        trySend(Unit)
    }
    awaitClose { setOnClickListener(null)}
}