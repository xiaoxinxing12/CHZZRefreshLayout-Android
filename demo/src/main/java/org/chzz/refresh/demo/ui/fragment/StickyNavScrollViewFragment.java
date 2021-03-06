package org.chzz.refresh.demo.ui.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.chzz.refresh.CHZZRefreshLayout;
import org.chzz.refresh.demo.R;
import org.chzz.refresh.demo.ui.activity.MainActivity;
import org.chzz.refresh.demo.ui.activity.ViewPagerActivity;

/**
 * 作者:copy 邮件:2499551993@qq.com
 * 创建时间:15/9/27 下午12:53
 * 描述:
 */
public class StickyNavScrollViewFragment extends BaseFragment implements CHZZRefreshLayout.RefreshLayoutDelegate {
    private TextView mClickableLabelTv;
    
    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_scrollview_sticky_nav);
        mClickableLabelTv = getViewById(R.id.tv_scrollview_clickablelabel);
    }

    @Override
    protected void setListener() {
        getViewById(R.id.tv_scrollview_clickablelabel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("点击了测试文本");
            }
        });
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
    }

    @Override
    protected void onUserVisible() {
    }

    @Override
    public void onRefreshLayoutBeginRefreshing(CHZZRefreshLayout refreshLayout) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected void onPreExecute() {
                showLoadingDialog();
            }

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
                dismissLoadingDialog();
                ((ViewPagerActivity) getActivity()).endRefreshing();
                mClickableLabelTv.setText("加载最新数据完成");
            }
        }.execute();
    }

    @Override
    public boolean onRefreshLayoutBeginLoadingMore(CHZZRefreshLayout refreshLayout) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected void onPreExecute() {
                showLoadingDialog();
            }

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
                dismissLoadingDialog();
                ((ViewPagerActivity) getActivity()).endLoadingMore();
                Log.i(TAG, "上拉加载更多完成");
            }
        }.execute();
        return true;
    }
}
