package com.zen.android.zz.sdk;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.squareup.moshi.Moshi;
import com.zen.android.brite.dbflow.DbflowBrite;
import com.zen.android.puck.runner.PuckTestRunner;
import com.zen.android.zz.sdk.model.Story;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

/**
 * @author zen yang - 2016/12/11
 */
@RunWith(PuckTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 23)
public class ZhiHuDbTest {

    MockData mMockData;

    @Before
    public void setUp() throws Exception {
        FlowManager.init(new FlowConfig.Builder(RuntimeEnvironment.application)
                .addDatabaseHolder(ZhiHu.getDatabaseHolderClass())
                .build());

        String mock = FileUtils.getFileContent("mock.json");
        mMockData = new Moshi.Builder().build().adapter(MockData.class).fromJson(mock);
    }

    @After
    public void tearDown() throws Exception {
        FlowManager.reset();
    }

    @Test
    public void testStoryLocal() throws Exception {
        DbflowBrite.save(Story.class, mMockData.getNews().getStories());

        DbflowBrite.Query.from(Story.class)
                .queryModels()
                .subscribe(result -> {
                    Assert.assertSame(result.size(), 6);
                });
    }

}
