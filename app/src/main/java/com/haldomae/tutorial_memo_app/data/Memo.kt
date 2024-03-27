package com.haldomae.tutorial_memo_app.data

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
data class Memo (
    val id: Int,
    val title: String,
    val contents: String,
    val createdTimeMillis: Long,
    val updateTimeMillis: Long,
    val expireTimeMillis: Long
) {
    companion object{
        // ダミーデータを10件作成する
        fun createFakes(): List<Memo> {
            val now = System.currentTimeMillis()
            return List(10) {
                Memo(
                    id = it,
                    title = "Memo $it",
                    contents = "memo description",
                    createdTimeMillis = now,
                    updateTimeMillis = now,
                    expireTimeMillis = now + TimeUnit.DAYS.toMillis(7)
                )
            }
        }
    }
}