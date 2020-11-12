package com.zkb.recitebook.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.zkb.recitebook.R
import com.zkb.recitebook.base.BaseActivity
import com.zkb.recitebook.ui.fragment.AnswerSubjectFragment


class SubjectDetailActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subject_detail)

        setTitle("问题详情")
        val id = intent.getStringExtra("ID")

       // fragmentManager
        val fm: FragmentManager = supportFragmentManager
        fm.beginTransaction()
            .add(R.id.detailFragment, AnswerSubjectFragment.newInstance(id))
            .commit()
        //  fragment.
        //     AnswerSubjectFragment.newInstance(id)

    }
}