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
    protected List<T> dataList;

    public void setData(List<T> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    public void setData(List<T> datas,int start,int end){
        this.dataList = datas;
        notifyItemRangeChanged(start,end);
    }

    public void clearData() {
        if (dataList != null)
            dataList.clear();
        notifyDataSetChanged();
    }

    public void refreshData() {
        notifyDataSetChanged();
    }

    public void appendData(T data) {
        if (dataList == null) {
            dataList = new ArrayList<>();
        }
        dataList.add(data);
        notifyDataSetChanged();
    }

    public void appendData(List<T> appendList) {
        if (dataList == null) {
            dataList = new ArrayList<>();
        }
        this.dataList.addAll(appendList);
        notifyDataSetChanged();
    }

    public void appendData(int position, List<T> appendList) {
        if (dataList == null) {
            dataList = new ArrayList<>();
        }
        this.dataList.addAll(position, appendList);
        notifyDataSetChanged();
    }

    public T getData(int position) {
        try {
            return dataList.get(position);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<T> getDataList() {
        return dataList;
    }

    @Override
    public int getItemCount() {
        if (dataList == null) {
            return 0;
        }
        return dataList.size();
    }

    @Override
    public long getItemId(int position) {
        if (dataList == null) {
            return 0;
        }
        return position;
    }

    public void appendData(List<T> appendList, boolean isRefresh) {
        if (dataList == null) {
            dataList = new ArrayList<>();
        }
        if(isRefresh){
            dataList.clear();
        }
        this.dataList.addAll(appendList);
        notifyDataSetChanged();
    }

}

