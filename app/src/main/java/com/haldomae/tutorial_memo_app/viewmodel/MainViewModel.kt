package com.haldomae.tutorial_memo_app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haldomae.tutorial_memo_app.data.Memo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _memoitems = MutableLiveData<List<Memo>>()
    
    val  memoItems: LiveData<List<Memo>> = _memoitems

    // メモリストを読み込む
    fun loadMemoItems() {
        // データ取得は非同期で
        // データ取得はIOスレッド
        viewModelScope.launch(Dispatchers.IO) {
            // LiveDataのメインスレッド以外での変更はpostValue()を使う
            // メインスレッドならば、memoItems.value = で行う
            _memoitems.postValue(Memo.createFakes()) // 本来はDBやCacheから取得
        }
    }
}