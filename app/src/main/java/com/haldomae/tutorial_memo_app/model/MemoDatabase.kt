package com.haldomae.tutorial_memo_app.model

import androidx.room.Database
import androidx.room.RoomDatabase
import com.haldomae.tutorial_memo_app.data.Memo

@Database(entities = [Memo::class], version = 1)
abstract class MemoDatabase  : RoomDatabase(){
    abstract fun memoDao(): MemoDao
}