package com.zkb.recitebook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.zkb.recitebook.adapter.MyFragmentPagerAdapter
import com.zkb.recitebook.ui.fragment.IndexFragment
import com.zkb.recitebook.ui.fragment.SettingFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val listFragment=ArrayList<Fragment>();
        listFragment.add(IndexFragment())
        listFragment.add(SettingFragment())
        val mainViewPager=findViewById<ViewPager>(R.id.mainViewPager);
        mainViewPager.adapter= MyFragmentPagerAdapter(supportFragmentManager,listFragment)


        findViewById<RadioGroup>(R.id.indexRg).setOnCheckedChangeListener { group, checkedId ->

            if(checkedId==R.id.indexRbSubject){
                mainViewPager.currentItem=0
            }else{
                mainViewPager.currentItem=1
            }

        }
    }


}