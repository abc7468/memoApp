package com.example.mylasttest

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mylasttest.data.MemoData
import io.realm.Realm
import io.realm.RealmResults
import java.util.*

class DetailViewModel: ViewModel(){
    var memoData = MemoData()
    val memoLiveData: MutableLiveData<MemoData> by lazy{
        MutableLiveData<MemoData>().apply{value = memoData}
    }

    private val realm: Realm by lazy{
        Realm.getDefaultInstance()
    }

    private val memoDao: MemoDao by lazy{
        MemoDao(realm)
    }

    override fun onCleared() {
        super.onCleared()
        realm.close()
    }

    fun loadMemo(id:String){
        memoData = realm.copyFromRealm(memoDao.selectMemo(id))
        memoLiveData.value = memoData
    }

    fun deleteAlarm(){
        memoData.alarmTime = Date(0)
        memoLiveData.value = memoData
    }

    fun setAlarm(time:Date){
        memoData.alarmTime = time
        memoLiveData.value = memoData
    }

    fun deleteLocation(){
        memoData.latitude = 0.0
        memoData.longitude = 0.0
        memoLiveData.value = memoData
    }

    fun setLocation(latitude: Double, longitude: Double){
        memoData.latitude = latitude
        memoData.longitude = longitude
        memoLiveData.value = memoData
    }

    fun addOrUpdateMemo(context: Context){
        AlarmTool.deleteAlarm(context, memoData.id)
        if(memoData.alarmTime.after(Date())){
            AlarmTool.addAlarm(context, memoData.id, memoData.alarmTime)
        }
    }

}