package com.example.l_post_tracking.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.l_post_tracking.R
import com.example.l_post_tracking.model.FindByNumOrTrackMainActivityState
import com.example.l_post_tracking.viewmodel.MainViewModel

class FindByNumOrTrackFragment(
) : Fragment() {

    private var viewModel: MainViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_find_order_by_number, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.setBackgroundResource(R.drawable.round_corner)
        val btnFind = view.findViewById<Button>(R.id.btnFind)

        btnFind.setOnClickListener {
            val findStr = view.findViewById<EditText>(R.id.edOrderNum).text.toString()
            viewModel?.findByOrderOrTrackNumClick(findStr)
        }

        viewModel?.getMainActivityFragmentState()?.observe(viewLifecycleOwner) { it as FindByNumOrTrackMainActivityState
            val errLabel = view.findViewById<TextView>(R.id.errMess_NumberFrg)
            if (it.errorMsg.isNullOrEmpty()) {
                errLabel.text = ""
                errLabel.visibility = View.GONE
            }
            else{
                errLabel.text = it.errorMsg
                errLabel.visibility = View.VISIBLE
            }
        }
    }

    override fun onDestroy() {
        deAttachViewModel()
        super.onDestroy()
    }

    fun attachViewModel(_vm: MainViewModel){
        this.viewModel = _vm
    }

    private fun deAttachViewModel(){
        this.viewModel = null
    }
}
