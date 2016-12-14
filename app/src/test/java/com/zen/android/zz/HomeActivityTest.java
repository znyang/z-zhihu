package com.zen.android.zz;

import com.zen.android.puck.runner.PuckTestRunner;
import com.zen.android.zz.ui.home.HomeActivity;
//import com.zen.android.zz.ui.home.HomeActivity_ViewBinding;
import com.zen.android.zz.ui.home.HomeActivity_ViewDelegate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.annotation.Config;

//import butterknife.ButterKnife;

/**
 * @author zen yang - 2016/12/11
 */
@RunWith(PuckTestRunner.class)
@Config(constants = BuildConfig.class, sdk = IConfig.SDK_VERSION)
public class HomeActivityTest {

    HomeActivity mActivity;

    @Before
    public void setUp() throws Exception {
//        mActivity = Robolectric.setupActivity(HomeActivity.class);

//        ButterKnife.bind(mActivity);
    }

    @Test
    public void testStart() throws Exception {


    }
}
