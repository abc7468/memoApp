package com.example.mylasttest

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class IntroActivity : AppCompatActivity() {

    var handler: Handler? = null // Runnable 을 실행하는 클래스
    var runnable: Runnable? = null // 병렬 실행이 가능한 Thread를 만들어주는 클래스

    companion object{
        private const val REQUEST_LOCATION_PERMISSION_CODE = 100
    }

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

    fun checkLocationPermission():Boolean{
        val fineLocationPermission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
        val coarseLocationPermission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED

        return fineLocationPermission&&coarseLocationPermission
    }

    fun moveListActivity(){
        runnable = Runnable{
            val intent = Intent(applicationContext, ListActivity::class.java)
            startActivity(intent)
            finish()
        }

        handler = Handler()
        handler?.run{
            postDelayed(runnable,2000)
        }
    }

    override fun onResume(){
        super.onResume()

        if(checkLocationPermission()){
            moveListActivity()
        }
        else{
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.ACCESS_FINE_LOCATION)){
                Toast.makeText(this, "이 앱을 실행하려면 위치 권한이 필요합니다.", Toast.LENGTH_LONG).show()
            }

            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION),
            REQUEST_LOCATION_PERMISSION_CODE)
        }

    }

    override fun onPause(){
        super.onPause()

        handler?.removeCallbacks(runnable)
    }

}
