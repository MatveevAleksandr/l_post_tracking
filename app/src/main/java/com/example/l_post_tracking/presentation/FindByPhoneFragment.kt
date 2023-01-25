package com.example.l_post_tracking.presentation

import android.content.Context
import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.util.Log
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

    private var mainActivity: IMainActivity? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_find_order_by_phone, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val edPhoneNum = view.findViewById<EditText>(R.id.edPhoneNumber)
        val errLabel = view.findViewById<TextView>(R.id.errMess_PhoneFrg)
        val orderOrTrackNum =
            (mainActivity?.getMainActivityState()?.value as FindByPhoneMainActivityState).orderNum

        view.setBackgroundResource(R.drawable.round_corner)
        view.findViewById<TextView>(R.id.tvOrderNumHeader).text =
            String.format(resources.getString(R.string.defaultNumOrder), orderOrTrackNum)
        edPhoneNum.addTextChangedListener(
            PhoneNumberFormattingTextWatcher()
        )

        view.findViewById<Button>(R.id.btnFindClarifyPhone).setOnClickListener {
            mainActivity?.findByPhoneNumClick(
                orderOrTrackNum = orderOrTrackNum, phoneNum = edPhoneNum.text.toString()
            )
        }
        mainActivity?.getMainActivityState()?.observe(viewLifecycleOwner) {
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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            mainActivity = activity as IMainActivity
        } catch (e: ClassCastException) {
            throw ClassCastException("Activity $activity must implement IMainActivity")
        }
    }
}