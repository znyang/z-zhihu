package com.zen.android.zz.sdk;

import com.zen.android.brite.dbflow.DbflowBrite;
import com.zen.android.puck.runner.PuckTestRunner;
import com.zen.android.zz.sdk.model.Story;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

/**
 * @author zen
 * @version 2016/12/8
 */
@RunWith(PuckTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 23)
public class ZhiHuTest {

    ZhiHuService mService;

    @Before
    public void setUp() throws Exception {
        mService = ZhiHu.get().service();
    }

    @Test
    public void testStoryRemote() throws Exception {
        mService.getNewsLatest()
                .subscribe(result -> {
                    Assert.assertNotNull(result.getDate());
                });
    }

}
