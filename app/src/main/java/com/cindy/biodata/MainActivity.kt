package com.cindy.biodata

import android.Manifest
import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat


class MainActivity : AppCompatActivity() {
    @SuppressLint("CutPasteId")
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val teleponButton: ImageButton = findViewById<View>(R.id.buttonTelepon) as ImageButton
        teleponButton.setOnClickListener { makeCall() }

        val mailButton: ImageButton = findViewById<View>(R.id.buttonEmail) as ImageButton
        mailButton.setOnClickListener { makeMail() }

        val mapsButton: ImageButton = findViewById<View>(R.id.buttonMaps) as ImageButton
        mapsButton.setOnClickListener { makeMaps() }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun makeCall() {
        val callIntent = Intent(Intent.ACTION_DIAL)
        callIntent.data = Uri.parse("tel:088228902384")
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.CALL_PHONE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CALL_PHONE),
                10
            )
            return
        } else {
            try {
                startActivity(callIntent)
            } catch (ex: ActivityNotFoundException) {
                Toast.makeText(
                    applicationContext,
                    "Tidak dapat dilakukan, coba beberapa saat lagi",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun makeMail() {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "plain/text"
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("cindyesa91@gmail.com"))
        startActivity(Intent.createChooser(intent, ""))
    }

    private fun makeMaps() {
        val latitude = -7.0277034
        val longitude = 111.0113451
        val label = "Cindy Address"
        val gmmIntentUri = Uri.parse("geo:0,0?q=$latitude,$longitude($label)")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)
    }
}