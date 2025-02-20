package com.example.vktech.util

import android.content.Context
import android.widget.Toast
import com.example.vktech.App
import com.example.vktech.di.AppComponent

fun Context.showToast(msg:String){
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun Context.getAppComponent(): AppComponent = (this.applicationContext as App).appComponent
