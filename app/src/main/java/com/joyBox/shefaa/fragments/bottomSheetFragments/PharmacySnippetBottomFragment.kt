package com.joyBox.shefaa.fragments.bottomSheetFragments

import android.app.Dialog
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.BottomSheetDialog
import android.support.design.widget.BottomSheetDialogFragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import butterknife.BindView
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.adapters.DoctorRecyclerViewAdapter
import com.joyBox.shefaa.adapters.PharmacyRecyclerViewAdapter
import com.joyBox.shefaa.entities.Doctor
import com.joyBox.shefaa.entities.Pharmacy

/**
 * Created by Adhamkh on 2018-11-29.
 */
class PharmacySnippetBottomFragment : BottomSheetDialogFragment() {

    lateinit var pharmacy: Pharmacy


    companion object {

        const val PharmacySnippetBottomFragment_Tag = "PharmacySnippetBottomFragment_Tag"

        fun getNewInstance(pharmacy: Pharmacy): PharmacySnippetBottomFragment {
            val pharmacySnippetBottomFragment = PharmacySnippetBottomFragment()
            pharmacySnippetBottomFragment.pharmacy = pharmacy
            val bundle = Bundle()
            pharmacySnippetBottomFragment.arguments = bundle
            return pharmacySnippetBottomFragment
        }

    }

    @BindView(R.id.recyclerView)
    lateinit var recyclerView: RecyclerView

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(activity)
        val list: MutableList<Pharmacy> = mutableListOf()
        list.add(pharmacy)
        recyclerView.adapter = PharmacyRecyclerViewAdapter(context!!, list)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.TransparentBottomSheetDialogTheme)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.pharmacy_snippet_layout, container, false)
        ButterKnife.bind(this, view)
        val displayMetrics = DisplayMetrics()
        activity?.windowManager?.defaultDisplay?.getMetrics(displayMetrics)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog.window.attributes.windowAnimations = R.style.BottomSheetAnimation
        initRecyclerView()

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        dialog.setOnShowListener { dialog ->
            val d = dialog as BottomSheetDialog
            val bottomSheet = d.findViewById<FrameLayout>(android.support.design.R.id.design_bottom_sheet)
            BottomSheetBehavior.from(bottomSheet).state = BottomSheetBehavior.STATE_EXPANDED
        }
        dialog.window.setBackgroundDrawableResource(android.R.color.transparent)
        return dialog
    }


}