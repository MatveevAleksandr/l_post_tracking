package com.example.l_post_tracking.presentation

import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.l_post_tracking.R
import com.example.l_post_tracking.model.FindByPhoneMainActivityState
import com.example.l_post_tracking.viewmodel.MainViewModel

class FindByPhoneFragment() : Fragment() {

    private var viewModel: MainViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_find_order_by_phone, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val edPhoneNum = view.findViewById<EditText>(R.id.edPhoneNumber)
        val phoneNum = edPhoneNum.text.toString()
        val errLabel = view.findViewById<TextView>(R.id.errMess_PhoneFrg)
        val orderOrTrackNum = (viewModel?.getMainActivityFragmentState() as FindByPhoneMainActivityState).orderNum

        view.setBackgroundResource(R.drawable.round_corner)
        view.findViewById<TextView>(R.id.tvOrderNumHeader).text =
            String.format(resources.getString(R.string.defaultNumOrder), orderOrTrackNum)
        edPhoneNum.addTextChangedListener(
            PhoneNumberFormattingTextWatcher()
        )

        view.findViewById<Button>(R.id.btnFindClarifyPhone).setOnClickListener {
            viewModel?.findByPhoneNumClick(
                orderOrTrackNum = orderOrTrackNum, _phoneNum = phoneNum
            )
        }
        viewModel?.getMainActivityFragmentState()?.observe(viewLifecycleOwner) {
            it as FindByPhoneMainActivityState
            if (it.errorMsg.isNullOrEmpty()) {
                errLabel.text = ""
                errLabel.visibility = View.GONE
            } else {
                errLabel.text = it.errorMsg
                errLabel.visibility = View.VISIBLE
            }
        }
    }

    override fun onDestroy() {
        deAttachViewModel()
        super.onDestroy()
    }

    fun attachViewModel(_vm: MainViewModel) {
        this.viewModel = _vm
    }

    private fun deAttachViewModel() {
        this.viewModel = null
    }
}