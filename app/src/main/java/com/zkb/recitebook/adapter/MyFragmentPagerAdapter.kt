package com.zkb.recitebook.adapter

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager.widget.PagerAdapter


class MyFragmentPagerAdapter(
    fm: FragmentManager,
    list: List<Fragment>?
) :
    FragmentStatePagerAdapter(fm) {
    private var mlist: List<Fragment>?
    private val fm: FragmentManager
    override fun getItem(arg0: Int): Fragment {
        return mlist!![arg0] //显示第几个页面
    }

    override fun getCount(): Int {
        return mlist!!.size //有几个页面
    }

    override fun destroyItem(
        container: ViewGroup,
        position: Int,
        `object`: Any
    ) {
        super.destroyItem(container, position, `object`)
    }

    fun setFragments(fragments: List<Fragment>?) {
        if (mlist != null) {
            var ft: FragmentTransaction? = fm.beginTransaction()
            if(ft==null) return
            for (f in mlist!!) {
                ft.remove(f)
            }
            ft.commit()
            ft = null
            fm.executePendingTransactions()
        }
        mlist = fragments
        notifyDataSetChanged()
    }

    override fun getItemPosition(`object`: Any): Int {
        return PagerAdapter.POSITION_NONE
    }

    init {
        mlist = list
        this.fm = fm
    }
}
