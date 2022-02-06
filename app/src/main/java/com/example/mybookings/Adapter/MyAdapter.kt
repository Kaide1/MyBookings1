package com.example.mybookings.Adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.mybookings.Fragments.Home
import com.example.mybookings.Fragments.Settings
import com.example.mybookings.Fragments.Homepage


internal class MyAdapter(var context : Context, fm: FragmentManager, var totalTabs: Int): FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
            return when(position)
            {
                0->{
                    Home()
                }

                1->{
                    Settings()
                }
                else ->
                    getItem(position)
            }
    }

    override fun getCount(): Int {
            return totalTabs
    }



}