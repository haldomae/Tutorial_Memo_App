package com.haldomae.tutorial_memo_app.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.concurrent.TimeUnit

/**
 * 単一のメモを表すデータクラス
 * @param id ユニークなID
 * @param title メモのタイトル
 * @param contents メモの内容
 * @param createdTimeMillis メモ作成日
 * @param updateTimeMillis メモ更新日
 * @param expireTimeMillis メモの締め切り (廃棄日)
 */
@Entity
data class Memo (
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "contents") val contents: String,
    @ColumnInfo(name = "created_time_millis") val createdTimeMillis: Long,
    @ColumnInfo(name = "update_time_millis") val updateTimeMillis: Long,
    @ColumnInfo(name = "expire_time_millis") val expireTimeMillis: Long
) {
    companion object{
        const val TITLE = "title"
        const val CONTENTS = "contents"
        const val CREATED_TIME = "created_time_millis"
        const val UPDATE_TIME = "update_time_millis"
        const val EXPIRE_TIME = "expire_time_millis"
    }
}