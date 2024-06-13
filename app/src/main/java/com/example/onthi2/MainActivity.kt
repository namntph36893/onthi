package com.example.onthi2

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button

import androidx.compose.material3.Text

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

import java.util.Timer
import java.util.TimerTask

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // chuyen man hinh sau x giay
        var abc = Intent(this, HomeActivity::class.java)
        var i = Timer();
        i.schedule(object : TimerTask() {
            override fun run() {
                startActivity(abc)
            }
        }, 5000)

        setContent {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = ""
                )
                Text(text = "Hello Namntph36893")
                Button(onClick = {
                    var i = Intent(this@MainActivity, HomeActivity::class.java)
                    startActivity(i)
                }) {
                    Text(text = "Go TO Main ")
                }
            }
        }
    }
}
