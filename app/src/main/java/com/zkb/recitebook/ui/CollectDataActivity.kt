package com.zkb.recitebook.ui

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.zkb.recitebook.R
import com.zkb.recitebook.base.BaseActivity
import com.zkb.recitebook.bean.SubjectBean
import com.zkb.recitebook.sql.MyDatabaseHelper

class CollectDataActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collect_data)
        title = "我的收藏"
        initAdapter();
    }


    private lateinit var list: MutableList<SubjectBean>
    private fun initAdapter() {

        val recycleView = findViewById<RecyclerView>(R.id.recycleView)
        recycleView.layoutManager = LinearLayoutManager(this)

        recycleView.adapter = getAdapter()


        val indexRefreshLayout = findViewById<SmartRefreshLayout>(R.id.smartRefreshLayout)
        indexRefreshLayout.setRefreshHeader(ClassicsHeader(this))
        indexRefreshLayout.setOnRefreshListener {
            list.clear()
            getData().let { it1 -> list.addAll(it1) }
            recycleView.adapter?.notifyDataSetChanged()
            indexRefreshLayout.finishRefresh()
        }
    }

    private fun getAdapter(): RecyclerView.Adapter<*>? {

        list=getData();
        val adapter = object :
            BaseQuickAdapter<SubjectBean, BaseViewHolder>(R.layout.subject_item_list, list) {
            override fun convert(holder: BaseViewHolder, item: SubjectBean) {

                val collectView = holder.getView<View>(R.id.collectView);
                if (item.collect == 0) {
                    collectView.visibility = View.GONE
                } else {
                    collectView.visibility = View.VISIBLE
                }

                holder.setText(R.id.subject, item.subject)
                holder.setText(R.id.answer, item.answer)
                holder.setText(R.id.forgetCount, "forget:" + item.forget)
                holder.setText(R.id.rememberCount, "remember:" + item.remember)

                holder.getView<View>(R.id.cardViewSubject).setOnClickListener {
                    val intent = Intent(context, SubjectDetailActivity::class.java)
                    intent.putExtra("ID", item.id.toString())
                    context.startActivity(intent)
                }
            }

        }

        return adapter

    }


    private fun getData(): MutableList<SubjectBean> {
        return MyDatabaseHelper.get(this).collect;

    }


}