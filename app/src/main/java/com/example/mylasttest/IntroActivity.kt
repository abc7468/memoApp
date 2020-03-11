package com.example.mylasttest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View

class IntroActivity : AppCompatActivity() {

    var handler: Handler? = null // Runnable 을 실행하는 클래스
    var runnable: Runnable? = null // 병렬 실행이 가능한 Thread를 만들어주는 클래스


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        //시스템 UI제거
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LOW_PROFILE or
                View.SYSTEM_UI_FLAG_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
    }

    override fun onResume(){
        super.onResume()

        runnable = Runnable{
            val intent = Intent(applicationContext, ListActivity::class.java)
            startActivity(intent)
        }
        handler = Handler()
        handler?.run{
            postDelayed(runnable,2000)
        }

    }

    override fun onPause(){
        super.onPause()

        handler?.removeCallbacks(runnable)
    }

}
