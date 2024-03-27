package com.haldomae.tutorial_memo_app

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.haldomae.tutorial_memo_app.data.Memo
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import kotlin.collections.List as List

// RecyclerView : 子Viewをリサイクルして表示する大枠のViewGroup
// Recycler.ViewHolder : 一つ一つの要素View(Item View)を保持するホルダー
// Recycler.Adapter : 子View(Adapter)とデータをViewHolderを介して橋渡しする役割
class MainAdapter : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
    // private val memoItems: MutableList<Memo> = memoItems.toMutableList()
    private var memoItems: List<Memo> = emptyList()

    // 一つ一つのItemViewを持つ (Holdする) ViewHolder
    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val title: TextView = itemView.findViewById(R.id.memo_title)
        val createdDate: TextView = itemView.findViewById(R.id.memo_created_date)
        val updatedData: TextView = itemView.findViewById(R.id.memo_updated_date)
        val expiredDate: TextView = itemView.findViewById(R.id.memo_expired_date)
    }


    // ItemViewのレイアウトをViewHolderにInflateする
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.memo_item_view, parent, false)
        )
    }

    override fun getItemCount(): Int = memoItems.size

    // ViewHolderが作られた時や更新された時に呼ばれるメソッド
    // ここでViewの中身を決めたりViewの設定を行う
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        // positionからどのデータをViewHolderに保持させるViewを決める
        val memo = memoItems[position]

        holder.title.text = memo.title
        holder.createdDate.text = memo.createdTimeMillis.toDateString()
        holder.updatedData.text = memo.updateTimeMillis.toDateString()
        holder.expiredDate.text = memo.expireTimeMillis.toDateString()
    }

    // UNIX epoch time millis to String
    @RequiresApi(Build.VERSION_CODES.O)
    private fun Long.toDateString() =
        Instant.ofEpochMilli(this)
            .atZone(ZoneId.systemDefault())
            .format(DATE_FORMATTER)


    companion object {
        private const val DATE_FORMAT = "yyyy/MM/dd"
        @RequiresApi(Build.VERSION_CODES.O)
        private val DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT)
    }

    // データの変更とデータ変更通知
    fun setMemoItems(items: List<Memo>) {
        memoItems = items
        notifyDataSetChanged()
    }
}