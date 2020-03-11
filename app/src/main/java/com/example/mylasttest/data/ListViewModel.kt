package com.example.mylasttest.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mylasttest.MemoDao
import com.example.mylasttest.RealmLiveData
import io.realm.Realm

class ListViewModel : ViewModel(){
//    private val memos: MutableList<MemoData> = mutableListOf()
//    val memoLiveData: MutableLiveData<MutableList<MemoData>> by lazy{
//        MutableLiveData<MutableList<MemoData>>().apply{
//            value = memos
//        }
//    }
//
//    fun addMemo(data: MemoData){
//        val tempList = memoLiveData.value
//        tempList?.add(data)
//        memoLiveData.value = tempList
//    }

    private val realm: Realm by lazy{
        Realm.getDefaultInstance()
    }

    private val memoDao: MemoDao by lazy{
        MemoDao(realm)
    }

    val memoLiveData: RealmLiveData<MemoData> by lazy{
        RealmLiveData<MemoData>(memoDao.getAllMemos())
    }

    override fun onCleared(){
        super.onCleared()
        realm.close()
    }
}