package com.joyBox.shefaa.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.ToggleButton
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.entities.AutoCompleteUser
import com.joyBox.shefaa.listeners.OnAutoCompleteUserSelectListener
import com.joyBox.shefaa.viewHolders.UserViewHolder

class UserRecyclerViewAdapter constructor(var context: Context, var usersList: MutableList<AutoCompleteUser>
                                          , var onAutoCompleteUserSelectListener: OnAutoCompleteUserSelectListener? = null,
                                          var isClickable: Boolean = true) :
        RecyclerView.Adapter<UserViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.user_item_layout, parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val poJo = usersList[position]
        holder.bind(poJo)
        holder.itemView.findViewById<ToggleButton>(R.id.user_toggle)
                .setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener {
                    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                        if (!isClickable) {
                            holder.itemView.findViewById<ToggleButton>(R.id.user_toggle).isChecked = !isChecked
                            return
                        }
                        if (isChecked) {
                            poJo.selected = true
                            onAutoCompleteUserSelectListener?.onSelectAutoCompleteUser(poJo)
                        } else {
                            buttonView?.setOnCheckedChangeListener(null)
                            poJo.selected = false
                            buttonView?.isChecked = poJo.selected
                            buttonView?.setOnCheckedChangeListener(this)
                        }
                    }
                })

        holder.itemView.findViewById<View>(R.id.deleteBtn).setOnClickListener {
            usersList.removeAt(holder.adapterPosition)
            notifyItemRemoved(holder.adapterPosition)
        }
    }

    fun addUser(user: AutoCompleteUser) {
        usersList.add(user)
        notifyDataSetChanged()
    }

    fun getSelectedUsers(): MutableList<AutoCompleteUser> {
        val selectedList: MutableList<AutoCompleteUser> = mutableListOf()
        usersList.forEach {
            if (it.selected)
                selectedList.add(it)
        }
        return selectedList
    }

}