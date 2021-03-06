package com.example.sfjdpt.adapter

import android.database.DataSetObservable
import android.database.DataSetObserver
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListAdapter

import java.util.ArrayList

/**
 *
 * Created by SCWANG on 2017/6/11.
 */

abstract class BaseRecyclerAdapter<T> : RecyclerView.Adapter<SmartViewHolder>, ListAdapter {


    //<editor-fold desc="BaseRecyclerAdapter">

    private val mLayoutId: Int
    private val mList: MutableList<T>
    private var mLastPosition = -1
    private var mOpenAnimationEnable = true
    private var mListener: AdapterView.OnItemClickListener? = null
    //</editor-fold>

    //<editor-fold desc="ListAdapter">
    private val mDataSetObservable = DataSetObservable()

    constructor(@LayoutRes layoutId: Int) {
        setHasStableIds(false)
        this.mList = ArrayList()
        this.mLayoutId = layoutId
    }

    constructor(collection: Collection<T>, @LayoutRes layoutId:Int) {
        setHasStableIds(false)
        this.mList = ArrayList(collection)
        this.mLayoutId = layoutId
    }

    constructor(collection: Collection<T>, @LayoutRes layoutId: Int, listener: AdapterView.OnItemClickListener) {
        setHasStableIds(false)
        setOnItemClickListener(listener)
        this.mList = ArrayList(collection)
        this.mLayoutId = layoutId
    }
    //</editor-fold>


    private fun addAnimate(holder: SmartViewHolder?, postion: Int) {
        if (mOpenAnimationEnable && mLastPosition < postion) {
            holder!!.itemView.alpha = 0f
            holder.itemView.animate().alpha(1f).start()
            mLastPosition = postion
        }
    }

    //<editor-fold desc="RecyclerAdapter">
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SmartViewHolder {
        return SmartViewHolder(LayoutInflater.from(parent.context).inflate(mLayoutId, parent, false), mListener)
    }

    override fun onBindViewHolder(holder: SmartViewHolder, position: Int) {
        onBindViewHolder(holder, if (position < mList.size) mList[position] else null, position)
    }

    protected abstract fun onBindViewHolder(holder: SmartViewHolder, model: T?, position: Int)

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onViewAttachedToWindow(holder: SmartViewHolder?) {
        super.onViewAttachedToWindow(holder)
        addAnimate(holder, holder!!.layoutPosition)
    }

    fun setOpenAnimationEnable(enabled: Boolean) {
        this.mOpenAnimationEnable = enabled
    }

    //</editor-fold>

    //<editor-fold desc="API">

    fun setOnItemClickListener(listener: AdapterView.OnItemClickListener): BaseRecyclerAdapter<T> {
        mListener = listener
        return this
    }

    fun refresh(collection: Collection<T>): BaseRecyclerAdapter<T> {
        mList.clear()
        mList.addAll(collection)
        notifyDataSetChanged()
        notifyListDataSetChanged()
        mLastPosition = -1
        return this
    }

    fun loadMore(collection: Collection<T>): BaseRecyclerAdapter<T> {
        mList.addAll(collection)
        notifyDataSetChanged()
        notifyListDataSetChanged()
        return this
    }

    //    public boolean hasStableIds() {
    //        return false;
    //    }

    override fun registerDataSetObserver(observer: DataSetObserver) {
        mDataSetObservable.registerObserver(observer)
    }

    override fun unregisterDataSetObserver(observer: DataSetObserver) {
        mDataSetObservable.unregisterObserver(observer)
    }

    /**
     * Notifies the attached observers that the underlying data has been changed
     * and any View reflecting the data set should refresh itself.
     */
    fun notifyListDataSetChanged() {
        mDataSetObservable.notifyChanged()
    }

    /**
     * Notifies the attached observers that the underlying data is no longer valid
     * or available. Once invoked this adapter is no longer valid and should
     * not report further data set changes.
     */
    fun notifyDataSetInvalidated() {
        mDataSetObservable.notifyInvalidated()
    }

    override fun areAllItemsEnabled(): Boolean {
        return true
    }

    override fun isEnabled(position: Int): Boolean {
        return true
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val holder: SmartViewHolder
        if (convertView != null) {
            holder = convertView.tag as SmartViewHolder
        } else {
            holder = onCreateViewHolder(parent, getItemViewType(position))
            convertView = holder.itemView
            convertView!!.tag = holder
        }
        holder.position = position
        onBindViewHolder(holder, getItemViewType(position))
        addAnimate(holder, position)
        return convertView
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getViewTypeCount(): Int {
        return 1
    }

    override fun isEmpty(): Boolean {
        return count == 0
    }

    override fun getItem(position: Int): T {
        return mList[position]
    }

    override fun getCount(): Int {
        return mList.size
    }

    //</editor-fold>
}
