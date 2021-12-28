package com.geek.infoandroid.Kotlin.les9PermissionsProvider

import android.database.Cursor
import android.provider.ContactsContract
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CursorAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var cursor:Cursor
    fun setCursor(cursorToSet: Cursor){
        if(::cursor.isInitialized && cursor != cursorToSet){//если уже проинициализирован и не равен курсорТуСет
            cursor.close()//то закрываем
        }
        cursor = cursorToSet
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        cursor.moveToPosition(position)//передвигаемся на нужный нам элемент(тут можно еще на нул проверки сделать)
        val name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))//получаем имя контакта
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}