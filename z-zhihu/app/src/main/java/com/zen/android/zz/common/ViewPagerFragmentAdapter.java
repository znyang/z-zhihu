package com.zen.android.zz.common;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zen yang - 2016/12/8
 */
public class ViewPagerFragmentAdapter extends FragmentPagerAdapter {

    private static final String TAG = "adapter";

    public interface PageProvider {
        Fragment buildFragment();
    }

    public ViewPagerFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    private List<PageProvider> mPageProviders = new ArrayList<>();

    public ViewPagerFragmentAdapter append(PageProvider provider) {
        mPageProviders.add(provider);
        return this;
    }

    public void bind(ViewPager viewPager) {
        if (viewPager == null) {
            Log.w(TAG, "bind ViewPager fail: viewPager is Null");
            return;
        }
        viewPager.setAdapter(this);
    }

    @Override
    public Fragment getItem(int position) {
        return mPageProviders.get(position).buildFragment();
    }

    @Override
    public int getCount() {
        return mPageProviders.size();
    }
}
