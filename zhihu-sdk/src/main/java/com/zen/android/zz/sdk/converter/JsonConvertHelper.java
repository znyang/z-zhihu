package com.zen.android.zz.sdk.converter;

import android.util.Log;

import com.squareup.moshi.Moshi;

import java.io.IOException;

/**
 * @author zen yang - 2016/12/11
 */
public class JsonConvertHelper<ModelClass> {

    static final Moshi MOSHI = new Moshi.Builder().build();

    private Class<ModelClass> mClzModel;

    public JsonConvertHelper(Class<ModelClass> clzModel) {
        mClzModel = clzModel;
    }

    String getDBValue(ModelClass model) {
        return MOSHI.adapter(mClzModel).toJson(model);
    }

    ModelClass getModelValue(String data) {
        try {
            return MOSHI.adapter(mClzModel).fromJson(data);
        } catch (IOException e) {
            Log.e("JsonConvert", "getModelValue fail", e);
            return null;
        }
    }


}
