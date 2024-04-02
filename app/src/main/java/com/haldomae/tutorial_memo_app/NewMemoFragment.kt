package com.haldomae.tutorial_memo_app

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.haldomae.tutorial_memo_app.viewmodel.NewMemoViewModel
import com.haldomae.tutorial_memo_app.viewmodel.NewMemoViewModelFactory

class NewMemoFragment : Fragment(R.layout.fragment_new_memo) {
    private val viewModel: NewMemoViewModel by viewModels {
        NewMemoViewModelFactory((requireActivity().application as App).memoRepository)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.new_memo_add_button).setOnClickListener {
            val title = view.findViewById<EditText>(R.id.new_memo_title).text.toString()
            val contents = view.findViewById<EditText>(R.id.new_memo_contents).text.toString()

            if (title.isEmpty())
                return@setOnClickListener
            viewModel.registerMemo(title, contents)
        }

        // DB挿入されたらFragmentを閉じる
        viewModel.insertEvent.observe(viewLifecycleOwner) { dismiss() }
    }

    private fun  dismiss() {
        parentFragmentManager.popBackStack()
    }

}