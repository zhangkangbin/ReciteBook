package com.zkb.recitebook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zkb.recitebook.adapter.SubjectListAdapter
import com.zkb.recitebook.sql.MyDatabaseHelper
import com.zkb.recitebook.ui.InputSubjectActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.insertData).setOnClickListener {
            startActivity(Intent(this,InputSubjectActivity::class.java))
        }
        initRecycleView();
    }

    fun initRecycleView(){

        //随机模式？
        //收藏集
        val recycleView=findViewById<RecyclerView>(R.id.recycleView)
        recycleView.layoutManager=LinearLayoutManager(this)
        val databaseHelper = MyDatabaseHelper(this)
        val list=databaseHelper.getAllData()
        recycleView.adapter= SubjectListAdapter(this,list)

    }
}