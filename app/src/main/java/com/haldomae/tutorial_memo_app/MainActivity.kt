package com.haldomae.tutorial_memo_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

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
        val mainTextView: TextView = findViewById(R.id.main_text)
        val addButton: Button = findViewById(R.id.add_button)

        addButton.setOnClickListener {
            mainTextView.text = "ボタンが押された!"
        }
    }
}