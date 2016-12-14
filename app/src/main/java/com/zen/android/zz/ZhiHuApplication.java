package com.zen.android.zz;

import android.app.Application;
import android.content.Context;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.zen.android.zz.common.ContextUtil;
import com.zen.android.zz.sdk.ZhiHu;

/**
 * @author zen yang - 2016/12/11
 */
public class ZhiHuApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        ContextUtil.setAppContext(base);

        FlowManager.init(new FlowConfig.Builder(this).build());
        FlowManager.initModule(ZhiHu.getDatabaseHolderClass());
    }
}
