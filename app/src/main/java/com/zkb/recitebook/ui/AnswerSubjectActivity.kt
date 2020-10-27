package com.zkb.recitebook.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.zkb.recitebook.R
import com.zkb.recitebook.adapter.MyFragmentPagerAdapter
import com.zkb.recitebook.sql.MyDatabaseHelper
import com.zkb.recitebook.ui.fragment.AnswerSubjectFragment

class AnswerSubjectActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer_subject)
        initRecyclerView();

    }
    private fun initRecyclerView(){
        val list=MyDatabaseHelper.get(applicationContext).getAllData();

        if(list.isNullOrEmpty()) return

        val listFragment=ArrayList<Fragment>()

        for(info in list){

            listFragment.add(AnswerSubjectFragment.newInstance(info.id.toString()))
        }



        val viewPager=findViewById<ViewPager>(R.id.viewPager)
        viewPager.adapter= MyFragmentPagerAdapter(this.supportFragmentManager,listFragment)
    }
}