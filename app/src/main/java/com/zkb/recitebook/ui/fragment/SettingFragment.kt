package com.zkb.recitebook.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import com.zkb.recitebook.R
import com.zkb.recitebook.ui.CollectDataActivity
import com.zkb.recitebook.ui.InputSubjectActivity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SettingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SettingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_setting, container, false)
        initView(view)
        return view
    }

    private fun  initView(view:View){
        view.findViewById<View>(R.id.settingTvAddData).setOnClickListener {
         startActivity(Intent(activity, InputSubjectActivity::class.java))
     }
        view.findViewById<View>(R.id.settingTvMyCollect).setOnClickListener {
            startActivity(Intent(activity, CollectDataActivity::class.java))
        }



    }

}