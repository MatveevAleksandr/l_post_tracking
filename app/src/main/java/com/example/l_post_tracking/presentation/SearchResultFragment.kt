package com.example.l_post_tracking.presentation

import android.content.Context
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.l_post_tracking.R
import com.example.l_post_tracking.model.ResultMainActivityState
import com.example.l_post_tracking.viewmodel.MainViewModel
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class SearchResultFragment : Fragment() {

    private var mainActivity: IMainActivity? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_find_order_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.setBackgroundResource(R.drawable.round_corner)
        mainActivity?.getMainActivityState()?.observe(viewLifecycleOwner) {
            it as ResultMainActivityState
            val orderData = it.orderData
            if (orderData.deliveryDatePlan.isNullOrEmpty()) {
                view.findViewById<TextView>(R.id.tvDeliveryDate).text =
                    "Дата будет известна позднее"
            } else {
                val formattedDate: String = DateFormat.getDateInstance(DateFormat.FULL).format(
                    SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(orderData.deliveryDatePlan!!)!!
                )
                val splitDate = formattedDate.split(",").toTypedArray()
                val deliveryDateForm = splitDate[1] + " (${splitDate[0]})"
                view.findViewById<TextView>(R.id.tvDeliveryDate).text = String.format(
                    resources.getString(R.string.deliveryDate),
                    deliveryDateForm,
                    if (orderData.timeFrom.isNullOrEmpty() || orderData.timeTo.isNullOrEmpty()) "" else "С ${orderData.timeFrom}:00 до ${orderData.timeTo}:00"
                )
            }
            view.findViewById<TextView>(R.id.orderResultHeader).text = String.format(
                resources.getString(R.string.order_result_header),
                orderData.orderNumber,
                if (orderData.isCourier) "Курьерская доставка" else "Самовывоз"
            )
            view.findViewById<TextView>(R.id.tvOrderState).text =
                String.format(resources.getString(R.string.orderState), orderData.statusDescription)

            // Если самовывоз - показываем инфо по нему
            view.findViewById<ConstraintLayout>(R.id.clPS_Address).visibility = View.GONE
            if (!orderData.isCourier) {
                val formAddress = String.format(
                    resources.getString(R.string.addressDescription),
                    orderData.pvzAddress,
                    orderData.pvzAddressDop
                )
                val underlinedFormAddress = SpannableString(formAddress)
                underlinedFormAddress.setSpan(
                    UnderlineSpan(),
                    0,
                    SpannableString(formAddress).length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                val vAddress = view.findViewById<TextView>(R.id.tvAddress)
                vAddress.text = underlinedFormAddress
                if (!orderData.pvzAddress.isNullOrEmpty()) {
                    vAddress.setOnClickListener { mainActivity?.addressClick(orderData.pvzAddress!!) }
                }

                view.findViewById<TextView>(R.id.tvPaymentMode).text = String.format(
                    resources.getString(R.string.payment_mode),
                    "${if (orderData.canPayCash) "Оплата наличными" else ""} ${if (orderData.canPayCard) "или картой" else ""}"
                )
//                view.findViewById<TextView>(R.id.tvWorkDays).text =
//                    String.format(resources.getString(R.string.workDays), workDays)
//                view.findViewById<TextView>(R.id.tvWorkHours).text =
//                    String.format(resources.getString(R.string.workHours), workHours)
                view.findViewById<ConstraintLayout>(R.id.clPS_Address).visibility = View.VISIBLE
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            this.mainActivity = activity as IMainActivity
        } catch (e: ClassCastException) {
            throw ClassCastException("Activity $activity must implement IMainActivity")
        }
    }
}