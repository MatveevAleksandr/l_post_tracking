package com.example.l_post_tracking.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.l_post_tracking.R
import com.example.l_post_tracking.model.FindByNumOrTrackMainScreenState
import com.example.l_post_tracking.presentation_compose.IMainActivity

class FindByNumOrTrackFragment : Fragment() {

    private var mainActivity: IMainActivity.IFindByNumOrTrackFragment? = null

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
            mainActivity?.findByOrderOrTrackNumClick(orderOrTrackNum = findStr)
        }

        mainActivity?.getMainActivityLiveDataState()?.observe(viewLifecycleOwner) {
            it as FindByNumOrTrackMainScreenState
            val errLabel = view.findViewById<TextView>(R.id.errMess_NumberFrg)
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
            this.mainActivity = activity as IMainActivity.IFindByNumOrTrackFragment
        } catch (e: ClassCastException) {
            throw ClassCastException("Activity $activity must implement IMainActivity.IFindByNumOrTrackFragment")
        }
    }
}
