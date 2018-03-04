package com.wangw.tvbox.module;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


/**
 * 可以使用任何类型的数据,作为{@link BaseAdapter}的扩展
 *
 * @param <V> ViewHolder
 * @param <T> 数据模型
 */
public abstract class BaseAdapter<V extends RecyclerView.ViewHolder, T> extends RecyclerView.Adapter<V> {
    protected List<T> mDataList = new ArrayList<>();

    public void setData(List<T> dataList) {
        this.mDataList.clear();
        mDataList.addAll(dataList);
        notifyItemRangeChanged(0,dataList.size());
//        notifyDataSetChanged();
    }

    public void setData(List<T> datas,int start,int end){
        this.mDataList = datas;
        notifyItemRangeChanged(start,end);
    }

    public void clearData() {
        if (mDataList != null)
            mDataList.clear();
        notifyDataSetChanged();
    }

    public void refreshData() {
        notifyDataSetChanged();
    }

    public void appendData(T data) {
        if (mDataList == null) {
            mDataList = new ArrayList<>();
        }
        mDataList.add(data);
        notifyDataSetChanged();
    }

    public void appendData(List<T> appendList) {
        if (mDataList == null) {
            mDataList = new ArrayList<>();
        }
        this.mDataList.addAll(appendList);
        notifyDataSetChanged();
    }

    public void appendData(int position, List<T> appendList) {
        if (mDataList == null) {
            mDataList = new ArrayList<>();
        }
        this.mDataList.addAll(position, appendList);
        notifyDataSetChanged();
    }

    public T getData(int position) {
        try {
            return mDataList.get(position);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<T> getDataList() {
        return mDataList;
    }

    @Override
    public int getItemCount() {
        if (mDataList == null) {
            return 0;
        }
        return mDataList.size();
    }

    @Override
    public long getItemId(int position) {
        if (mDataList == null) {
            return 0;
        }
        return position;
    }

    public void appendData(List<T> appendList, boolean isRefresh) {
        if (mDataList == null) {
            mDataList = new ArrayList<>();
        }
        if(isRefresh){
            mDataList.clear();
        }
        this.mDataList.addAll(appendList);
        notifyDataSetChanged();
    }

}

