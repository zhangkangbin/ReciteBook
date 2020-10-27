package com.zkb.recitebook.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.zkb.recitebook.R
import com.zkb.recitebook.bean.SubjectBean
import com.zkb.recitebook.sql.MyDatabaseHelper

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AnswerSubjectFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AnswerSubjectFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    private lateinit var mView: View;
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mView= inflater.inflate(R.layout.fragment_answer_subject, container, false)
        initView();
        return mView
    }

    private fun initView(){

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            //     param2 = it.getString(ARG_PARAM2)

            val info = MyDatabaseHelper.get(this.context).getById(param1)
            initData(info)
        }
    }

    private fun initData(info: SubjectBean?) {
        if(info==null) return
        val subject=mView.findViewById<TextView>(R.id.testTvSubject);
        subject.text=info.subject
        val answer=mView.findViewById<TextView>(R.id.testTvAnswer);

        var isShow=false
        mView.findViewById<TextView>(R.id.testTvAnswer).setOnClickListener {
            if(isShow){
                answer.text="You can click here to show the answer!"
                isShow=false
            }else{
                answer.text=info.answer
                isShow=true
            }

        }

        //update data
        val testTvRemember= mView.findViewById<TextView>(R.id.testTvRemember)
        testTvRemember.text="Remember:"+info.remember
        testTvRemember.setOnClickListener {
            val isSuccess=MyDatabaseHelper.get(this.context).updateRemember(info.id)
            if(isSuccess){
                testTvRemember.text="Remember:"+(++info.remember)
             //   showToast("update successfully!")
            }else{
                showToast("update fail!")
            }
        }

        //update data
        val testTvForget= mView.findViewById<TextView>(R.id.testTvForget)
        testTvForget.text="Forget:"+info.forget
        testTvForget.setOnClickListener {
            val isSuccess=MyDatabaseHelper.get(this.context).updateForget(info.id)
            if(isSuccess){
                testTvForget.text="Forget:"+(++info.forget)
                //   showToast("update successfully!")
            }else{
                showToast("update fail!")
            }
        }


    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String) =
            AnswerSubjectFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                  //  putString(ARG_PARAM2, param2)
                }
            }
    }

    fun showToast(msg:String){
        Toast.makeText(activity,msg, Toast.LENGTH_SHORT).show()
    }
}