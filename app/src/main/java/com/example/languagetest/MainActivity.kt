package com.example.languagetest

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    companion object{
        var current:String?=null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.button).setOnClickListener {
            current = if(current == "sw"){
                "en"
            }else{
                "sw"
            }
            val intent: Intent = prepareIntentForRecreateActivity()
            finish()
            startActivity(intent)
        }
    }

    private fun prepareIntentForRecreateActivity(): Intent {
        val intent = intent
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        return intent
    }

    override fun attachBaseContext(newBase: Context?) {
        val updatedCtx: Context = LocaleHelper.updateLocale(newBase!!, current)
        super.attachBaseContext(updatedCtx)
    }
}