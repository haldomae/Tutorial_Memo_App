package com.haldomae.tutorial_memo_app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.haldomae.tutorial_memo_app.repository.MemoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Duration
import java.util.concurrent.TimeUnit

class NewMemoViewModel(private val memoRepository: MemoRepository) : ViewModel() {
    private val _insertEvent = MutableLiveData<Unit>()
    // DB挿入イベント
    val insertEvent: LiveData<Unit> = _insertEvent

    // Memoを登録する
    fun registerMemo(
        title: String,
        contents: String,
        expireDuration: Long = TimeUnit.DAYS.toMillis(7)) {
        viewModelScope.launch(Dispatchers.IO) {
            memoRepository.insertMemo(title, contents, expireDuration)
            _insertEvent.postValue(Unit)
        }
    }
}

// NewMemoViewModelを生成する独自のFactoryクラス
class NewMemoViewModelFactory(
    private val memoRepository: MemoRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun<T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewMemoViewModel::class.java))
            return NewMemoViewModel(memoRepository) as T
        throw IllegalAccessException("Unknown ViewModel class")
    }

}
