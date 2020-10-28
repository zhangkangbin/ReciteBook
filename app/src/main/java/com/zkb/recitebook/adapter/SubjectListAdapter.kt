package com.zkb.recitebook.adapter

import android.content.Context
import android.content.Intent
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.zkb.recitebook.R
import com.zkb.recitebook.bean.SubjectBean
import com.zkb.recitebook.ui.AnswerSubjectActivity

class SubjectListAdapter(context:Context,data: MutableList<SubjectBean>?) :
    BaseQuickAdapter<SubjectBean, BaseViewHolder>(R.layout.subject_item_list, data) {
    override fun convert(holder: BaseViewHolder, item: SubjectBean) {
       val collectView=holder.getView<View>(R.id.collectView);
        if(item.collect==0){
            collectView.visibility=View.GONE
        }else{
            collectView.visibility=View.VISIBLE
        }

        holder.setText(R.id.subject,item.subject)
        holder.setText(R.id.answer,item.answer)
        holder.setText(R.id.forgetCount,"forget:"+item.forget)
        holder.setText(R.id.rememberCount,"remember:"+item.remember)

        holder.getView<View>(R.id.cardViewSubject).setOnClickListener {

            context.startActivity(Intent(context,AnswerSubjectActivity::class.java))
        }

    }
}