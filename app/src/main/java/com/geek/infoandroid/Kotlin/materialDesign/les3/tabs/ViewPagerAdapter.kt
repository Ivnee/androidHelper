package com.geek.infoandroid.Kotlin.materialDesign.les3.tabs

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.myweather.ui.browser.BrowserFragment
import com.geek.infoandroid.android.Fragments5.Fragment1
import com.geek.infoandroid.android.Fragments5.Fragment2
//типы адаптеров
//FragmentStatePagerAdapter - создает фрагменты по мере их открытия и уничтожает предыдущие(идеально для книг)
//FragmentPagerAdapter - хранит фрагменты в памяти(идеально когда мало фрагментов)

class ViewPagerAdapter(private val fragmentManager: FragmentManager) ://обязательно передаем фрагмент менеджер
    FragmentStatePagerAdapter(//пейджер наших фрагментов
        fragmentManager,//и вставляем в пейджер
        BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
    ) {
    val fragments = listOf(Fragment1(), Fragment2(),BrowserFragment())//список фрагментов
    override fun getCount(): Int {
        return fragments.size
    }

    override fun getItem(position: Int): Fragment {//указываем какие фрагменты на какой позиции возвращаем
        return when(position){
            0->{fragments[0]}
            1->{fragments[1]}
            2->{fragments[2]}
            else->{fragments[1]}
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragments[position]::class.simpleName//возвращает название фрагмента для текста в табах
    }

}