package com.wolf.fastlibrary;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

/**
 * Created by Wolf on 2017/11/9
 */


public abstract class FastAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public final static int TYPE_FOOTER = 2;//底部
    public final static int TYPE_NORMAL = 1; // 正常

    /**
     * 获取列表数据
     *
     * @return
     */
    public abstract List<T> getData();

    /**
     * 根据TYPE判断底部
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        T bean = getData().get(position);
        if (bean == null) {
            return TYPE_FOOTER;
        } else {
            return TYPE_NORMAL;
        }
    }

    /**
     * 获取正常显示ViewHolder
     *
     * @param context
     * @param parent
     * @return
     */
    public abstract RecyclerView.ViewHolder getViewHolder(Context context, ViewGroup parent);

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_FOOTER:
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.load_layout, parent, false);
                FooterViewHolder viewHolder = new FooterViewHolder(v);
                return viewHolder;
            case TYPE_NORMAL:
                return getViewHolder(parent.getContext(), parent);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
