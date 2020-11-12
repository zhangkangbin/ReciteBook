package com.zkb.recitebook.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.zkb.recitebook.R
import com.zkb.recitebook.base.BaseActivity
import com.zkb.recitebook.bean.SubjectBean
import com.zkb.recitebook.sql.MyDatabaseHelper
import java.lang.Exception

/**
  *
  * 作用描述
  * @author:zhangkb
  * Date:2020/10/23 16:24
 */
class InputSubjectActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_subject)
        setTitle("添加数据")
        initView()
    }

    private fun initView(){

        val subject = findViewById<EditText>(R.id.subjectTvSubject);
        val answer = findViewById<EditText>(R.id.subjectTvAnswer);
        val bookId = findViewById<EditText>(R.id.subjectTvBookId);

        val databaseHelper = MyDatabaseHelper(this)
        findViewById<Button>(R.id.subjectBtnSubmit).setOnClickListener {

            val  info=SubjectBean()
            info.subject=subject.text.toString()
            info.answer=answer.text.toString()
            info.bookId=bookId.text.toString()
            try {
                databaseHelper.insertData(info)
                showToast("insert success")
            }catch (e:Exception){
                showToast("失败")

            }

        }
    }


}