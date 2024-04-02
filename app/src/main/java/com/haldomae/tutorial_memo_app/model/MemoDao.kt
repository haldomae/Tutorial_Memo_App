package com.haldomae.tutorial_memo_app.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.haldomae.tutorial_memo_app.data.Memo

@Dao
interface MemoDao {
        // 挿入
        // 同じデータが存在していた場合入れ替える
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun insertMemo(memo:Memo)
        // 取得
        @Query("SELECT * from memo")
        fun fetchAll(): List<Memo>
        // 削除
        @Delete
        fun deleteMemo(memo:Memo)
}