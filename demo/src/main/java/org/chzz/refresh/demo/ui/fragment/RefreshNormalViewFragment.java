package org.chzz.refresh.demo.ui.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.chzz.refresh.demo.engine.DataEngine;
import org.chzz.refresh.CHZZRefreshLayout;
import org.chzz.refresh.CHZZStickinessRefreshViewHolder;

import org.chzz.refresh.demo.R;

import org.chzz.refresh.demo.ui.activity.MainActivity;

/**
 * 作者:copy 邮件:2499551993@qq.com
 * 创建时间:15/5/21 上午1:22
 * 描述:
 */
public class RefreshNormalViewFragment extends BaseFragment implements CHZZRefreshLayout.BGARefreshLayoutDelegate {
    private static final String TAG = RefreshNormalViewFragment.class.getSimpleName();
    private CHZZRefreshLayout mRefreshLayout;
    private TextView mClickableLabelTv;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_normalview_refresh);
        mRefreshLayout = getViewById(R.id.rl_normalview_refresh);
        mClickableLabelTv = getViewById(R.id.tv_normalview_clickablelabel);
    }

    @Override
    protected void setListener() {
        mRefreshLayout.setDelegate(this);

        mClickableLabelTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("点击了测试文本");
            }
        });
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        CHZZStickinessRefreshViewHolder stickinessRefreshViewHolder = new CHZZStickinessRefreshViewHolder(mApp, true);
        stickinessRefreshViewHolder.setStickinessColor(R.color.colorPrimary);
        stickinessRefreshViewHolder.setRotateImage(R.mipmap.bga_refresh_stickiness);
        mRefreshLayout.setRefreshViewHolder(stickinessRefreshViewHolder);

        mRefreshLayout.setCustomHeaderView(DataEngine.getCustomHeaderView(mApp), false);
    }

    @Override
    protected void onUserVisible() {

    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(CHZZRefreshLayout refreshLayout) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    Thread.sleep(MainActivity.LOADING_DURATION);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                mRefreshLayout.endRefreshing();
                mClickableLabelTv.setText("加载最新数据完成");
            }
        }.execute();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(CHZZRefreshLayout refreshLayout) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    Thread.sleep(MainActivity.LOADING_DURATION);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                mRefreshLayout.endLoadingMore();
                Log.i(TAG, "上拉加载更多完成");
            }
        }.execute();
        return true;
    }
}