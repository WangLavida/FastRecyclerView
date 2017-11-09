package com.wolf.fastrecyclerview;

import android.Manifest;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.wolf.fastlibrary.FastLoadMore;
import com.wolf.fastlibrary.FastSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * Created by Wolf on 2017/11/9
 */

public class MainActivity extends AppCompatActivity {
    private FastSwipeRefreshLayout fastSwipeRefreshLayout;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipe;
    private WxAdapter wxAdapter;
    private List<WxBean.ResultBean.ListBean> listBean = new ArrayList<WxBean.ResultBean.ListBean>();
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        getWx();
    }

    private void getWx() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("key", UrlList.APPKEY);
        httpParams.put("cid", "13");
        httpParams.put("page", page);
        httpParams.put("size", UrlList.SIZE);
        OkGo.<String>get(UrlList.SEARCH).params(httpParams).execute(new StringCallback() {
            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                fastSwipeRefreshLayout.setLoad(false);
            }

            @Override
            public void onSuccess(Response<String> response) {
                Gson gson = new Gson();
                WxBean wxBean = gson.fromJson(response.body(), WxBean.class);
                if (page == 1) {
                    listBean.clear();
                }
                fastSwipeRefreshLayout.setLoad(false);
                if (wxBean.getResult().getList().size() != 0) {
                    listBean.addAll(wxBean.getResult().getList());
                    wxAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void initView() {
        fastSwipeRefreshLayout = (FastSwipeRefreshLayout) findViewById(R.id.fast_layout);
        wxAdapter = new WxAdapter(listBean);
        fastSwipeRefreshLayout.init(new LinearLayoutManager(this), wxAdapter, new FastLoadMore() {
            @Override
            public void refresh() {
                page = 1;
                getWx();
            }

            @Override
            public void loadMore() {
                page = page + 1;
                getWx();
            }
        });

    }
}
