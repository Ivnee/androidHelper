package com.geek.infoandroid.Kotlin.les9PermissionsProvider

import androidx.fragment.app.Fragment
import android.content.ContentResolver
import android.database.Cursor
import android.provider.ContactsContract
import androidx.recyclerview.widget.RecyclerView

class GetContacts: Fragment() {
    val adapter: RecyclerView? = null
    fun contacts(){
        val contentReslover: ContentResolver = requireContext().contentResolver//получаем контент ресловер
        val cursor: Cursor? = contentReslover.query(//получаем курсор из запроса контактов
            ContactsContract.Contacts.CONTENT_URI,//получить контакты
            null,
            null,
            null,
            ContactsContract.Contacts.DISPLAY_NAME + " DESC"//отсортировать по убиыванию
        )
        val contacts = mutableListOf<String>()
        val safeCursor = cursor ?: return
        while (safeCursor.moveToNext()) {
            val contactIndex = safeCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)
            val displayName = safeCursor.getString(contactIndex)
            contacts.add(displayName)
        }
        adapter.setData(contacts)


        cursor?.close()//обязательно закрывать
        adapter.setData(contacts)
    }
}