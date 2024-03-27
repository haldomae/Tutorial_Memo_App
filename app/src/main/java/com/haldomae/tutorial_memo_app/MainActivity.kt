package com.haldomae.tutorial_memo_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.haldomae.tutorial_memo_app.data.Memo

//　アプリの土台はActivity
// ActivityはAppCompatActivityクラスを継承して作成する
class MainActivity : AppCompatActivity() {
    // Activityが生成されたときに呼ばれるメソッド
    override fun onCreate(savedInstanceState: Bundle?) {
        // 親クラスAppCompatActivity()の同名メソッドを呼ぶ
        super.onCreate(savedInstanceState)
        // res > layout > activity_main.xmlを元にViewを生成する
        setContentView(R.layout.activity_main)

        // findViewById<T>()の <T>は型推論できるので省略可能
        // activity_main.xmlで割り当てたIDからViewを探す
        // val mainTextView = findViewById<TextView>(R.id.main_text)って書いても良い
        // val mainTextView: TextView = findViewById(R.id.main_text)
        val addButton: Button = findViewById(R.id.add_button)

        // RecyclerViewをセット
        val recyclerView: RecyclerView = findViewById(R.id.main_list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = MainAdapter(Memo.createFakes())
        recyclerView.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )

        addButton.setOnClickListener {
            // ActivityでFragmentを生成する時は、FragmentManager経由でFragmentTransactionのメソッドで追加を行う
            supportFragmentManager.beginTransaction().apply {
                // FragmentLayout上にNewMemoFragmentを追加する
                add(R.id.main_container, NewMemoFragment())
                // バックスタックにトランザクションを追加
                // バックトランザクションに追加しておくと、Fragmentを積み上げた場合、期待したFragmentやActivityに戻れなくなるのを防ぐ
                // 引数をnull以外にするとトランザクションに名前を付ける事ができる
                addToBackStack(null)
                // コミット
                commit()
            }
        }
    }
}