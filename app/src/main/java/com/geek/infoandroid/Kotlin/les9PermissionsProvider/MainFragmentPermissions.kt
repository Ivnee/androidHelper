package com.geek.infoandroid.Kotlin.les9PermissionsProvider

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.geek.infoandroid.MainActivity
import com.geek.infoandroid.R
import com.google.android.material.snackbar.Snackbar

class MainFragment() : Fragment(R.layout.fragment_main) {
    val permissionRequest =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {//или RequestMultiplePermission, для запроса нескольких пермишенов(для запуска нужен аррай в launch)
            if (it) {//эта конструкция создает запрос пермишенов,внутри нужно описать поведение в случае удачи и неудачи запроса
                //получилось получить пермишн(openContacts())
            } else {
                //не получилось
                Toast.makeText(requireContext(),"cant use this feature", Toast.LENGTH_LONG).show()
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_CONTACTS
            )//проверить пермишены
            == PackageManager.PERMISSION_GRANTED//проверить что они разрешены
        ) {
            //еслои все ок, то открываем контакты
        } else {
            if (shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS)) {//если необходимо показать для чего нужны пермишены
                Snackbar.make(view, "need this permission for ...", Snackbar.LENGTH_SHORT)
                    .setAction("grand") {
                        permissionRequest.launch(Manifest.permission.READ_CONTACTS)
                    }
                    .show()
            } else {
                permissionRequest.launch(Manifest.permission.READ_CONTACTS)
            }
        }
    }
}