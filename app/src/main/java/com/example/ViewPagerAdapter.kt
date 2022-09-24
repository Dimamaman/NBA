package com.example

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.fragments.GamesFragment
import com.example.fragments.playersFragment.PlayersFragment
import com.example.fragments.TeamsFragment

class ViewPagerAdapter(fragmentManager: FragmentManager,lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager,lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> PlayersFragment()
            1 -> TeamsFragment()
            2 -> GamesFragment()
            else -> Fragment()
        }
    }

}