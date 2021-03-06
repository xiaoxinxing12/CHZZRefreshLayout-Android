package org.chzz.refresh.demo.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import org.chzz.refresh.CHZZNormalRefreshViewHolder;
import org.chzz.refresh.CHZZRefreshLayout;
import org.chzz.refresh.demo.R;
import org.chzz.refresh.demo.model.BannerModel;
import org.chzz.refresh.demo.ui.fragment.StickyNavListViewFragment;
import org.chzz.refresh.demo.ui.fragment.StickyNavRecyclerViewFragment;
import org.chzz.refresh.demo.ui.fragment.StickyNavScrollViewFragment;
import org.chzz.refresh.demo.ui.fragment.StickyNavWebViewFragment;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;
import cn.bingoogolapple.bgaindicator.BGAFixedIndicator;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewPagerActivity extends BaseActivity implements CHZZRefreshLayout.RefreshLayoutDelegate {
    private CHZZRefreshLayout mRefreshLayout;
    private BGABanner mBanner;
    private BGAFixedIndicator mIndicator;
    private ViewPager mContentVp;

    private Fragment[] mFragments;
    private String[] mTitles;
    private StickyNavRecyclerViewFragment mRecyclerViewFragment;
    private StickyNavListViewFragment mListViewFragment;
    private StickyNavScrollViewFragment mScrollViewFragment;
    private StickyNavWebViewFragment mWebViewFragment;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_viewpager);
        mRefreshLayout = getViewById(R.id.refreshLayout);
        mBanner = getViewById(R.id.banner);
        mIndicator = getViewById(R.id.indicator);
        mContentVp = getViewById(R.id.vp_viewpager_content);
    }

    @Override
    protected void setListener() {
        mRefreshLayout.setDelegate(this);
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        mRefreshLayout.setRefreshViewHolder(new CHZZNormalRefreshViewHolder(mApp, true));

        initBanner();

        mFragments = new Fragment[4];
        mFragments[0] = mRecyclerViewFragment = new StickyNavRecyclerViewFragment();
        mFragments[1] = mListViewFragment = new StickyNavListViewFragment();
        mFragments[2] = mScrollViewFragment = new StickyNavScrollViewFragment();
        mFragments[3] = mWebViewFragment = new StickyNavWebViewFragment();

        mTitles = new String[4];
        mTitles[0] = "RecyclerView";
        mTitles[1] = "ListView";
        mTitles[2] = "ScrollView";
        mTitles[3] = "WebView";
        mContentVp.setAdapter(new ContentViewPagerAdapter(getSupportFragmentManager()));
        mIndicator.initData(0, mContentVp);
    }

    private void initBanner() {
        final List<View> views = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            views.add(View.inflate(this, R.layout.view_image, null));
        }
        mBanner.setViews(views);
        mEngine.getBannerModel().enqueue(new Callback<BannerModel>() {
            @Override
            public void onResponse(Response<BannerModel> response) {
                BannerModel bannerModel = response.body();
                for (int i = 0; i < views.size(); i++) {
                    Glide.with(ViewPagerActivity.this).load(bannerModel.imgs.get(i)).placeholder(R.mipmap.holder).error(R.mipmap.holder).dontAnimate().thumbnail(0.1f).into((ImageView) views.get(i));
                }
                mBanner.setTips(bannerModel.tips);
            }

            @Override
            public void onFailure(Throwable t) {
            }
        });
    }

    @Override
    public void onRefreshLayoutBeginRefreshing(CHZZRefreshLayout refreshLayout) {
        switch (mContentVp.getCurrentItem()) {
            case 0:
                mRecyclerViewFragment.onRefreshLayoutBeginRefreshing(refreshLayout);
                break;
            case 1:
                mListViewFragment.onRefreshLayoutBeginRefreshing(refreshLayout);
                break;
            case 2:
                mScrollViewFragment.onRefreshLayoutBeginRefreshing(refreshLayout);
                break;
            case 3:
                mWebViewFragment.onRefreshLayoutBeginRefreshing(refreshLayout);
                break;
        }
    }

    @Override
    public boolean onRefreshLayoutBeginLoadingMore(CHZZRefreshLayout refreshLayout) {
        switch (mContentVp.getCurrentItem()) {
            case 0:
                return mRecyclerViewFragment.onRefreshLayoutBeginLoadingMore(refreshLayout);
            case 1:
                return mListViewFragment.onRefreshLayoutBeginLoadingMore(refreshLayout);
            case 2:
                return mScrollViewFragment.onRefreshLayoutBeginLoadingMore(refreshLayout);
            case 3:
                return mWebViewFragment.onRefreshLayoutBeginLoadingMore(refreshLayout);
            default:
                return false;
        }
    }

    public void endRefreshing() {
        mRefreshLayout.endRefreshing();
    }

    public void endLoadingMore() {
        mRefreshLayout.endLoadingMore();
    }

    class ContentViewPagerAdapter extends FragmentPagerAdapter {

        public ContentViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mTitles.length;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments[position];
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }
    }

}