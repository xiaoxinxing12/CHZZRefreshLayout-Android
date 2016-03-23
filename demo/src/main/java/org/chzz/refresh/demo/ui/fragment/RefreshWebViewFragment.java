package org.chzz.refresh.demo.ui.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.chzz.refresh.CHZZMoocStyleRefreshViewHolder;
import org.chzz.refresh.CHZZRefreshLayout;

import org.chzz.refresh.demo.R;

/**
 * 作者:copy 邮件:2499551993@qq.com
 * 创建时间:15/7/21 下午11:42
 * 描述:
 */
public class RefreshWebViewFragment extends BaseFragment implements CHZZRefreshLayout.RefreshLayoutDelegate {
    private static final String TAG = RefreshWebViewFragment.class.getSimpleName();
    private CHZZRefreshLayout mRefreshLayout;
    private WebView mContentWv;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_webview_refresh);
        mRefreshLayout = getViewById(R.id.rl_webview_refresh);
        mContentWv = getViewById(R.id.wv_webview_content);
    }

    @Override
    protected void setListener() {
        mRefreshLayout.setDelegate(this);
        mContentWv.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mRefreshLayout.endRefreshing();
            }
        });
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        CHZZMoocStyleRefreshViewHolder moocStyleRefreshViewHolder = new CHZZMoocStyleRefreshViewHolder(mApp, false);
        moocStyleRefreshViewHolder.setOriginalImage(R.mipmap.bga_refresh_moooc);
        moocStyleRefreshViewHolder.setUltimateColor(R.color.imoocstyle);
        mRefreshLayout.setRefreshViewHolder(moocStyleRefreshViewHolder);
//        mRefreshLayout.setCustomHeaderView(DataEngine.getCustomHeaderView(mApp), true);
        mContentWv.getSettings().setJavaScriptEnabled(true);
        mContentWv.loadUrl("https://github.com/bingoogolapple");
    }

    @Override
    protected void onUserVisible() {
    }

    @Override
    public void onRefreshLayoutBeginRefreshing(CHZZRefreshLayout refreshLayout) {
        mContentWv.reload();
    }

    @Override
    public boolean onRefreshLayoutBeginLoadingMore(CHZZRefreshLayout refreshLayout) {
        Log.i(TAG, "加载更多");
        return false;
    }
}