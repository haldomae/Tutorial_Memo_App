package com.haldomae.tutorial_memo_app

import android.app.Application
import com.haldomae.tutorial_memo_app.repository.MemoRepository

class App : Application() {
    // MemoRepositoryを参照
    // by lazyは遅延初期化で呼ばれたタイミングで初期化が行われる
    val memoRepository: MemoRepository by lazy { MemoRepository(this) }
}