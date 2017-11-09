package com.wolf.fastrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wolf.fastlibrary.FastAdapter;
import com.wolf.fastlibrary.FooterViewHolder;

import java.util.List;

/**
 * Created by Wolf on 2017/11/9
 */


public class WxAdapter extends FastAdapter<WxBean.ResultBean.ListBean> {
    private List<WxBean.ResultBean.ListBean> listBean;

    public WxAdapter(List<WxBean.ResultBean.ListBean> listBean) {
        this.listBean = listBean;
    }


    @Override
    public List<WxBean.ResultBean.ListBean> getData() {
        return listBean;
    }

    @Override
    public RecyclerView.ViewHolder getViewHolder(Context context, ViewGroup parent) {
        View v1 = LayoutInflater.from(context).inflate(R.layout.wx_item, parent, false);
        WxViewHolder viewHolder1 = new WxViewHolder(v1);
        return viewHolder1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof WxViewHolder) {
            WxViewHolder wxViewHolder = (WxViewHolder) holder;
            wxViewHolder.titleText.setText(listBean.get(position).getTitle());
        }

    }


    @Override
    public int getItemCount() {
        return listBean.size();
    }

    public static class WxViewHolder extends RecyclerView.ViewHolder {
        TextView titleText;

        public WxViewHolder(View itemView) {
            super(itemView);
            titleText = (TextView) itemView.findViewById(R.id.title_text);
        }
    }
}
