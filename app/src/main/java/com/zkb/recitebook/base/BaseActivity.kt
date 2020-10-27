package com.zkb.recitebook.base

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class BaseActivity : AppCompatActivity()  {

    fun showToast(msg:String){
        Toast.makeText(this,msg, Toast.LENGTH_SHORT).show()
    }
}