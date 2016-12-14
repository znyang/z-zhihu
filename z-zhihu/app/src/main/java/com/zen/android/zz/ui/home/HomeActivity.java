package com.zen.android.zz.ui.home;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.zen.android.zz.R;
import com.zen.android.zz.common.ViewPagerFragmentAdapter;
import com.zen.android.zz.ui.news.NewsFragment;
import com.zen.android.zz.ui.theme.ThemeFragment;

//import butterknife.BindView;
//import butterknife.ButterKnife;
import easymvp.annotation.ActivityView;

@ActivityView(layout = R.layout.activity_home, presenter = HomePresenter.class)
public class HomeActivity extends AppCompatActivity implements HomeView {

    //    @BindView(R.id.vp_home)
    ViewPager            mVpHome;
    //    @BindView(R.id.bnv_home)
    BottomNavigationView mBnvHome;

    private static final Integer[] MENU_IDS = new Integer[]{R.id.action_news, R.id.action_theme};

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_home);
//    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
//        ButterKnife.bind(this);
        mVpHome = (ViewPager) findViewById(R.id.vp_home);
        mBnvHome = (BottomNavigationView) findViewById(R.id.bnv_home);

        Log.d("Home", "setContentView finish");
    }

    @Override
    protected void onStart() {
        super.onStart();
        setupPages();
    }

    private void setupPages() {
        new ViewPagerFragmentAdapter(getSupportFragmentManager())
                .append(NewsFragment::newInstance)
                .append(ThemeFragment::newInstance)
                .bind(mVpHome);

        mVpHome.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            private MenuItem mPrevItem;

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (mPrevItem != null) {
                    mPrevItem.setChecked(false);
                }
                mPrevItem = mBnvHome.getMenu().getItem(position);
                mPrevItem.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mBnvHome.setOnNavigationItemSelectedListener(item -> {
            for (int i = MENU_IDS.length; --i >= 0; ) {
                if (MENU_IDS[i] == item.getItemId()) {
                    mVpHome.setCurrentItem(i);
                    return true;
                }
            }
            return false;
        });
    }
}
