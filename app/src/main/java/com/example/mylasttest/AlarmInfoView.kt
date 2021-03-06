package com.example.mylasttest

import android.content.Context
import android.icu.text.SimpleDateFormat
import android.util.AttributeSet
import kotlinx.android.synthetic.main.view_info.view.*
import java.util.*

class AlarmInfoView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : InfoView(context, attrs, defStyleAttr){
    companion object{
        private val dateFormat = SimpleDateFormat("yy/MM/dd HH:mm")
    }
    init{
        typeImage.setImageResource(R.drawable.ic_alarm)
        infoText.setText("")
    }

    fun setAlarmDate(alarmDate : Date){
        if(alarmDate.before(Date())){
                infoText.setText("알람이 없습니다")
        }
        else{
            infoText.setText(dateFormat.format(alarmDate))
        }
    }
}