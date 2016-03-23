package org.chzz.refresh.demo.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import org.chzz.refresh.demo.R;
import org.chzz.refresh.demo.ui.fragment.RefreshGridViewFragment;
import org.chzz.refresh.demo.ui.fragment.RefreshListViewFragment;
import org.chzz.refresh.demo.ui.fragment.RefreshNormalViewFragment;
import org.chzz.refresh.demo.ui.fragment.RefreshRecyclerViewFragment;
import org.chzz.refresh.demo.ui.fragment.RefreshScrollViewFragment;
import org.chzz.refresh.demo.ui.fragment.RefreshStaggeredRecyclerViewFragment;
import org.chzz.refresh.demo.ui.fragment.RefreshSwipeListViewFragment;
import org.chzz.refresh.demo.ui.fragment.RefreshSwipeRecyclerViewFragment;
import org.chzz.refresh.demo.ui.fragment.RefreshWebViewFragment;

import cn.bingoogolapple.bgabanner.BGAViewPager;

/**
 * 作者:copy 邮件:2499551993@qq.com
 * 创建时间:15/7/10 14:11
 * 描述:
 */
public class MainActivity extends BaseActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    public static final int LOADING_DURATION = 2000;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationView mNavigationView;
    private Toolbar mToolbar;
    private BGAViewPager mViewPager;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        mDrawerLayout = getViewById(R.id.drawerLayout);
        mNavigationView = getViewById(R.id.navigationView);
        mToolbar = getViewById(R.id.toolbar);
        mViewPager = getViewById(R.id.viewPager);
    }

    @Override
    protected void setListener() {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                hideDrawer();
                if (menuItem.getItemId() == R.id.navigation_main_stickyNav) {
                    startActivity(new Intent(MainActivity.this, StickyNavActivity.class));
                } else {
                    setTitle(menuItem.getTitle());
                    switch (menuItem.getItemId()) {
                        case R.id.navigation_main_gridview:
                            mViewPager.setCurrentItem(0, false);
                            break;
                        case R.id.navigation_main_normallistview:
                            mViewPager.setCurrentItem(1, false);
                            break;
                        case R.id.navigation_main_normalrecyclerview:
                            mViewPager.setCurrentItem(2, false);
                            break;
                        case R.id.navigation_main_swipelistview:
                            mViewPager.setCurrentItem(3, false);
                            break;
                        case R.id.navigation_main_swiperecyclerview:
                            mViewPager.setCurrentItem(4, false);
                            break;
                        case R.id.navigation_main_staggeredgridlayoutmanager:
                            mViewPager.setCurrentItem(5, false);
                            break;
                        case R.id.navigation_main_scrollview:
                            mViewPager.setCurrentItem(6, false);
                            break;
                        case R.id.navigation_main_normalview:
                            mViewPager.setCurrentItem(7, false);
                            break;
                        case R.id.navigation_main_webview:
                            mViewPager.setCurrentItem(8, false);
                            break;
                        default:
                            break;
                    }
                }
                return true;
            }
        });
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        setSupportActionBar(mToolbar);
        setTitle(R.string.gridview_demo);

        setUpNavDrawer();
        setUpViewPager();
    }

    private void setUpNavDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this,
                mDrawerLayout,
                mToolbar,
                R.string.app_name,
                R.string.app_name);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }

    private void setUpViewPager() {
        mViewPager.setAllowUserScrollable(false);
        mViewPager.setAdapter(new ContentViewPagerAdapter(getSupportFragmentManager(), this));
    }


    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            hideDrawer();
        } else {
            super.onBackPressed();
        }
    }

    private void hideDrawer() {
        mDrawerLayout.closeDrawer(GravityCompat.START);
    }

    private static class ContentViewPagerAdapter extends FragmentPagerAdapter {
        private Class[] mFragments = new Class[]{RefreshGridViewFragment.class, RefreshListViewFragment.class, RefreshRecyclerViewFragment.class, RefreshSwipeListViewFragment.class, RefreshSwipeRecyclerViewFragment.class, RefreshStaggeredRecyclerViewFragment.class, RefreshScrollViewFragment.class, RefreshNormalViewFragment.class, RefreshWebViewFragment.class};
        private Context mContext;

        public ContentViewPagerAdapter(FragmentManager fm, Context context) {
            super(fm);
            mContext = context;
        }

        @Override
        public Fragment getItem(int position) {
            return Fragment.instantiate(mContext, mFragments[position].getName());
        }

        @Override
        public int getCount() {
            return mFragments.length;
        }
    }

}