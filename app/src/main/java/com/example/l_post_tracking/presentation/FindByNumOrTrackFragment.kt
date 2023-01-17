package com.example.l_post_tracking.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.l_post_tracking.R
import com.example.l_post_tracking.viewmodel.MainViewModel
import kotlinx.coroutines.*

class FindByNumOrTrackFragment(private val vm: MainViewModel) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_find_order_by_number, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnFind = view.findViewById<Button>(R.id.btnFind)

        btnFind.setOnClickListener {
            val findStr = view.findViewById<EditText>(R.id.edOrderNum).text.toString()
            vm.findByOrderOrTrackNumClick(findStr)
        }

        vm.getMainActivityFragmentState().observe(viewLifecycleOwner) { mainActivityFragmentState ->
            if (!mainActivityFragmentState.errorMsg.isNullOrEmpty()) {
                val errLabel = view.findViewById<TextView>(R.id.errMess_NumberFrg)
                errLabel.text = mainActivityFragmentState.errorMsg
                errLabel.visibility = View.VISIBLE
            }
        }
    }
}