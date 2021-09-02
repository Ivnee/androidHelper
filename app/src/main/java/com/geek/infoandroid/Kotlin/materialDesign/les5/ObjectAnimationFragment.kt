package com.geek.infoandroid.Kotlin.materialDesign.les5

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.transition.*
import android.view.View
import android.widget.Toast
import com.geek.infoandroid.R
import com.geek.infoandroid.databinding.ObjectAnimatorFragmentBinding

class ObjectAnimationFragment : Fragment(R.layout.object_animator_fragment) {

    private var isExpanded = false
    var viewBinding: ObjectAnimatorFragmentBinding? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = ObjectAnimatorFragmentBinding.bind(view)
        val safeBinding = viewBinding ?: return
        setFab()
    }

    private fun setFab() {
        setInitialState()
        viewBinding?.fab?.setOnClickListener {
            if (isExpanded) {
                collapseFab()
            } else {
                expandFab()
            }
        }
    }

    private fun setInitialState() {
        viewBinding?.transparentLayout?.apply {
            alpha = 0f//убрать прозрачность на 0(полностью невидно)
        }
        viewBinding?.optionTwoContainer?.apply {
            alpha = 0f
            isClickable = false
        }
        viewBinding?.optionOneContainer?.apply {
            alpha = 0f
            isClickable = false
        }

    }

    private fun collapseFab() {
        val safeBinding = viewBinding ?: return
        isExpanded = false
        ObjectAnimator.ofFloat(viewBinding?.plusImageview, "rotation", 0f, -180f)
            .start()//повернуть вьюху на 180 градусов против часовой стрелки?
        ObjectAnimator.ofFloat(viewBinding?.optionTwoContainer, "translationY", 0f)
            .start()//сдвинуть вертиально
        ObjectAnimator.ofFloat(viewBinding?.optionOneContainer, "translationY", 0f)
            .start()//тоже

        viewBinding!!.optionTwoContainer.animate()?.alpha(0f)//прозрачность(0=полностью прозначна)
            .setDuration(300)//длительность анимации
            .setListener(object : AnimatorListenerAdapter() {//листенер на завершение анимации
                override fun onAnimationEnd(animation: Animator?) {
                    safeBinding.optionTwoContainer.isClickable = false
                    safeBinding.optionOneContainer.setOnClickListener(null)
                }
            })
        safeBinding.optionOneContainer.animate()
            .alpha(0f)
            .setDuration(300)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    safeBinding.optionOneContainer.isClickable = false
                }
            })

        safeBinding.transparentLayout.animate()//закрывает наш экран
            .alpha(0f)//полностью невидно
            .setDuration(300)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    safeBinding.transparentLayout.isClickable = false
                }
            })

    }

    private fun expandFab() {
        isExpanded = true
        val safeBinding = viewBinding ?:return
        ObjectAnimator.ofFloat(safeBinding.plusImageview,"rotation", 0f,225f).start()//развернуть кнопку на 225 градусов
        ObjectAnimator.ofFloat(safeBinding.optionTwoContainer,"translationY",-130f).start()//движение по вертикали
        ObjectAnimator.ofFloat(safeBinding.optionOneContainer,"translationY",-250f).start()

        safeBinding.optionTwoContainer.animate().//анимироваьб
                alpha(1f).//прозрачность (полностью видно))
                setDuration(300).//длительность анимации
                setListener(object :AnimatorListenerAdapter(){//листенер на завершение анимации
                    override fun onAnimationEnd(animation: Animator?) {
                        safeBinding.optionTwoContainer.isClickable = true
                        safeBinding.optionTwoContainer.setOnClickListener{
                            Toast.makeText(requireContext(), "Option 2", Toast.LENGTH_SHORT).show()
                        }
                    }
                })
        safeBinding.optionOneContainer.animate()
            .alpha(0.7f)
            .setDuration(300)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    safeBinding.optionOneContainer.isClickable = true
                    safeBinding.optionOneContainer.setOnClickListener {
                        Toast.makeText(requireContext(), "Option 1", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        safeBinding.transparentLayout.animate()
            .alpha(1f)//полностью видно(этот лейаут над нашей картинкой,закрывает ее)
            .setDuration(300)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    safeBinding.transparentLayout.isClickable = true
                }
            })

    }
}
