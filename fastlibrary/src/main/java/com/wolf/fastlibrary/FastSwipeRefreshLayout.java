package com.wolf.fastlibrary;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
  Created by Wolf on 2017/11/9
 */


public class FastSwipeRefreshLayout extends LinearLayout {
    private SwipeRefreshLayout fastRefresh;
    private RecyclerView fastRecycler;
    private int lastVisibleItem;
    public boolean isLoad = false;
    public FastAdapter fastAdapter;

    public FastSwipeRefreshLayout(Context context) {
        super(context);
    }

    public FastSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 加载布局
        LayoutInflater.from(context).inflate(R.layout.fast_swipe_layout, this);
        fastRefresh = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        fastRecycler = (RecyclerView) findViewById(R.id.recyclerView);

    }


    /**
     * 隐藏刷新效果
     *
     * @param loadType
     */
    public void setLoad(boolean loadType) {
        fastRefresh.setRefreshing(false);
        if (!loadType) {
            if (isLoad) {
                fastAdapter.getData().remove(fastAdapter.getItemCount() - 1);
                isLoad = false;
            }
        }
    }

    /**
     * @param layout
     * @param adapter
     */
    public void init(final RecyclerView.LayoutManager layout, FastAdapter adapter, final FastLoadMore fastLoadMore) {
        fastAdapter = adapter;
        fastRecycler.setLayoutManager(layout);
        fastRecycler.setAdapter(fastAdapter);
//        上拉加载更多
        fastRecycler.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == fastAdapter.getItemCount()) {
//                    滑动到底部
                    if (!isLoad) {
                        isLoad = true;
                        fastLoadMore.loadMore();
                        fastAdapter.getData().add(null);
                        fastAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = ((LinearLayoutManager) fastRecycler.getLayoutManager()).findLastVisibleItemPosition();

            }
        });
//        下拉刷新
        fastRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fastLoadMore.refresh();
            }
        });
    }
}
