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
  *最迷人的风景，是成长的足迹--小林
 * 时间划分：1-6 7-12
 * 1一个月一科？穿插法？目前适合自己才是最好的，6月后才启动穿插法吧。需要不断去调整自己的学习模型。
 * 数学 专业课（408，四科），政治，英语。--专业课和数学复习难度最大耗时最多，占分最高。三高
 * 如何合理利用时间复习？
 * 周末：一个月8天，每天 11个小时，共88小时，6个月只有48天。。共528小时
 * 晚上：2个小时？ 20*6=120小时（跟周末重合，所以是20天。）留点时间休息和喘气，给大脑腾出思考，吸收和休息时间。
 * 专业课学习时间主要放在周一到周五9~18.
 * 6个月一共648个小时。
 * 预想：前面的时间都分给英语和数学。等4月10号考完试，学习政治和专业课。
 * 数学前期预计花得时间很多，口语和听力要如何学习?这个是一个值得思考的问题？
 * * 作用描述
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