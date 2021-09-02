package com.geek.infoandroid.Kotlin.materialDesign.les5

import android.app.Fragment
import android.os.Build
import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.view.View
import android.view.animation.AnticipateOvershootInterpolator
import androidx.annotation.RequiresApi
import com.geek.infoandroid.R
import com.geek.infoandroid.databinding.ConstraintsSetFragmentBinding

class ConstraintsSetFragment: Fragment(R.layout.constraints_set_fragment) {
    private var show = false
    var viewBinding: ConstraintsSetFragmentBinding? = null


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = ConstraintsSetFragmentBinding.bind(view)
        val safeBinding = viewBinding ?: return
        safeBinding.backgroundImage.setOnClickListener{
            if(show)hideComponents() else showComponents()
        }
    }

    private fun showComponents() {
        val safeBinding = viewBinding ?: return
        show = true
        val constaintSet= ConstraintSet()//класс для создания констреинтов для нашего лейаута
        constaintSet.clone(requireContext(),R.layout.main_fragment_end)//копируем все параметры констреинты из main_fragment_end
        val changeBounds = ChangeBounds()//анимация которая анимирует положения и размеры вьюх
        changeBounds.interpolator = AnticipateOvershootInterpolator()//анимация отпружинивания
        changeBounds.duration = 1200//длительность анимации
        TransitionManager.beginDelayedTransition(safeBinding.main,changeBounds)//устанавливаем лэйаут ,в котором будет применяться эта анимация
        constaintSet.applyTo(safeBinding.main)//применяем все констреинты к нашему констрейт лейауте
        //id констрейнт лейауту нужно давать только в одном макете,который используется, с которого копируем главное дать айди всем вьюхам

    }

    private fun hideComponents() {
        val safeBinding = viewBinding ?: return
        show = false
        val constaintSet= ConstraintSet()
        constaintSet.clone(requireActivity(),R.layout.main_fragment)
        val changeBounds = ChangeBounds()
        changeBounds.interpolator = AnticipateOvershootInterpolator()
        changeBounds.duration = 1200
        TransitionManager.beginDelayedTransition(safeBinding.main,changeBounds)
        constaintSet.applyTo(safeBinding.main)
    }
}