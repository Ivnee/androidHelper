package com.geek.infoandroid.Kotlin.materialDesign.les5

import android.animation.Animator
import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.transition.*
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import com.geek.infoandroid.MainActivity
import com.geek.infoandroid.R
import com.geek.infoandroid.databinding.MainFragmentBinding

class Animationsss : Fragment(R.layout.main_fragment){
    var bools = false
    var isExpanded = false
    var toRightAnimation = false
    var viewBinding: MainFragmentBinding? = null

    val titles = mutableListOf<String>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = MainFragmentBinding.bind(view)
        val safeBinding = viewBinding ?: return
        viewBinding?.btn?.setOnClickListener {

//Анимирование Visibility
            TransitionManager.beginDelayedTransition(
                viewBinding?.main,//в какой вью группе будут применяться анимации на действия внутри нее
                Slide(Gravity.END).setDuration(1000)
            )//1)где устанавливаем анимацию
            //2)какая будет анимация
            //ChangeBounds меняет расположение элемента и его размеры, двигает нашу кнопку по умолчанию.
            //Fade — медленное проявление и затухание элемента. Применяется для отображения нашего текста по умолчанию.
            //TransitionSet — это набор других Transitions, таких как ChangeBounds или Fade.
            //AutoTransition — TransitionSet, содержащий Fade out, ChangeBounds и Fade в таком порядке. То есть сначала исчезают view, которые должны исчезнуть, затем изменяется расположение остальных view, затем появляются новые. AutoTransition применяется по умолчанию, если вы не передаёте никаких параметров в качестве второго аргумента в функцию beginDelayedTransition.
            //Slide - Аналог Fade. Элемент появляется, не меняя видимость, а выезжая из-за границы экрана.Slide(Gravity.START)


            viewBinding?.tv?.visibility = if (bools) {
                View.GONE
            } else {
                View.VISIBLE
            }
            bools = bools.not()
            // explode(it)
        }
            //Приближение отдаление картиинки
        safeBinding.image.setOnClickListener{
            isExpanded = !isExpanded
            val transitionSet = TransitionSet()//создаем сет из анимаций
                .addTransition(ChangeBounds())//анимирует положение и размеры вью
                .addTransition(ChangeImageTransform())//анимирует изменение  scaleType у картинок
            TransitionManager.beginDelayedTransition(safeBinding.main,transitionSet)//устанавливаем анимации
            val params: ViewGroup.LayoutParams =safeBinding.image.layoutParams// сохраняем параметры нашей имидж вью
            params.height = if(isExpanded) ViewGroup.LayoutParams.MATCH_PARENT else ViewGroup.LayoutParams.WRAP_CONTENT//размер
            safeBinding.image.layoutParams = params//сетим параметры в нашу имидж вью
            safeBinding.image.scaleType = if(isExpanded) ImageView.ScaleType.CENTER_CROP else ImageView.ScaleType.FIT_CENTER//устанавливаем ScaleType

        }

        //Анимация перемещения кнопки
        viewBinding?.btn?.setOnClickListener {
            val changeBounds = ChangeBounds()//анимация плавного перемещения по траэкториям
            changeBounds.pathMotion = ArcMotion()//арч моушн тоже анимация из пакета,которая сетит траэкторию
            changeBounds.duration = 1000//длительность анимации
            TransitionManager.beginDelayedTransition(safeBinding.main,changeBounds)//менеджер анимаций,указываем где будет анимация и какая анимация
            toRightAnimation = !toRightAnimation
            val params = safeBinding.btn.layoutParams as FrameLayout.LayoutParams//определяем параметры
            params.gravity = if(toRightAnimation)Gravity.END or Gravity.BOTTOM else Gravity.START or Gravity.TOP//инициализируем
            safeBinding.btn.layoutParams = params//устанавливаем параметры вьюхи

        }
        //Анимация перемешивания
        for(i in 0..4){
            titles.add(String.format("Item %d", i + 1))//СОЗДАЕМ НОМЕРА ОТ 1 ДО 5
        }
        createViews(safeBinding.linearL,titles) //метод для создания вьюх ,передаем лэйаут в котором создавать будем и названия

        viewBinding?.btn?.setOnClickListener {
            TransitionManager.beginDelayedTransition(safeBinding.linearL,ChangeBounds())//создаем анимацию для нашего лэйаута
            titles.shuffle()//перемешиваем наши названия тв
            createViews(safeBinding.linearL,titles)//создаем наши вьюхи
        }
    }

    ////////////к анимации перемешивария
    private fun createViews(linearLey: ViewGroup, titles: MutableList<String>) {//метод создаем вьюхи и присваивает им айди для перемешивания
        linearLey.removeAllViews()//удалить все вьюхи
        for (title in titles) {
            val textView = TextView(requireContext())//создаем текст вью
            textView.text = title//указываем ему текст
            textView.gravity = Gravity.CENTER_HORIZONTAL//положение тв
            ViewCompat.setTransitionName(//присваиваем айди
                textView,
                title
            )//устанавливаем айди для анимации конкретного вью,чтоб он запоминал положение вьюх по тексту
            linearLey.addView(textView)//добавляем в лэйаут
        }
    }
    ///////////////Разлетающаяся анимация
    private fun explode(btnClicked: View) {
        val viewRect = Rect()//создаем прямоугольник
        btnClicked.getGlobalVisibleRect(viewRect)//передаем координаты видимого прямоугольник(т е экрана)в наш прямоугольник
        val explode = Explode()//это сама анимация(приблежает и расскидывает весь экран)
        explode.epicenterCallback = object : Transition.EpicenterCallback() {
            //коллбек анимации,в которой передаем вью с которой будет эта анимация начинаться
            override fun onGetEpicenter(transition: Transition?): Rect {
                return viewRect//возвращаем прямоугольник в области которого будет создаваться анимация
            }
        }
        explode.excludeTarget(btnClicked,true)//не применять анимацию на эту вью
        explode.duration = 1000//анимация будет идти секунду
        val set = TransitionSet()//дает возможность юзать несколько анимаций(соединить их)
            .addTransition(explode)//добавляем анимацию
            .addTransition(Fade().addTarget(btnClicked))//закрываем кнорку ,которую нажали через анимацию фэйд
            .addListener(@RequiresApi(Build.VERSION_CODES.O)
            object : TransitionListenerAdapter() {
                override fun onTransitionEnd(transition: Transition) {
                    transition.removeListener(this)//удаляем текущий листенер
                    requireActivity().onBackPressed()//устанавливаем чтоб при клике нажималось назад
                }
            })
        TransitionManager.beginDelayedTransition(viewBinding?.main,set)//вместо мэйн можно рв еще (вместо сет можно поставить просто explode,но тогда не сможем добавить еще одну анимацию)

    //анимация на конкретную вьюху
        imageView.animate()
            .setDuration(2000)//длительность анимации
            .rotationBy(1000f)//крутить по часовой стрелке
            .scaleXBy(-1f)//развернуть по оси х
            .scaleYBy(-1f)//развернуть по y
            .rotationXBy(100f)//прокрутить по оси х
            .alpha(0.5f)//уменьшить прозрачтость на 50 процентов
            .setInterpolator(AccelerateInterpolator())
            .setListener(object : Animator.AnimatorListener{
                override fun onAnimationEnd(animation: Animator?) {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }

                override fun onAnimationStart(animation: Animator?) {}

                override fun onAnimationCancel(animation: Animator?) {}

                override fun onAnimationRepeat(animation: Animator?) {}
            })
    }
}