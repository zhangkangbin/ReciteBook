package com.zkb.recitebook.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.zkb.recitebook.R
import com.zkb.recitebook.adapter.SubjectListAdapter
import com.zkb.recitebook.sql.MyDatabaseHelper

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [IndexFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class IndexFragment : Fragment() {

    private fun initRecycleView(view: View){

        //随机模式？
        //收藏集
        val recycleView=view.findViewById<RecyclerView>(R.id.recycleView)
        recycleView.layoutManager= LinearLayoutManager(activity)
        val databaseHelper = MyDatabaseHelper(activity)
        val list=databaseHelper.getAllData()
        recycleView.adapter= activity?.applicationContext?.let { SubjectListAdapter(it,list) }


        val indexRefreshLayout=view.findViewById<SmartRefreshLayout>(R.id.indexRefreshLayout)
        indexRefreshLayout.setRefreshHeader(ClassicsHeader(activity))
        indexRefreshLayout.setOnRefreshListener {
            list.clear()
            list.addAll(databaseHelper.allData)
            recycleView.adapter?.notifyDataSetChanged()
            indexRefreshLayout.finishRefresh()
        }

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       val view=inflater.inflate(R.layout.fragment_index, container, false);
        initRecycleView(view)
        return view
    }


}


