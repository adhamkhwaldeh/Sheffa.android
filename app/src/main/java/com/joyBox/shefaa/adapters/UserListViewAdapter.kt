package com.joyBox.shefaa.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.entities.AutoCompleteUser
import com.joyBox.shefaa.listeners.OnAutoCompleteUserSelectListener
import java.util.ArrayList


class UserListViewAdapter constructor(
        context: Context,
        private var userAutoCompleteList: MutableList<AutoCompleteUser>
        , private val onUserSelectListener: OnAutoCompleteUserSelectListener) :
        ArrayAdapter<AutoCompleteUser>(context, R.layout.user_autocomplete_item,
                userAutoCompleteList), Filterable {

    private val originalList: MutableList<AutoCompleteUser> = userAutoCompleteList.toMutableList()


    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rowView = inflater.inflate(R.layout.doctor_autocomplete_item, parent, false)

        val textView = rowView.findViewById(R.id.doctorName) as TextView
        val name=(userAutoCompleteList[position].first_Name)+" "+(userAutoCompleteList[position].last_Name)
        textView.text = name

        rowView.setOnClickListener {
            onUserSelectListener.onSelectAutoCompleteUser(userAutoCompleteList[position])
        }

        return rowView
    }

    override fun getCount(): Int = userAutoCompleteList.size

    override fun getFilter() = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {

            val results = FilterResults()

            val list = originalList
            val count = list.size
            val resultList = ArrayList<AutoCompleteUser>(count)

            val filterString: String = constraint?.toString() ?: ""
            for (i in 0..(count - 1)) {
                val filterableString = list[i].first_Name.toLowerCase()
                val filterableString2 = list[i].last_Name.toLowerCase()
                if (filterableString.toLowerCase().contains(filterString.toLowerCase())) {
                    resultList.add(list[i])
                }else if (filterableString2.toLowerCase().contains(filterString.toLowerCase())) {
                    resultList.add(list[i])
                }
            }

            results.values = resultList
            results.count = resultList.size

            return results
        }

        @SuppressWarnings("unchecked")
        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            userAutoCompleteList = (results?.values
                    ?: mutableListOf<AutoCompleteUser>()) as MutableList<AutoCompleteUser>
            notifyDataSetChanged()
        }
    }


}