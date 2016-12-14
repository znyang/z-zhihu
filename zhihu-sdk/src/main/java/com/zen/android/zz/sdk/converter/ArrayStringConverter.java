package com.zen.android.zz.sdk.converter;

import com.raizlabs.android.dbflow.converter.TypeConverter;

/**
 * @author zen yang - 2016/12/11
 */
@com.raizlabs.android.dbflow.annotation.TypeConverter
public class ArrayStringConverter extends TypeConverter<String, String[]> {

    private JsonConvertHelper<String[]> mHelper = new JsonConvertHelper<>(String[].class);

    @Override
    public String getDBValue(String[] model) {
        return mHelper.getDBValue(model);
    }

    @Override
    public String[] getModelValue(String data) {
        return mHelper.getModelValue(data);
    }
}
