package com.haldomae.tutorial_memo_app.repository

import android.content.Context
import androidx.room.Room
import com.haldomae.tutorial_memo_app.data.Memo
import com.haldomae.tutorial_memo_app.model.MemoDatabase

class MemoRepository(context: Context) {
    private val dao = Room.databaseBuilder(
        context,
        MemoDatabase::class.java,
        DATABASE_NAME
    ).build().memoDao()

    fun insertMemo(title: String, contents: String, expiredDuration: Long) {
        val currentTime = System.currentTimeMillis()
        dao.insertMemo(
            Memo(
                id = 0, // autoGenerateの場合は0を指定
                title = title,
                contents = contents,
                createdTimeMillis = currentTime,
                updateTimeMillis = currentTime,
                expireTimeMillis = currentTime + expiredDuration
            )
        )
    }
    
    companion object{
        const val DATABASE_NAME = "memo"
    }
}

