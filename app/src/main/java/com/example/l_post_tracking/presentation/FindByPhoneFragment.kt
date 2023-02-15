package com.example.l_post_tracking.presentation

import android.content.Context
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
import com.example.l_post_tracking.model.FindByPhoneMainScreenState
import com.example.l_post_tracking.presentation_compose.IMainActivity

class FindByPhoneFragment() : Fragment() {

    private var mainActivity: IMainActivity.IFindByPhoneFragment? = null

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
            (mainActivity?.getMainActivityLiveDataState()?.value as FindByPhoneMainScreenState).orderNum

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
        mainActivity?.getMainActivityLiveDataState()?.observe(viewLifecycleOwner) {
            it as FindByPhoneMainScreenState
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
            mainActivity = activity as IMainActivity.IFindByPhoneFragment
        } catch (e: ClassCastException) {
            throw ClassCastException("Activity $activity must implement IMainActivity.IFindByPhoneFragment")
        }
    }
}