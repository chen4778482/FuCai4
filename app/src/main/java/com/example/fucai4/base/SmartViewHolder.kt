package com.example.sfjdpt.adapter

import android.content.res.Resources
import android.support.annotation.StringRes
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.util.TypedValue
import android.view.View
import android.widget.*


open class SmartViewHolder(itemView: View, private val mListener: AdapterView.OnItemClickListener?) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
    private var mPosition = -1

    init {
        itemView.setOnClickListener(this)
        /*
         * 设置水波纹背景
         */
        if (itemView.background == null) {
            val typedValue = TypedValue()
            val theme = itemView.context.theme
            val top = itemView.paddingTop
            val bottom = itemView.paddingBottom
            val left = itemView.paddingLeft
            val right = itemView.paddingRight
            if (theme.resolveAttribute(android.R.attr.selectableItemBackground, typedValue, true)) {
                itemView.setBackgroundResource(typedValue.resourceId)
            }
            itemView.setPadding(left, top, right, bottom)
        }
    }

    fun setPosition(position: Int) {
        mPosition = position
    }

    override fun onClick(v: View) {
        if (mListener != null) {
            val position = adapterPosition
            if (position >= 0) {
                mListener.onItemClick(null, v, position, itemId)
            } else if (mPosition > -1) {
                mListener.onItemClick(null, v, mPosition, itemId)
            }
        }
    }

    fun findViewById(id: Int): View {
        return if (id == 0) itemView else itemView.findViewById(id)
    }

    fun textEd(id: Int, sequence: String): SmartViewHolder {
        val view = findViewById(id)
        if (view is EditText) {
            view.setText(sequence)
        }
        return this
    }

    fun text(id: Int,  stringRes: String): SmartViewHolder {
        val view = findViewById(id)
        if (view is TextView) {
            view.text = stringRes
        }
        return this
    }

    fun text1(id: Int,  stringRes: String): View {
        val view = findViewById(id)
        if (view is TextView) {
            view.text = stringRes
        }
        return view
    }

    fun textColorId(id: Int, colorId: Int): SmartViewHolder {
        val view = findViewById(id)
        if (view is TextView) {
            view.setTextColor(ContextCompat.getColor(view.getContext(), colorId))
        }
        return this
    }

    fun textColorbg(id: Int, colorId: Int): SmartViewHolder {
        val view = findViewById(id)
        if (view is TextView) {
            view.setBackgroundColor(ContextCompat.getColor(view.getContext(), colorId))
        }
        return this
    }


    fun text_line(id: Int, line: Int): SmartViewHolder {
        val view = findViewById(id)
        if (view is TextView) {
            view.maxLines = line
        }
        return this
    }

    fun image(id: Int, imageId: Int): SmartViewHolder {
        val view = findViewById(id)
        if (view is ImageView) {
            view.setImageResource(imageId)
//            Glide.with(view.context).load(imageId).into(view)
        }
        return this
    }

    fun textVisibility(id: Int, imageId: Int): SmartViewHolder {
        val view = findViewById(id)
        if (view is View) {
            view.visibility = imageId
        }
        return this
    }

    fun layout_marginLeft(id: Int, leftMargin: Int): SmartViewHolder {
        val view = findViewById(id)
        if (view is View) {
            val paramTest = view.layoutParams as LinearLayout.LayoutParams
            paramTest.leftMargin = leftMargin
            view.layoutParams = paramTest
        }
        return this
    }

    fun setPadding(id: Int, leftMargin: Int): SmartViewHolder {
        val view = findViewById(id)
        if (view is View) {
//            val paramTest = view.layoutParams as LinearLayout.LayoutParams
//            paramTest.leftMargin = leftMargin
//            paramTest.topMargin = leftMargin
//            paramTest.rightMargin = leftMargin
//            paramTest.bottomMargin = leftMargin
//            view.layoutParams = paramTest
            view.setPadding(leftMargin * 2, leftMargin, leftMargin, leftMargin)
        }
        return this
    }

    fun setLayoutParams(id: Int, leftMargin: Int): SmartViewHolder {
        val view = findViewById(id)
        if (view is View) {
            var p = view.layoutParams;
            p.height = leftMargin
            view.layoutParams = p;
        }
        return this
    }
}