package com.geek.infoandroid.Kotlin.materialDesign.les3.tabs

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.geek.infoandroid.android.Fragments5.Fragment1
import com.geek.infoandroid.android.Fragments5.Fragment2

class ViewPagerAdapter2(private val fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    private val fragments = arrayOf(Fragment1(), Fragment2(), Fragment3())
    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}
//XML
/*
<androidx.viewpager2.widget.ViewPager2
    android:id="@+id/view_pager"
    android:layout_width="match_parent"
    android:layout_height="match_parent">
        <TableLayout
            android:id="@+id/tab_layout"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"/>
</androidx.viewpager2.widget.ViewPager2>
*/
