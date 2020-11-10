package com.fmt.kotlin.eyepetizer.common.ext

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

fun AppCompatActivity.setToolBar(toolbar: Toolbar){
    setSupportActionBar(toolbar)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    supportActionBar?.setHomeButtonEnabled(true)
    toolbar.setNavigationOnClickListener { onBackPressed() }
}
